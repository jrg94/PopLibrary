package com.therenegadecoder.poplibrary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Book::class], version = 3)
@TypeConverters(Converters::class)
abstract class BookRoomDatabase : RoomDatabase() {
    abstract fun bookDAO(): BookDAO

    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BookRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "book_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(BookDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class BookDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        /**
         * Generates an array of books.
         */
        private fun generateBooks(): MutableList<Book> {
            return mutableListOf(
                Book(isbn13 = "978-0743273565", title = "The Great Gatsby", pageCount = 180, editor = "Scribner", author = "F. Scott Fitzgerald", coverImageURL = "http://ecx.images-amazon.com/images/I/41eiFf1x23L._SL160_.jpg", lexileLevel = Lexile(1070, Lexile.LexileType.NA)),
                Book(isbn13 = "978-0143039433", title = "The Grapes of Wrath", pageCount = 464, editor = "Penguin Classics", author = "John Steinbeck", coverImageURL = "http://ecx.images-amazon.com/images/I/41adOkkXUzL._SL160_.jpg"),
                Book(isbn13 = "978-0452262935", title = "Nineteen Eighty-Four", pageCount = 304, editor = "Berkley", author = "George Orwell", coverImageURL = "http://ecx.images-amazon.com/images/I/41Kv1qGuXUL._SL160_.jpg"),
                Book(isbn13 = "978-1613823590", title = "Ulysses", pageCount = 564, editor = "Simon & Brown", author = "James Joyce", coverImageURL = "http://ecx.images-amazon.com/images/I/51XEH13NOnL._SL200_.jpg"),
                Book(isbn13 = "978-0316769174", title = "The Catcher in the Rye", pageCount = 277, editor = "Back Bay Books", author = "J. D. Salinger", coverImageURL = "http://ecx.images-amazon.com/images/I/511BDFArolL._SL200_.jpg"),
                Book(isbn13 = "978-0061743528", title = "To Kill a Mockingbird", pageCount = 323, editor = "Harper", author = "Harper Lee", coverImageURL = "http://ecx.images-amazon.com/images/I/51KFyfyK7eL._SL200_.jpg"),
                Book(isbn13 = "978-0544003415", title = "The Lord of the Rings", pageCount = 1285, editor = "Mariner Books", author = "J. R. R. Tolkien", coverImageURL = "http://ecx.images-amazon.com/images/I/516GyHY9p6L._SL200_.jpg"),
                Book(isbn13 = "978-0061767647", title = "Brave New World", pageCount = 384, editor = "Harper", author = "Aldous Huxley", coverImageURL = "http://ecx.images-amazon.com/images/I/41kwa0ECKKL._SL200_.jpg")
            )
        }

        /**
         * A handy function for populating the database with books.
         */
        suspend fun populateDatabase(bookDAO: BookDAO) {
            bookDAO.deleteAll()

            for (book: Book in generateBooks()) {
                bookDAO.insert(book)
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.bookDAO())
                }
            }
        }
    }


}