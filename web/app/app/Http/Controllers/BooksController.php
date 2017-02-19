<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Book;
use Auth;
use Amazon;

class BooksController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth');
    }

    public function index()
    {
        $books = Auth::user()->books;

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

        // Creates the book
        $book = Book::create([
            'title' => request('title'),
            'isbn' => request('isbn'),
            'asin' => request('asin')
        ]);

        $book->users()->attach(Auth::user()->id);

        return redirect('/');
    }

    public function search()
    {
        $amazon = new Amazon(getenv('AMAZON_ACCESS_KEY'), 'US', getenv('AMAZON_SECRET_KEY'));
        $results = $amazon->itemSearch(array('SearchIndex' => 'Books',
                                    'AssociateTag' => getenv('AMAZON_ASSOCIATE_TAG')));

        foreach ($results as $result)
        {
            echo $result->Title . '<br />';
        }
    }
}
