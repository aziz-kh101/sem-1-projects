using System.Data;
using System.Data.SQLite;
using VehiculeProject.models;

namespace VehiculeProject
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (username.Text.Trim() == "" || password.Text.Trim() == "")
            {
                MessageBox.Show("you need to complete information", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                SQLiteConnection conn = new SQLiteConnection("Data Source=project.db3;version=3;");
                conn.Open();

                string query = "select * from users where username = @username and password = @password";
                SQLiteCommand cmd = new SQLiteCommand(query, conn);
                cmd.Parameters.AddWithValue("@username", username.Text);
                cmd.Parameters.AddWithValue("@password", password.Text);
                SQLiteDataAdapter da = new SQLiteDataAdapter(cmd);
                DataTable dt = new DataTable();

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    User user = new User();
                    user.Id = int.Parse(dt.Rows[0]["id"].ToString());
                    user.Username = dt.Rows[0]["username"].ToString();
                    user.Password = dt.Rows[0]["password"].ToString();
                    user.Role = int.Parse(dt.Rows[0]["role"].ToString());

                    this.Hide();
                    username.Text = "";
                    password.Text = "";
                    var form2 = new Form2(this, user);
                    form2.Show();
                }
                else
                {
                    MessageBox.Show("username or password incorrect", "failed", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }

                conn.Close();
            }

        }
    }
}