using System;
using System.Collections.Generic;
using System.Text;
using SQLite;

namespace PopLibrary.DatabasesClasses.Model
{
    [Table("Users")]
    class User
    {
        [PrimaryKey, AutoIncrement]
        public int Id { get; set; }

        public string Email { get; set; }
    }
}
