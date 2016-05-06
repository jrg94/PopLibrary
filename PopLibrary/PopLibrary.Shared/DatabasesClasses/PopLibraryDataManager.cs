using System;
using System.Collections.Generic;
using System.Text;
using SQLite;
using PopLibrary.DatabasesClasses.Model;

namespace PopLibrary.DatabasesClasses
{
    /// <summary>
    /// Handles all data management with the database
    /// </summary>
    class PopLibraryDataManager
    {
        public async void InitializeDatabase()
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");
            await conn.CreateTableAsync<User>();
        }

        public async void AddUser(string email)
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");

            User newUser = new User()
            {
                Email = email
            };

            await conn.InsertAsync(newUser);
        }

        public void AddBook()
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");
        }
    }
}
