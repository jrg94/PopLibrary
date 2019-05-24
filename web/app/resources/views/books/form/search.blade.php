<form method="POST" action="/search">

    {{ csrf_field() }}

    <div>
        <label for="search">Search:</label>
        <input type="text" id="search" name="search" required>
    </div>

    @include ('layouts.errors')

</form>
