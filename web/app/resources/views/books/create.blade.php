@extends ('layouts.master')

@section ('content')
    <h1>Create a Book</h1>

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
@endsection
