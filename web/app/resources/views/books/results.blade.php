@extends ('layouts.poplibrary')

@section ('library')
    <div>
        @foreach ($results as $result)
            @include('books.item')
        @endforeach
    </div>
@endsection
