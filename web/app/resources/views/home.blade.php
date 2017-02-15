@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">Dashboard</div>

                <div class="panel-body">
                    You are logged in!

                    <hr>

                    @foreach ($books as $book)
                        @include ('books.book')
                    @endforeach

                    <hr>

                    <form method="POST" action="/books">

                        {{ csrf_field() }}

                        <div>
                            <label for="title">Title:</label>
                            <input type="text" id="title" name="title" required>
                        </div>

                        <div>
                             <label for="isbn">ISBN:</label>
                             <input type="text" id="isbn" name="isbn" required>
                        </div>

                        <div>
                            <label for="asin">ASIN:</label>
                            <input type="text" id="asin" name="asin" required>
                        </div>

                        <button type="submit">Submit</button>

                        @include ('layouts.errors')

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
@endsection
