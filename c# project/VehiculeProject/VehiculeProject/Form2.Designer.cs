namespace VehiculeProject
{
    partial class Form2
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            vehiculeDelete = new TabControl();
            adminsTab = new TabPage();
            dataGridView1 = new DataGridView();
            panel2 = new Panel();
            update = new Button();
            delete = new Button();
            panel1 = new Panel();
            label3 = new Label();
            label2 = new Label();
            save = new Button();
            label1 = new Label();
            passwordInput = new TextBox();
            usernameInput = new TextBox();
            vehiculesTab = new TabPage();
            panel6 = new Panel();
            dataGridView2 = new DataGridView();
            panel7 = new Panel();
            button3 = new Button();
            VehiculeUpdate = new Button();
            panel5 = new Panel();
            vehiculeSave = new Button();
            label8 = new Label();
            label5 = new Label();
            label7 = new Label();
            label6 = new Label();
            yearInput = new TextBox();
            modelInput = new TextBox();
            brandInput = new TextBox();
            matInput = new TextBox();
            label4 = new Label();
            panel3 = new Panel();
            username = new Label();
            logout = new Button();
            panel4 = new Panel();
            vehiculeDelete.SuspendLayout();
            adminsTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)dataGridView1).BeginInit();
            panel2.SuspendLayout();
            panel1.SuspendLayout();
            vehiculesTab.SuspendLayout();
            panel6.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)dataGridView2).BeginInit();
            panel7.SuspendLayout();
            panel5.SuspendLayout();
            panel3.SuspendLayout();
            panel4.SuspendLayout();
            SuspendLayout();
            // 
            // vehiculeDelete
            // 
            vehiculeDelete.Controls.Add(adminsTab);
            vehiculeDelete.Controls.Add(vehiculesTab);
            vehiculeDelete.Dock = DockStyle.Fill;
            vehiculeDelete.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            vehiculeDelete.Location = new Point(0, 0);
            vehiculeDelete.Margin = new Padding(3, 4, 3, 4);
            vehiculeDelete.Name = "vehiculeDelete";
            vehiculeDelete.SelectedIndex = 0;
            vehiculeDelete.Size = new Size(957, 502);
            vehiculeDelete.TabIndex = 0;
            // 
            // adminsTab
            // 
            adminsTab.Controls.Add(dataGridView1);
            adminsTab.Controls.Add(panel2);
            adminsTab.Controls.Add(panel1);
            adminsTab.Location = new Point(4, 33);
            adminsTab.Margin = new Padding(3, 4, 3, 4);
            adminsTab.Name = "adminsTab";
            adminsTab.Padding = new Padding(3, 4, 3, 4);
            adminsTab.Size = new Size(949, 465);
            adminsTab.TabIndex = 0;
            adminsTab.Text = "admins";
            adminsTab.UseVisualStyleBackColor = true;
            // 
            // dataGridView1
            // 
            dataGridView1.AllowUserToAddRows = false;
            dataGridView1.AllowUserToDeleteRows = false;
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView1.BackgroundColor = Color.LightGray;
            dataGridView1.BorderStyle = BorderStyle.Fixed3D;
            dataGridView1.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridView1.Dock = DockStyle.Fill;
            dataGridView1.Location = new Point(315, 4);
            dataGridView1.Margin = new Padding(3, 4, 3, 4);
            dataGridView1.MultiSelect = false;
            dataGridView1.Name = "dataGridView1";
            dataGridView1.ReadOnly = true;
            dataGridView1.RowHeadersWidth = 51;
            dataGridView1.RowHeadersWidthSizeMode = DataGridViewRowHeadersWidthSizeMode.DisableResizing;
            dataGridView1.RowTemplate.Height = 24;
            dataGridView1.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dataGridView1.Size = new Size(631, 332);
            dataGridView1.TabIndex = 2;
            // 
            // panel2
            // 
            panel2.Controls.Add(update);
            panel2.Controls.Add(delete);
            panel2.Dock = DockStyle.Bottom;
            panel2.Location = new Point(315, 336);
            panel2.Margin = new Padding(3, 4, 3, 4);
            panel2.Name = "panel2";
            panel2.Size = new Size(631, 125);
            panel2.TabIndex = 1;
            // 
            // update
            // 
            update.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;
            update.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            update.Location = new Point(504, 39);
            update.Margin = new Padding(3, 4, 3, 4);
            update.Name = "update";
            update.Size = new Size(104, 60);
            update.TabIndex = 5;
            update.Text = "update";
            update.UseVisualStyleBackColor = true;
            update.Click += update_Click;
            // 
            // delete
            // 
            delete.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;
            delete.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            delete.Location = new Point(389, 39);
            delete.Margin = new Padding(3, 4, 3, 4);
            delete.Name = "delete";
            delete.Size = new Size(104, 60);
            delete.TabIndex = 4;
            delete.Text = "delete";
            delete.UseVisualStyleBackColor = true;
            delete.Click += delete_Click;
            // 
            // panel1
            // 
            panel1.Controls.Add(label3);
            panel1.Controls.Add(label2);
            panel1.Controls.Add(save);
            panel1.Controls.Add(label1);
            panel1.Controls.Add(passwordInput);
            panel1.Controls.Add(usernameInput);
            panel1.Dock = DockStyle.Left;
            panel1.Location = new Point(3, 4);
            panel1.Margin = new Padding(3, 4, 3, 4);
            panel1.Name = "panel1";
            panel1.Size = new Size(312, 457);
            panel1.TabIndex = 0;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(22, 194);
            label3.Name = "label3";
            label3.Size = new Size(98, 24);
            label3.TabIndex = 5;
            label3.Text = "Password";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(22, 102);
            label2.Name = "label2";
            label2.Size = new Size(102, 24);
            label2.TabIndex = 4;
            label2.Text = "Username";
            // 
            // save
            // 
            save.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            save.Location = new Point(91, 295);
            save.Margin = new Padding(3, 4, 3, 4);
            save.Name = "save";
            save.Size = new Size(104, 60);
            save.TabIndex = 3;
            save.Text = "save";
            save.UseVisualStyleBackColor = true;
            save.Click += save_Click;
            // 
            // label1
            // 
            label1.Font = new Font("Roboto", 16.2F, FontStyle.Bold | FontStyle.Italic, GraphicsUnit.Point);
            label1.Location = new Point(0, 39);
            label1.Name = "label1";
            label1.Size = new Size(312, 34);
            label1.TabIndex = 2;
            label1.Text = "Admin management";
            label1.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // passwordInput
            // 
            passwordInput.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            passwordInput.Location = new Point(22, 223);
            passwordInput.Margin = new Padding(3, 4, 3, 4);
            passwordInput.Name = "passwordInput";
            passwordInput.Size = new Size(265, 32);
            passwordInput.TabIndex = 1;
            // 
            // usernameInput
            // 
            usernameInput.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            usernameInput.Location = new Point(22, 131);
            usernameInput.Margin = new Padding(3, 4, 3, 4);
            usernameInput.Name = "usernameInput";
            usernameInput.Size = new Size(265, 32);
            usernameInput.TabIndex = 0;
            // 
            // vehiculesTab
            // 
            vehiculesTab.Controls.Add(panel6);
            vehiculesTab.Controls.Add(panel5);
            vehiculesTab.Location = new Point(4, 33);
            vehiculesTab.Margin = new Padding(3, 4, 3, 4);
            vehiculesTab.Name = "vehiculesTab";
            vehiculesTab.Padding = new Padding(3, 4, 3, 4);
            vehiculesTab.Size = new Size(949, 465);
            vehiculesTab.TabIndex = 1;
            vehiculesTab.Text = "vehicules";
            vehiculesTab.UseVisualStyleBackColor = true;
            // 
            // panel6
            // 
            panel6.Controls.Add(dataGridView2);
            panel6.Controls.Add(panel7);
            panel6.Dock = DockStyle.Fill;
            panel6.Location = new Point(315, 4);
            panel6.Name = "panel6";
            panel6.Size = new Size(631, 457);
            panel6.TabIndex = 1;
            // 
            // dataGridView2
            // 
            dataGridView2.AllowUserToAddRows = false;
            dataGridView2.AllowUserToDeleteRows = false;
            dataGridView2.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView2.BackgroundColor = Color.LightGray;
            dataGridView2.BorderStyle = BorderStyle.Fixed3D;
            dataGridView2.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridView2.Dock = DockStyle.Fill;
            dataGridView2.Location = new Point(0, 0);
            dataGridView2.MultiSelect = false;
            dataGridView2.Name = "dataGridView2";
            dataGridView2.ReadOnly = true;
            dataGridView2.RowHeadersWidth = 51;
            dataGridView2.RowHeadersWidthSizeMode = DataGridViewRowHeadersWidthSizeMode.DisableResizing;
            dataGridView2.RowTemplate.Height = 29;
            dataGridView2.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dataGridView2.Size = new Size(631, 332);
            dataGridView2.TabIndex = 1;
            // 
            // panel7
            // 
            panel7.Controls.Add(button3);
            panel7.Controls.Add(VehiculeUpdate);
            panel7.Dock = DockStyle.Bottom;
            panel7.Location = new Point(0, 332);
            panel7.Name = "panel7";
            panel7.Size = new Size(631, 125);
            panel7.TabIndex = 0;
            // 
            // button3
            // 
            button3.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            button3.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            button3.Location = new Point(394, 45);
            button3.Margin = new Padding(3, 4, 3, 4);
            button3.Name = "button3";
            button3.Size = new Size(104, 60);
            button3.TabIndex = 12;
            button3.Text = "delete";
            button3.UseVisualStyleBackColor = true;
            button3.Click += button3_Click;
            // 
            // VehiculeUpdate
            // 
            VehiculeUpdate.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            VehiculeUpdate.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            VehiculeUpdate.Location = new Point(504, 45);
            VehiculeUpdate.Margin = new Padding(3, 4, 3, 4);
            VehiculeUpdate.Name = "VehiculeUpdate";
            VehiculeUpdate.Size = new Size(104, 60);
            VehiculeUpdate.TabIndex = 11;
            VehiculeUpdate.Text = "update";
            VehiculeUpdate.UseVisualStyleBackColor = true;
            VehiculeUpdate.Click += VehiculeUpdate_Click;
            // 
            // panel5
            // 
            panel5.Controls.Add(vehiculeSave);
            panel5.Controls.Add(label8);
            panel5.Controls.Add(label5);
            panel5.Controls.Add(label7);
            panel5.Controls.Add(label6);
            panel5.Controls.Add(yearInput);
            panel5.Controls.Add(modelInput);
            panel5.Controls.Add(brandInput);
            panel5.Controls.Add(matInput);
            panel5.Controls.Add(label4);
            panel5.Dock = DockStyle.Left;
            panel5.Location = new Point(3, 4);
            panel5.Name = "panel5";
            panel5.Size = new Size(312, 457);
            panel5.TabIndex = 0;
            // 
            // vehiculeSave
            // 
            vehiculeSave.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            vehiculeSave.Location = new Point(95, 377);
            vehiculeSave.Margin = new Padding(3, 4, 3, 4);
            vehiculeSave.Name = "vehiculeSave";
            vehiculeSave.Size = new Size(104, 60);
            vehiculeSave.TabIndex = 5;
            vehiculeSave.Text = "save";
            vehiculeSave.UseVisualStyleBackColor = true;
            vehiculeSave.Click += vehiculeSave_Click;
            // 
            // label8
            // 
            label8.AutoSize = true;
            label8.Location = new Point(15, 303);
            label8.Name = "label8";
            label8.Size = new Size(155, 24);
            label8.TabIndex = 9;
            label8.Text = "Fabrication Year";
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new Point(15, 148);
            label5.Name = "label5";
            label5.Size = new Size(62, 24);
            label5.TabIndex = 9;
            label5.Text = "Brand";
            // 
            // label7
            // 
            label7.AutoSize = true;
            label7.Location = new Point(15, 231);
            label7.Name = "label7";
            label7.Size = new Size(65, 24);
            label7.TabIndex = 8;
            label7.Text = "Model";
            // 
            // label6
            // 
            label6.AutoSize = true;
            label6.Location = new Point(15, 76);
            label6.Name = "label6";
            label6.Size = new Size(94, 24);
            label6.TabIndex = 8;
            label6.Text = "Matricule";
            // 
            // yearInput
            // 
            yearInput.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            yearInput.Location = new Point(15, 332);
            yearInput.Margin = new Padding(3, 4, 3, 4);
            yearInput.Name = "yearInput";
            yearInput.Size = new Size(265, 32);
            yearInput.TabIndex = 4;
            // 
            // modelInput
            // 
            modelInput.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            modelInput.Location = new Point(15, 260);
            modelInput.Margin = new Padding(3, 4, 3, 4);
            modelInput.Name = "modelInput";
            modelInput.Size = new Size(265, 32);
            modelInput.TabIndex = 3;
            // 
            // brandInput
            // 
            brandInput.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            brandInput.Location = new Point(15, 177);
            brandInput.Margin = new Padding(3, 4, 3, 4);
            brandInput.Name = "brandInput";
            brandInput.Size = new Size(265, 32);
            brandInput.TabIndex = 2;
            // 
            // matInput
            // 
            matInput.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            matInput.Location = new Point(15, 105);
            matInput.Margin = new Padding(3, 4, 3, 4);
            matInput.Name = "matInput";
            matInput.Size = new Size(265, 32);
            matInput.TabIndex = 1;
            // 
            // label4
            // 
            label4.Font = new Font("Roboto", 16.2F, FontStyle.Bold | FontStyle.Italic, GraphicsUnit.Point);
            label4.Location = new Point(0, 25);
            label4.Name = "label4";
            label4.Size = new Size(312, 34);
            label4.TabIndex = 3;
            label4.Text = "Vehicule management";
            label4.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // panel3
            // 
            panel3.Controls.Add(username);
            panel3.Controls.Add(logout);
            panel3.Dock = DockStyle.Top;
            panel3.Location = new Point(0, 0);
            panel3.Margin = new Padding(3, 4, 3, 4);
            panel3.Name = "panel3";
            panel3.Size = new Size(957, 67);
            panel3.TabIndex = 1;
            // 
            // username
            // 
            username.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            username.AutoEllipsis = true;
            username.Font = new Font("Roboto", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            username.Location = new Point(559, 24);
            username.Name = "username";
            username.Size = new Size(271, 20);
            username.TabIndex = 5;
            username.Text = "@username";
            username.TextAlign = ContentAlignment.MiddleRight;
            // 
            // logout
            // 
            logout.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            logout.Font = new Font("Roboto", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            logout.Location = new Point(836, 9);
            logout.Margin = new Padding(3, 4, 3, 4);
            logout.Name = "logout";
            logout.Size = new Size(91, 51);
            logout.TabIndex = 4;
            logout.Text = "log out";
            logout.UseVisualStyleBackColor = true;
            logout.Click += logout_Click;
            // 
            // panel4
            // 
            panel4.Controls.Add(vehiculeDelete);
            panel4.Dock = DockStyle.Fill;
            panel4.Location = new Point(0, 67);
            panel4.Margin = new Padding(3, 4, 3, 4);
            panel4.Name = "panel4";
            panel4.Size = new Size(957, 502);
            panel4.TabIndex = 2;
            // 
            // Form2
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(957, 569);
            Controls.Add(panel4);
            Controls.Add(panel3);
            Margin = new Padding(3, 4, 3, 4);
            Name = "Form2";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Form2";
            FormClosed += closed;
            vehiculeDelete.ResumeLayout(false);
            adminsTab.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)dataGridView1).EndInit();
            panel2.ResumeLayout(false);
            panel1.ResumeLayout(false);
            panel1.PerformLayout();
            vehiculesTab.ResumeLayout(false);
            panel6.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)dataGridView2).EndInit();
            panel7.ResumeLayout(false);
            panel5.ResumeLayout(false);
            panel5.PerformLayout();
            panel3.ResumeLayout(false);
            panel4.ResumeLayout(false);
            ResumeLayout(false);
        }

        #endregion

        private TabControl vehiculeDelete;
        private Panel panel1;
        private TabPage vehiculesTab;
        private TabPage adminsTab;
        private TextBox usernameInput;
        private Label label1;
        private TextBox passwordInput;
        private Button save;
        private DataGridView dataGridView1;
        private Panel panel2;
        private Button update;
        private Button delete;
        private Panel panel3;
        private Button logout;
        private Panel panel4;
        private Label username;
        private Label label2;
        private Label label3;
        private Panel panel6;
        private Panel panel7;
        private Panel panel5;
        private Label label5;
        private Label label6;
        private TextBox brandInput;
        private TextBox matInput;
        private Label label4;
        private Button button3;
        private Button VehiculeUpdate;
        private Button vehiculeSave;
        private Label label8;
        private Label label7;
        private TextBox yearInput;
        private TextBox modelInput;
        private DataGridView dataGridView2;
    }
}