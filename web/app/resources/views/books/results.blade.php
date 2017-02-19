@extends ('layouts.app')

@section ('library')
    <div>
        @foreach ($results as $result)
            @include('books.item')
        @endforeach
    </div>
@endsection
