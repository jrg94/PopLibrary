<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Book;

class BooksController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth');
    }

    public function index()
    {
        $books = Book::all();

        return view('books.index', compact('books'));
    }

    public function show(Book $book)
    {
        return view('books.show', compact('book'));
    }

    public function create()
    {
        return view('books.create');
    }

    public function store()
    {
        $this->validate(request(), [
            'title' => 'required',
            'isbn' => 'required',
            'asin' => 'required'
        ]);

        // I DON"T GET IT
        $book = Book::create([
            'title' => request('title'),
            'isbn' => request('isbn'),
            'asin' => request('asin')
        ]);

        $book->users()->attach(auth()->id);

        return redirect('/books');
    }
}
