<?php

namespace App;

use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
    use Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name', 'email', 'password',
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'password', 'remember_token',
    ];

    /**
     * Retrieves any books this user might have.
     */
    public function books()
    {
        return $this->belongsToMany(Book::class);
    }

    /**
     * Retrieves all the loans associated with this user.
     * Query to retrieve all loans owned/borrowed.
     */
    public function loans()
    {
        return $this->hasMany(Loan::class);
    }
}
