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
        /// <summary>
        /// A function for creating a new database
        /// </summary>
        public async void InitializeDatabase()
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");
            await conn.CreateTableAsync<Student>();
            await conn.CreateTableAsync<Book>();
            await conn.CreateTableAsync<Issue>();
            await conn.CreateTableAsync<Return>();
        }

        /// <summary>
        /// A function for adding new users to the database
        /// </summary>
        /// <param name="email"></param>
        public async void AddStudent(string name)
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");

            Student newStudent = new Student()
            {
                Name = name
            };

            await conn.InsertAsync(newStudent);
        }

        /// <summary>
        /// A function for adding a book to the database
        /// </summary>
        public async void AddBook(string title, string isbn, string author)
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");

            Book newBook = new Book()
            {
                Title = title,
                ISBN = isbn,
                Author = author
            };

            await conn.InsertAsync(newBook);
        }
    }
}
