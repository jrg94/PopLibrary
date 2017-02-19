@extends ('layouts.app')

@section ('content')
    <div>
        @foreach ($books as $book)
            @include ('books.book')
        @endforeach
    </div>
@endsection
