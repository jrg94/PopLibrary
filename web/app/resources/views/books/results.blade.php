@extends ('layouts.app')

@section ('content')
    <div>
        @foreach ($results as $result)
            @echo $result->Title
        @endforeach
    </div>
@endsection
