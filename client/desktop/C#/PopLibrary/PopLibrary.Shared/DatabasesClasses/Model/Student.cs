using System;
using System.Collections.Generic;
using System.Text;
using SQLite;

namespace PopLibrary.DatabasesClasses.Model
{
    [Table("Students")]
    class Student
    {
        [PrimaryKey, AutoIncrement]
        public int StudentId { get; set; }

        public string Name { get; set; }

        public override string ToString()
        {
            return string.Format("Student:{0}", Name);
        }
    }
}
