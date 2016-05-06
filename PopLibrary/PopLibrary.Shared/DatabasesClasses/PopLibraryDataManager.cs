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
            await conn.CreateTableAsync<Loan>();
        }

        /// <summary>
        /// Handles behavior when a student wants to borrow a book
        /// </summary>
        /// <param name="student"></param>
        /// <param name="book"></param>
        /// <param name="loan"></param>
        public async void LoanBook(Student student, Loan loan)
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");
            await conn.InsertAsync(student);
            await conn.InsertAsync(loan);
        }

        /// <summary>
        /// Handles behavior when a student returns a book
        /// </summary>
        /// <param name="loan"></param>
        public async void ReturnBook(Loan loan)
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");
            await conn.UpdateAsync(loan);
        }

        /// <summary>
        /// A function for adding a book to the database
        /// </summary>
        public async void AddBook(Book book)
        {
            SQLiteAsyncConnection conn = new SQLiteAsyncConnection("PopLibrary.db");
            await conn.InsertAsync(book);
        }
    }
}
