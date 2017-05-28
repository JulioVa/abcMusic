﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Web;
using System.Drawing.Imaging;
using System.Drawing.Drawing2D;

namespace KNN_Music
{
    public partial class Form1 : Form
    {
        private string databaseUrl;
        private string newUrl;
        private Song[] songs;
        private Song[] newSongs;
        double minOnset = Double.MaxValue, maxOnset = Double.MinValue, minBeat = Double.MaxValue, maxBeat = Double.MinValue;
        double minOnsetCur = Double.MaxValue, maxOnsetCur = Double.MinValue, minBeatCur = Double.MaxValue, maxBeatCur = Double.MinValue;
        Bitmap bmp, bmpBig;
        Dictionary<int, Color> colors = new Dictionary<int, Color>();
        int picSize = 500;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            bmp = new Bitmap(picSize, picSize, PixelFormat.Format24bppRgb);
            pictureBox.Image = bmp;
            colors.Add(1, Color.Red);
            colors.Add(2, Color.Green);
            colors.Add(3, Color.Blue);
            colors.Add(4, Color.Yellow);
            colors.Add(5, Color.Purple);
        }

        private void buttonDatabaseDir_Click(object sender, EventArgs e)
        {
            openFileDialogDatabase.Filter = "Text files (*.txt)|*.txt";
            if (openFileDialogDatabase.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                textBoxDatabaseDir.Text = databaseUrl = openFileDialogDatabase.FileName;
                if(textBoxNewDir.Text.Length > 0)
                    buttonClassify.Enabled = true;

                if (File.Exists(databaseUrl))
                {
                    string[] t = File.ReadAllLines(databaseUrl);
                    songs = new Song[t.Length];
                    for (int i = 0; i < songs.Length; i++)
                    {
                        string[] split = t[i].Split(new string[] { "\\\\" }, StringSplitOptions.None);
                        double onset = double.Parse(split[1], CultureInfo.GetCultureInfo("en-US"));
                        double beat = double.Parse(split[2], CultureInfo.GetCultureInfo("en-US"));
                        int genre = int.Parse(split[3]);
                        songs[i] = new Song(split[0], onset, beat, genre);

                        minOnset = Math.Min(minOnset, onset);
                        maxOnset = Math.Max(maxOnset, onset);
                        minBeat = Math.Min(minBeat, beat);
                        maxBeat = Math.Max(maxBeat, beat);
                    }
                    minOnsetCur = minOnset;
                    maxOnsetCur = maxOnset;
                    minBeatCur = minBeat;
                    maxBeatCur = maxBeat;
                    Repaint(-1, -1);
                }
            }
        }

        private void buttonNewDir_Click(object sender, EventArgs e)
        {
            openFileDialogNew.Filter = "Text files (*.txt)|*.txt";
            if (openFileDialogNew.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                textBoxNewDir.Text = newUrl = openFileDialogNew.FileName;
                if (textBoxDatabaseDir.Text.Length > 0)
                    buttonClassify.Enabled = true;
                if (File.Exists(newUrl))
                {
                    comboBoxSelected.Items.Clear();
                    string[] t = File.ReadAllLines(newUrl);
                    newSongs = new Song[t.Length];
                    for (int i = 0; i < newSongs.Length; i++)
                    {
                        string[] split = t[i].Split(new string[] { "\\\\" }, StringSplitOptions.None);
                        double onset = double.Parse(split[1], CultureInfo.GetCultureInfo("en-US"));
                        double beat = double.Parse(split[2], CultureInfo.GetCultureInfo("en-US"));
                        newSongs[i] = new Song(split[0], onset, beat);
                        comboBoxSelected.Items.Add(split[0]);
                    }
                    comboBoxSelected.SelectedIndex = 0;
                }
            }
        }

        private void buttonClassify_Click(object sender, EventArgs e)
        {
            Song curSong = newSongs[comboBoxSelected.SelectedIndex];
                //new Song(double.Parse(textBoxOnset.Text), double.Parse(textBoxBeat.Text));
            
            int k = (int)numericUpDownK.Value;

            for (int i = 0; i < songs.Length; i++)
                songs[i].distance = Math.Sqrt(Math.Pow(curSong.onset - songs[i].onset, 2) + Math.Pow(curSong.beat - songs[i].beat, 2));

            Array.Sort(songs, Song.SortByDistance);

            int genreSum = 0;
            for (int i = 0; i < k; i++)
                genreSum += songs[i].genre;

            int result = (int)Math.Round((double)genreSum / (double)k);

            textBoxResult.Text = result.ToString();
        }

        private void Repaint(int curX, int curY)
        {
            bmp.Dispose();
            bmp = new Bitmap(picSize, picSize, PixelFormat.Format24bppRgb);
            using (var graphics = Graphics.FromImage(bmp))
            {
                graphics.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
                for (int i = 0; i < songs.Length; i++)
                {
                    int x = (int)Math.Round((songs[i].onset - minOnsetCur) / (maxOnsetCur - minOnsetCur) * (picSize - 1));
                    int y = (int)Math.Round((songs[i].beat - minBeatCur) / (maxBeatCur - minBeatCur) * (picSize - 1));
                    graphics.FillEllipse(new SolidBrush(colors[songs[i].genre]), new Rectangle(x - 5, y - 5, 11, 11));
                }
                if(curX >= 0 && curY >= 0)
                    graphics.FillEllipse(new SolidBrush(Color.White), new Rectangle(curX - 5, curY - 5, 11, 11));
                graphics.DrawImage(bmp, 0, 0);
            }

            pictureBox.Image = bmp;
        }

        private void comboBoxSelected_SelectedIndexChanged(object sender, EventArgs e)
        {
            Song curSong = newSongs[comboBoxSelected.SelectedIndex];

            minOnsetCur = Math.Min(minOnset, curSong.onset);
            maxOnsetCur = Math.Max(maxOnset, curSong.onset);
            minBeatCur = Math.Min(minBeat, curSong.beat);
            maxBeatCur = Math.Max(maxBeat, curSong.beat);

            Repaint((int)Math.Round((curSong.onset - minOnsetCur) / (maxOnsetCur - minOnsetCur) * (picSize - 1)),
                (int)Math.Round((curSong.beat - minBeatCur) / (maxBeatCur - minBeatCur) * (picSize - 1)));
        }
    }
}
