using System;
using System.Collections.Generic;
using System.Text;
using SQLite;

namespace PopLibrary.DatabasesClasses.Model
{
    [Table("Books")]
    class Book
    {
        [AutoIncrement]
        public int Id { get; set; }

        public string Title { get; set; }

        public string ISBN { get; set; }

        public string Author { get; set; }
    }
}
