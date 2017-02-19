@extends ('layouts.app')

@section ('library')
    <div>
        @foreach ($books as $book)
            @include ('books.book')
        @endforeach
    </div>
@endsection
