<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Loan extends Model
{

    /**
     * Retrieves the owner of this loan.
     */
    public function owner()
    {
        return $this->belongsTo(User::class, 'owner_id');
    }

    /**
     * Retrieves the borrow of this loan.
     */
    public function borrower()
    {
        return this->belongsTo(User::class, 'borrower_id');
    }
}
