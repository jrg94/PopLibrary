@extends ('layout')

@section ('content')
    <ul>
        @foreach ($books as $book)
            <li>{{ $book->title }}</li>
        @endforeach
    </ul>    
@endsection
