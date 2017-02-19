@extends('layouts.app')

@section('dashboard')

    <div class="panel-heading">Filters</div>
    <div class="panel-body">
        All
        <hr>
        @include ('books.form.add')
        <hr>
        @include ('books.form.search')
    </div>

@endsection

@section('library')

    <div class="panel-heading">Library</div>

        <div class="panel-body">

            @foreach ($books as $book)
                @include ('books.cover')
            @endforeach

        </div>
    </div>

@endsection
