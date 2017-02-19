<div class="col-md-3">
    <div class="panel-heading">
        {{ $book->title }}
    </div>
    <div class="panel-body">
        <ul>
            <li>ISBN: {{ $book->isbn }}</li>
            <li>ASIN: {{ $book->asin }}</li>
            <li>Timestamp: {{ $book->created_at->toFormattedDateString() }}</li>
        </ul>
    </div>
</div>
