﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KNN_Music
{
    class Song
    {
        public double onset;
        public double beat;
        public int genre;
        public double distance;

        public Song(double onset, double beat)
        {
            this.onset = onset;
            this.beat = beat;
        }

        public Song(double onset, double beat, int genre)
            : this(onset, beat) 
        { 
            this.genre = genre; 
        }

        public static IComparer SortByDistance
        { get { return (IComparer)new SongComparer(); } }

        public override string ToString()
        {
            return String.Format("onset: {0}\tbeat: {1}\tgenre: {2}\tdistance: {3}", onset, beat, genre, distance);
        }
    }
}
