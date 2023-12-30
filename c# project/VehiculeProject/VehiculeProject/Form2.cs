using System.ComponentModel;
using System.Data;
using System.Data.SQLite;
using System.Diagnostics;
using VehiculeProject.models;

namespace VehiculeProject
{
    public partial class Form2 : Form
    {
        private User? CurrentUser;
        private bool signout = false;
        private Form1 SignInForm;
        private User? EditingUser;
        private Vehicule? EditingVehicule;
        private int EditingIndex;
        public Form2(Form1 SignInForm, User CurrentUser)
        {
            InitializeComponent();
            this.CurrentUser = CurrentUser;
            this.SignInForm = SignInForm;

            if (CurrentUser.Role == 1)
            {
                vehiculeDelete.TabPages.Remove(adminsTab);
            }
            else
            {
                dataGridView1.DataSource = GetUsers();
                dataGridView1.Columns["Id"].Visible = false;
                dataGridView1.Columns["Role"].Visible = false;
            }

            username.Text = "@" + CurrentUser.Username;

            dataGridView2.DataSource = GetVehicules();
            dataGridView2.Columns["Id"].Visible = false;
        }

        private void save_Click(object sender, EventArgs e)
        {
            if (usernameInput.Text.Trim() == "" || passwordInput.Text.Trim() == "")
            {
                MessageBox.Show("you need to complete information", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            BindingList<User> Admins = dataGridView1.DataSource as BindingList<User>;
            SQLiteConnection conn = new SQLiteConnection("Data Source=project.db3;version=3;");
            conn.Open();

            try
            {
                if (EditingUser != null)
                {

                    string query = "update users set username = @username , password = @password  where id = @id";
                    SQLiteCommand cmd = new SQLiteCommand(query, conn);
                    cmd.Parameters.AddWithValue("id", EditingUser.Id);
                    cmd.Parameters.AddWithValue("username", usernameInput.Text.Trim());
                    cmd.Parameters.AddWithValue("password", passwordInput.Text.Trim());
                    int x = cmd.ExecuteNonQuery();
                    EditingUser.Username = usernameInput.Text.Trim();
                    EditingUser.Password = passwordInput.Text.Trim();

                    Admins[EditingIndex] = EditingUser;
                    conn.Close();

                    EditingUser = null;
                }
                else
                {
                    string query = "insert into users values (null, @username, @password, 1)";
                    SQLiteCommand cmd = new SQLiteCommand(query, conn);
                    cmd.Parameters.AddWithValue("username", usernameInput.Text.Trim());
                    cmd.Parameters.AddWithValue("password", passwordInput.Text.Trim());
                    int x = cmd.ExecuteNonQuery();

                    User user = new User();
                    user.Id = (int)conn.LastInsertRowId;
                    user.Username = usernameInput.Text.Trim();
                    user.Password = passwordInput.Text.Trim();
                    user.Role = 1;

                    Admins.Add(user);
                    conn.Close();
                }

                usernameInput.Text = "";
                passwordInput.Text = "";
            }
            catch (SQLiteException ex)
            {
                if (ex.ErrorCode == 19)
                {
                    MessageBox.Show("username already exists", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            finally
            {
                if (conn != null)
                {
                    conn.Close();
                }
            }
        }

        private void logout_Click(object sender, EventArgs e)
        {
            signout = true;
            this.Close();
            this.SignInForm.Show();
        }

        private void closed(object sender, FormClosedEventArgs e)
        {
            if (!signout)
            {
                this.SignInForm.Close();
            }
        }

        private BindingList<User> GetUsers()
        {
            BindingList<User> users = new BindingList<User>();
            SQLiteConnection conn = new SQLiteConnection("Data Source=project.db3;version=3;");
            conn.Open();

            string query = "select * from users where role = 1";
            SQLiteCommand cmd = new SQLiteCommand(query, conn);
            SQLiteDataAdapter da = new SQLiteDataAdapter(cmd);
            DataTable dt = new DataTable();

            da.Fill(dt);

            foreach (DataRow data in dt.Rows)
            {
                User user = new User();
                user.Id = int.Parse(data["id"].ToString());
                user.Username = data["username"].ToString();
                user.Password = data["password"].ToString();
                user.Role = int.Parse(data["role"].ToString());
                users.Add(user);

            }

            conn.Close();
            return users;
        }

        private BindingList<Vehicule> GetVehicules()
        {
            BindingList<Vehicule> vehicules = new BindingList<Vehicule>();
            SQLiteConnection conn = new SQLiteConnection("Data Source=project.db3;version=3;");
            conn.Open();

            string query = "select * from vehicules";
            SQLiteCommand cmd = new SQLiteCommand(query, conn);
            SQLiteDataAdapter da = new SQLiteDataAdapter(cmd);
            DataTable dt = new DataTable();

            da.Fill(dt);

            foreach (DataRow data in dt.Rows)
            {
                Vehicule vehicule = new Vehicule();
                vehicule.Id = int.Parse(data["id"].ToString());
                vehicule.Matricule = data["matricule"].ToString();
                vehicule.Brand = data["brand"].ToString();
                vehicule.Model = data["model"].ToString();
                vehicule.Year = int.Parse(data["year"].ToString());
                vehicules.Add(vehicule);

            }

            conn.Close();
            return vehicules;

        }

        private void delete_Click(object sender, EventArgs e)
        {
            User SelectedUser = getSetlectedUser();
            if (SelectedUser != null)
            {
                BindingList<User> Admins = dataGridView1.DataSource as BindingList<User>;

                DialogResult result = MessageBox.Show("Do you want to delete admin : "
                    + SelectedUser.Username, "confirm", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    SQLiteConnection conn = new SQLiteConnection("Data Source=project.db3;version=3;");
                    conn.Open();
                    string query = "delete from users where id = @id";
                    SQLiteCommand cmd = new SQLiteCommand(query, conn);
                    cmd.Parameters.AddWithValue("id", SelectedUser.Id);
                    int x = cmd.ExecuteNonQuery();
                    Admins.Remove(SelectedUser);
                    conn.Close();

                }
            }
        }

        private void update_Click(object sender, EventArgs e)
        {
            User SelectedUser = getSetlectedUser();
            if (SelectedUser != null)
            {
                EditingUser = SelectedUser;
                EditingIndex = getSetlectedIndex();
                usernameInput.Text = EditingUser.Username;
                passwordInput.Text = EditingUser.Password;
            }
        }

        private User getSetlectedUser()
        {
            if (dataGridView1.SelectedRows.Count == 1)
            {
                BindingList<User> Admins = dataGridView1.DataSource as BindingList<User>;
                return Admins[getSetlectedIndex()];
            }
            else { return null; }
        }

        private int getSetlectedIndex()
        {
            if (dataGridView1.SelectedRows.Count == 1)
            {
                return dataGridView1.SelectedRows[0].Index;
            }
            else { return -1; }
        }

        private void vehiculeSave_Click(object sender, EventArgs e)
        {
            if (matInput.Text.Trim() == "" || brandInput.Text.Trim() == "" || modelInput.Text.Trim() == "" || yearInput.Text.Trim() == "")
            {
                MessageBox.Show("you need to complete information", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (!int.TryParse(yearInput.Text.Trim(), out _))
            {
                MessageBox.Show("year should be number", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            BindingList<Vehicule> vehicules = dataGridView2.DataSource as BindingList<Vehicule>;
            SQLiteConnection conn = new SQLiteConnection("Data Source=project.db3;version=3;");
            conn.Open();

            try
            {
                if (EditingVehicule != null)
                {
                    string query = "update vehicules set matricule = @matricule , brand = @brand , model = @model , year = @year   where id = @id";
                    SQLiteCommand cmd = new SQLiteCommand(query, conn);
                    cmd.Parameters.AddWithValue("id", EditingVehicule.Id);
                    cmd.Parameters.AddWithValue("matricule", matInput.Text.Trim());
                    cmd.Parameters.AddWithValue("brand", brandInput.Text.Trim());
                    cmd.Parameters.AddWithValue("model", modelInput.Text.Trim());
                    cmd.Parameters.AddWithValue("year", int.Parse(yearInput.Text.Trim()));
                    int x = cmd.ExecuteNonQuery();
                    EditingVehicule.Matricule = matInput.Text.Trim();
                    EditingVehicule.Brand = brandInput.Text.Trim();
                    EditingVehicule.Model = modelInput.Text.Trim();
                    EditingVehicule.Year = int.Parse(yearInput.Text.Trim());

                    vehicules[EditingIndex] = EditingVehicule;

                    EditingVehicule = null;
                }
                else
                {
                    string query = "insert into vehicules values (null, @matricule, @brand, @model, @year)";
                    SQLiteCommand cmd = new SQLiteCommand(query, conn);
                    cmd.Parameters.AddWithValue("matricule", matInput.Text.Trim());
                    cmd.Parameters.AddWithValue("brand", brandInput.Text.Trim());
                    cmd.Parameters.AddWithValue("model", modelInput.Text.Trim());
                    cmd.Parameters.AddWithValue("year", int.Parse(yearInput.Text.Trim()));
                    int x = cmd.ExecuteNonQuery();

                    Vehicule vehicule = new Vehicule();
                    vehicule.Id = (int)conn.LastInsertRowId;
                    vehicule.Matricule = matInput.Text.Trim();
                    vehicule.Brand = brandInput.Text.Trim();
                    vehicule.Model = modelInput.Text.Trim();
                    vehicule.Year = int.Parse(yearInput.Text.Trim());

                    vehicules.Add(vehicule);
                }

                matInput.Text = "";
                brandInput.Text = "";
                modelInput.Text = "";
                yearInput.Text = "";
            }
            catch (SQLiteException ex)
            {
                if (ex.ErrorCode == 19)
                {
                    MessageBox.Show("username already exists", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            finally
            {
                if (conn != null)
                {
                    conn.Close();
                }
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Vehicule selectedVehicule = getSetlectedVehicule();
            if (selectedVehicule != null)
            {
                BindingList<Vehicule> vehicules = dataGridView2.DataSource as BindingList<Vehicule>;

                DialogResult result = MessageBox.Show("Do you want to delete vehicule : "
                    + selectedVehicule.Matricule, "confirm", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (result == DialogResult.Yes)
                {
                    SQLiteConnection conn = new SQLiteConnection("Data Source=project.db3;version=3;");
                    conn.Open();
                    string query = "delete from vehicules where id = @id";
                    SQLiteCommand cmd = new SQLiteCommand(query, conn);
                    cmd.Parameters.AddWithValue("id", selectedVehicule.Id);
                    int x = cmd.ExecuteNonQuery();
                    vehicules.Remove(selectedVehicule);
                    conn.Close();

                }
            }
        }

        private Vehicule getSetlectedVehicule()
        {
            if (dataGridView2.SelectedRows.Count == 1)
            {
                BindingList<Vehicule> vehicules = dataGridView2.DataSource as BindingList<Vehicule>;
                return vehicules[getSetlectedVehiculeIndex()];
            }
            else { return null; }
        }

        private int getSetlectedVehiculeIndex()
        {
            if (dataGridView2.SelectedRows.Count == 1)
            {
                return dataGridView2.SelectedRows[0].Index;
            }
            else { return -1; }
        }

        private void VehiculeUpdate_Click(object sender, EventArgs e)
        {
            Vehicule SelectedVehicule = getSetlectedVehicule();
            if (SelectedVehicule != null)
            {
                EditingVehicule = SelectedVehicule;
                EditingIndex = getSetlectedVehiculeIndex();
                matInput.Text = EditingVehicule.Matricule;
                brandInput.Text = EditingVehicule.Brand;
                modelInput.Text = EditingVehicule.Model;
                yearInput.Text = EditingVehicule.Year.ToString();
            }
        }
    }
}
