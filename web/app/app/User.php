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
     * Retrieves all the loans this user has lent.
     * TODO: Come up with a name that makes sense.
     */
    public function loans()
    {
        return $this->hasMany(Loan::class, 'user_id', 'id');
    }

    /**
     * Retrieves all the loans this user has received.
     * TODO: Come up with a name that makes sense.
     */
    public function borrows()
    {
        return $this->hasMany(Loan::class, 'borrower_id', 'id');
    }
}
