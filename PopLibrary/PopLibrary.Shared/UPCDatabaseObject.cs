using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PopLibrary
{
    /// <summary>
    /// UPCSearchObject is a temporary object for deserializing incoming
    /// data from the UPC code searches
    /// </summary>
    class UPCDatabaseObject
    {
        public bool valid { get; set; }
        public string reason { get; set; }
        public string number { get; set; }
        public string itemname { get; set; }
        public string alias { get; set; }
        public string description { get; set; }
        public string avg_price { get; set; }
        public int rate_up { get; set; }
        public int rate_down { get; set; }

        /// <summary>
        /// Provides the UPC search as a string
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            if (valid)
            {
                string formatString = "UPC: {0}\nLabel: {1}\nDescription: {2}";
                return string.Format(formatString, number, itemname, description);
            }
            else
            {
                return reason;
            }
        }
    }
}
