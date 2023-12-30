namespace VehiculeProject
{
    partial class Form1
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
            label1 = new Label();
            username = new TextBox();
            password = new TextBox();
            button1 = new Button();
            panel1 = new Panel();
            label3 = new Label();
            label2 = new Label();
            panel1.SuspendLayout();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Roboto", 18F, FontStyle.Bold, GraphicsUnit.Point);
            label1.ForeColor = Color.CornflowerBlue;
            label1.Location = new Point(34, 42);
            label1.Name = "label1";
            label1.Size = new Size(87, 29);
            label1.TabIndex = 0;
            label1.Text = "Sign In";
            // 
            // username
            // 
            username.Font = new Font("Roboto", 13.8F, FontStyle.Regular, GraphicsUnit.Point);
            username.Location = new Point(40, 140);
            username.Name = "username";
            username.Size = new Size(296, 30);
            username.TabIndex = 1;
            // 
            // password
            // 
            password.Font = new Font("Roboto", 13.8F, FontStyle.Regular, GraphicsUnit.Point);
            password.Location = new Point(40, 221);
            password.Name = "password";
            password.PasswordChar = '*';
            password.Size = new Size(296, 30);
            password.TabIndex = 2;
            // 
            // button1
            // 
            button1.Location = new Point(132, 273);
            button1.Name = "button1";
            button1.Size = new Size(121, 46);
            button1.TabIndex = 3;
            button1.Text = "sign in";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // panel1
            // 
            panel1.Anchor = AnchorStyles.None;
            panel1.BackgroundImageLayout = ImageLayout.None;
            panel1.Controls.Add(label3);
            panel1.Controls.Add(label2);
            panel1.Controls.Add(label1);
            panel1.Controls.Add(button1);
            panel1.Controls.Add(username);
            panel1.Controls.Add(password);
            panel1.Location = new Point(-2, -2);
            panel1.Name = "panel1";
            panel1.Size = new Size(363, 339);
            panel1.TabIndex = 4;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            label3.Location = new Point(38, 196);
            label3.Name = "label3";
            label3.Size = new Size(79, 19);
            label3.TabIndex = 5;
            label3.Text = "Password";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new Font("Roboto", 12F, FontStyle.Regular, GraphicsUnit.Point);
            label2.Location = new Point(39, 115);
            label2.Name = "label2";
            label2.Size = new Size(80, 19);
            label2.TabIndex = 4;
            label2.Text = "Username";
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(362, 334);
            Controls.Add(panel1);
            FormBorderStyle = FormBorderStyle.Fixed3D;
            MaximizeBox = false;
            Name = "Form1";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "sign in";
            panel1.ResumeLayout(false);
            panel1.PerformLayout();
            ResumeLayout(false);
        }

        #endregion

        private Label label1;
        private TextBox username;
        private TextBox password;
        private Button button1;
        private Panel panel1;
        private Label label3;
        private Label label2;
    }
}