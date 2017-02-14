@extends ('layouts.master')

@section ('content')
    <div>
        @foreach ($books as $book)
            @include ('books.book')
        @endforeach
    </div>
@endsection
