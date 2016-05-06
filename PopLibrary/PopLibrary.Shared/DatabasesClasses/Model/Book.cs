using System;
using System.Collections.Generic;
using System.Text;
using SQLite;

namespace PopLibrary.DatabasesClasses.Model
{
    /// <summary>
    /// A book entry will contain all the information for
    /// that book such as the title, the author, the isbn,
    /// and the number of copies.
    /// </summary>
    [Table("Books")]
    class Book
    {
        [PrimaryKey, AutoIncrement]
        public int BookId { get; set; }

        public string Title { get; set; }

        public string ISBN { get; set; }

        public string Author { get; set; }

        public int NumberOfCopies { get; set; }

        public override string ToString()
        {
            return string.Format("Title:{0}\nAuthor:{1}\nISBN:{2}\nCopies:{3}", Title, Author, ISBN, NumberOfCopies);
        }
    }
}
