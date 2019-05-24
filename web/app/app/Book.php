<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Book extends Model
{
    // NOTE: This is risky
    protected $guarded = [];

    /**
     * Retrieves all users who have a copy of this book.
     */
    public function users()
    {
    	return $this->belongsToMany(User::class);
    }

    /**
     * Retrieves all loans for this book.
     */
    public function loans()
    {
        return $this->hasMany(Loan::class);
    }
}
