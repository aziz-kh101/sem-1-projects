using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VehiculeProject.models
{
    public class Vehicule
    {
        public int Id { get; set; }

        public string Matricule { get; set; }
        public string Brand { get; set; }
        public string Model { get; set; }
        public int Year { get; set; }
        public Vehicule() { }
    }
}
