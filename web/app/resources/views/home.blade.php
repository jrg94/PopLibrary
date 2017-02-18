@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        <div class="col-md-3 no-float">
            <div class="panel panel-default">
                <div class="panel-heading">Filters</div>
                <div class="panel-body">
                    All
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Add a Book</div>
                <div class="panel-body">
                    @include ('books.add')
                </div>
            </div>
        </div>
        <div class="col-md-9 no-float">
            <div class="panel panel-default">
                <div class="panel-heading">Library</div>

                <div class="panel-body">

                    @foreach ($books as $book)
                        @include ('books.cover')
                    @endforeach

                </div>

            </div>
        </div>
    </div>


</div>
@endsection
