<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Book;

class BooksController extends Controller
{
    public function index()
    {
        $books = Book::latest()->get();

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
            'isbn' => 'required'
        ]);

        Book::create(request(['title', 'isbn']));

        return redirect('/books');
    }
}