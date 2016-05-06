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
    class PopLibraryDataManager : SQLiteAsyncConnection
    {
        /// <summary>
        /// The constructor for the database
        /// </summary>
        /// <param name="path"></param>
        public PopLibraryDataManager(string path) : base(path)
        {
            CreateTableAsync<Student>();
            CreateTableAsync<Book>();
            CreateTableAsync<Loan>();
        }

        /// <summary>
        /// Returns a list of loans out for a book id
        /// </summary>
        /// <param name="book"></param>
        /// <returns></returns>
        public IEnumerable<Loan> QueryLoanByBookId(Book book)
        {
            return (IEnumerable<Loan>) Table<Loan>().Where(x => x.BookId == book.BookId);
        }

        /// <summary>
        /// Returns the entire database of books
        /// </summary>
        /// <returns></returns>
        public IEnumerable<Book> QueryAllBooks()
        {
            return (IEnumerable<Book>) (from b in Table<Book>()
                   orderby b.Title
                   select b);
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
