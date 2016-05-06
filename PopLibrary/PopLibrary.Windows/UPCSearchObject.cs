using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PopLibrary
{
    class UPCSearchObject
    {
        public bool valid { get; set; }
        public string number { get; set; }
        public string itemname { get; set; }
        public string alias { get; set; }
        public string description { get; set; }
        public string avg_price { get; set; }
        public int rate_up { get; set; }
        public int rate_down { get; set; }

        public string ToString()
        {
            if (valid)
            {
                return "UPC: " + number +
                       "\nLabel: " + itemname;
            }
            else
            {
                return "Could not find this product";
            }
        }
    }
}
