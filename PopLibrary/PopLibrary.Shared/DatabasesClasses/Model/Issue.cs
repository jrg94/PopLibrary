using System;
using System.Collections.Generic;
using System.Text;
using SQLite;

namespace PopLibrary.DatabasesClasses.Model
{
    [Table("Issues")]
    class Issue
    {
        [PrimaryKey, AutoIncrement]
        public int IssueId { get; set; }

        public DateTime IssueDate { get; set; }

        public int BookId { get; set; }

        public int StudentId { get; set; }
    }
}
