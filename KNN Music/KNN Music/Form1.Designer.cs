namespace KNN_Music
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
            this.buttonDatabaseDir = new System.Windows.Forms.Button();
            this.textBoxDatabaseDir = new System.Windows.Forms.TextBox();
            this.buttonClassify = new System.Windows.Forms.Button();
            this.textBoxResult = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.openFileDialogDatabase = new System.Windows.Forms.OpenFileDialog();
            this.numericUpDownK = new System.Windows.Forms.NumericUpDown();
            this.label4 = new System.Windows.Forms.Label();
            this.pictureBox = new System.Windows.Forms.PictureBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.textBoxNewDir = new System.Windows.Forms.TextBox();
            this.buttonNewDir = new System.Windows.Forms.Button();
            this.openFileDialogNew = new System.Windows.Forms.OpenFileDialog();
            this.comboBoxSelected = new System.Windows.Forms.ComboBox();
            this.labelSelected = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownK)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).BeginInit();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // buttonDatabaseDir
            // 
            this.buttonDatabaseDir.Location = new System.Drawing.Point(275, 10);
            this.buttonDatabaseDir.Name = "buttonDatabaseDir";
            this.buttonDatabaseDir.Size = new System.Drawing.Size(41, 23);
            this.buttonDatabaseDir.TabIndex = 0;
            this.buttonDatabaseDir.Text = "...";
            this.buttonDatabaseDir.UseVisualStyleBackColor = true;
            this.buttonDatabaseDir.Click += new System.EventHandler(this.buttonDatabaseDir_Click);
            // 
            // textBoxDatabaseDir
            // 
            this.textBoxDatabaseDir.Location = new System.Drawing.Point(49, 12);
            this.textBoxDatabaseDir.Name = "textBoxDatabaseDir";
            this.textBoxDatabaseDir.ReadOnly = true;
            this.textBoxDatabaseDir.Size = new System.Drawing.Size(220, 20);
            this.textBoxDatabaseDir.TabIndex = 1;
            // 
            // buttonClassify
            // 
            this.buttonClassify.Enabled = false;
            this.buttonClassify.Location = new System.Drawing.Point(221, 127);
            this.buttonClassify.Name = "buttonClassify";
            this.buttonClassify.Size = new System.Drawing.Size(75, 39);
            this.buttonClassify.TabIndex = 4;
            this.buttonClassify.Text = "Klasyfikuj";
            this.buttonClassify.UseVisualStyleBackColor = true;
            this.buttonClassify.Click += new System.EventHandler(this.buttonClassify_Click);
            // 
            // textBoxResult
            // 
            this.textBoxResult.Location = new System.Drawing.Point(63, 200);
            this.textBoxResult.Name = "textBoxResult";
            this.textBoxResult.ReadOnly = true;
            this.textBoxResult.Size = new System.Drawing.Size(152, 20);
            this.textBoxResult.TabIndex = 5;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(31, 13);
            this.label1.TabIndex = 6;
            this.label1.Text = "Baza";
            // 
            // openFileDialogDatabase
            // 
            this.openFileDialogDatabase.FileName = "openFileDialog1";
            // 
            // numericUpDownK
            // 
            this.numericUpDownK.Location = new System.Drawing.Point(26, 19);
            this.numericUpDownK.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numericUpDownK.Name = "numericUpDownK";
            this.numericUpDownK.Size = new System.Drawing.Size(63, 20);
            this.numericUpDownK.TabIndex = 9;
            this.numericUpDownK.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(6, 21);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(14, 13);
            this.label4.TabIndex = 10;
            this.label4.Text = "K";
            // 
            // pictureBox
            // 
            this.pictureBox.Location = new System.Drawing.Point(322, 10);
            this.pictureBox.Name = "pictureBox";
            this.pictureBox.Size = new System.Drawing.Size(500, 500);
            this.pictureBox.TabIndex = 11;
            this.pictureBox.TabStop = false;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(18, 203);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(37, 13);
            this.label5.TabIndex = 12;
            this.label5.Text = "Wynik";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(12, 44);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(35, 13);
            this.label6.TabIndex = 15;
            this.label6.Text = "Nowe";
            // 
            // textBoxNewDir
            // 
            this.textBoxNewDir.Location = new System.Drawing.Point(49, 41);
            this.textBoxNewDir.Name = "textBoxNewDir";
            this.textBoxNewDir.ReadOnly = true;
            this.textBoxNewDir.Size = new System.Drawing.Size(220, 20);
            this.textBoxNewDir.TabIndex = 14;
            // 
            // buttonNewDir
            // 
            this.buttonNewDir.Location = new System.Drawing.Point(275, 39);
            this.buttonNewDir.Name = "buttonNewDir";
            this.buttonNewDir.Size = new System.Drawing.Size(41, 23);
            this.buttonNewDir.TabIndex = 13;
            this.buttonNewDir.Text = "...";
            this.buttonNewDir.UseVisualStyleBackColor = true;
            this.buttonNewDir.Click += new System.EventHandler(this.buttonNewDir_Click);
            // 
            // openFileDialogNew
            // 
            this.openFileDialogNew.FileName = "openFileDialog1";
            // 
            // comboBoxSelected
            // 
            this.comboBoxSelected.FormattingEnabled = true;
            this.comboBoxSelected.Location = new System.Drawing.Point(49, 67);
            this.comboBoxSelected.Name = "comboBoxSelected";
            this.comboBoxSelected.Size = new System.Drawing.Size(267, 21);
            this.comboBoxSelected.TabIndex = 16;
            this.comboBoxSelected.SelectedIndexChanged += new System.EventHandler(this.comboBoxSelected_SelectedIndexChanged);
            // 
            // labelSelected
            // 
            this.labelSelected.AutoSize = true;
            this.labelSelected.Location = new System.Drawing.Point(12, 70);
            this.labelSelected.Name = "labelSelected";
            this.labelSelected.Size = new System.Drawing.Size(35, 13);
            this.labelSelected.TabIndex = 17;
            this.labelSelected.Text = "Utwór";
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.numericUpDownK);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Location = new System.Drawing.Point(15, 94);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(200, 100);
            this.groupBox1.TabIndex = 18;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Parametry algorytmu";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(837, 527);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.labelSelected);
            this.Controls.Add(this.comboBoxSelected);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.textBoxNewDir);
            this.Controls.Add(this.buttonNewDir);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.pictureBox);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.textBoxResult);
            this.Controls.Add(this.buttonClassify);
            this.Controls.Add(this.textBoxDatabaseDir);
            this.Controls.Add(this.buttonDatabaseDir);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.Text = "Knn Music";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownK)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).EndInit();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonDatabaseDir;
        private System.Windows.Forms.TextBox textBoxDatabaseDir;
        private System.Windows.Forms.Button buttonClassify;
        private System.Windows.Forms.TextBox textBoxResult;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.OpenFileDialog openFileDialogDatabase;
        private System.Windows.Forms.NumericUpDown numericUpDownK;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.PictureBox pictureBox;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox textBoxNewDir;
        private System.Windows.Forms.Button buttonNewDir;
        private System.Windows.Forms.OpenFileDialog openFileDialogNew;
        private System.Windows.Forms.ComboBox comboBoxSelected;
        private System.Windows.Forms.Label labelSelected;
        private System.Windows.Forms.GroupBox groupBox1;
    }
}

