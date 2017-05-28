using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KNN_Music
{
    class SongComparer : IComparer
    {
        int IComparer.Compare(object o1, object o2)
        {
            Song t1 = o1 as Song;
            Song t2 = o2 as Song;
            if (t1 != null && t2 != null)
                return t1.distance.CompareTo(t2.distance);
            else
                throw new ArgumentException("Parameter is not a Song!");
        }
    }
}
