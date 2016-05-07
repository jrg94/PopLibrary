using System;
using System.Collections.Generic;
using System.Text;

namespace PopLibrary
{
    class DigitEyesObject
    {
        public string upc_code { get; set; }
        public string description { get; set; }
        public string uom { get; set; }
        public string brand { get; set; }
        public string language { get; set; }
        public string ingredients { get; set; }
        public string nutrition { get; set; }
        public string website { get; set; }
        public string product_web_page { get; set; }
        public string image { get; set; }
        public Thumbnail thumnbnail { get; set; }
        public Manufacturer manufacturer { get; set; } 
        public GCP gcp { get; set; }
        public string categories { get; set; }

        public struct Thumbnail
        {
            public string thumbnail_url { get; set; }
            public string thumbnail_width { get; set; }
            public string thumbnail_height { get; set; }
        }
        
        public struct Manufacturer
        {
            public string name { get; set; }
            public string address { get; set; }
            public string address2 { get; set; }
            public string city { get; set; }
            public string state { get; set; }
            public string country { get; set; }
            public string postal_code { get; set; }
            public string contact { get; set; }
            public string phone { get; set; }
        }

        public struct GCP
        {
            public string company { get; set; }
            public string address { get; set; }
            public string address2 { get; set; }
            public string city { get; set; }
            public string state { get; set; }
            public string country { get; set; }
            public string postal_code { get; set; }
            public string contact { get; set; }
            public string phone { get; set; }
            public string fax { get; set; }
        }
    }
}
