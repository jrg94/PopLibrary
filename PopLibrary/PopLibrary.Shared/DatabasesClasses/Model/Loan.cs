using System;
using System.Collections.Generic;
using System.Text;
using SQLite;

namespace PopLibrary.DatabasesClasses.Model
{
    [Table("Loans")]
    class Loan
    {
        [PrimaryKey, AutoIncrement]
        public int LoanId { get; set; }

        public int BookId { get; set; }

        public int StudentId { get; set; }

        public DateTime DateOut { get; set; }

        public DateTime DateDue { get; set; }

        public DateTime DateReturned { get; set; }

        public override string ToString()
        {
            return string.Format("Issued:{0}\nDue:{1}\nReturned:{2}", DateOut, DateDue, DateReturned);
        }
    }
}
