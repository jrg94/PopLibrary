@extends ('layouts.poplibrary')

@section ('library')
    <div>
        @foreach ($books as $book)
            @include ('books.book')
        @endforeach
    </div>
@endsection
