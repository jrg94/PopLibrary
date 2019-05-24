<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/books', 'BooksController@index');
Route::get('/books/{book}', 'BooksController@show')->where('book', '[0-9]+');
Route::post('/books', 'BooksController@store');
Route::post('/search', 'BooksController@search');

Auth::routes();

Route::get('/', 'HomeController@index');
