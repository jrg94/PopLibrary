using System;
using System.Collections.Generic;
using System.Text;
using SQLite;

namespace PopLibrary.DatabasesClasses.Model
{
    [Table("Returns")]
    class Return
    {
        [PrimaryKey, AutoIncrement]
        public int ReturnId { get; set; }

        public DateTime ReturnDate { get; set; }

        public int BookId { get; set; }
    }
}
