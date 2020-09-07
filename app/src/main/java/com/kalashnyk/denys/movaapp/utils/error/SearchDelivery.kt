package com.kalashnyk.denys.movaapp.utils.error

interface SearchDelivery {
    fun deliverError(t: Throwable)
    fun deliverResult(id: String?)
}