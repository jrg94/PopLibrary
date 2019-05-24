<div class="col-md-3">
    <div class="panel-heading">
        <a href="/books/{{ $book->id }}">
            {{ $book->title }}
        </a>
    </div>
    <div class="panel-body">
        <ul>
            <li>ISBN: {{ $book->isbn }}</li>
            <li>ASIN: {{ $book->asin }}</li>
            <li>Timestamp: {{ $book->created_at->toFormattedDateString() }}</li>
        </ul>
    </div>
</div>
