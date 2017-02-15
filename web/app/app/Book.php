<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Book extends Model
{
    // NOTE: This is risky
    protected $guarded = [];

    public function users()
    {
    	return $this->hasMany(User::class);
    }
}
