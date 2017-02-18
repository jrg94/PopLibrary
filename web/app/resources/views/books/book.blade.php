<div class="col-md-3">
    <h1>
        <a href="/books/{{ $book->id }}">
            {{ $book->title }}
        </a>
    </h1>
    <p>
        <ul>
            <li>ISBN: {{ $book->isbn }}</li>
            <li>ASIN: {{ $book->asin }}</li>
            <li>Timestamp: {{ $book->created_at->toFormattedDateString() }}</li>
        </ul>
    </p>
</div>
