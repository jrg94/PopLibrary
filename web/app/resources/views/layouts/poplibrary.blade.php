@extends ('layouts.app')

@section ('content')
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="panel panel-default">
                    @yield('dashboard')
                </div>
            </div>
            <div class="col-md-9">
                <div class="panel panel-default">
                    @yield('library')
                </div>
            </div>
        </div>
    </div>
@endsection
