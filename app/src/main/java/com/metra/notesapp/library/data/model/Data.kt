package com.metra.notesapp.library.data.model

sealed class Data<out T> {

    data class Success<out T>(val value: T) : Data<T>() {
        override fun toString() = "Success($value)"
    }

    data class Error(
        val cause: Throwable
    ) : Data<Nothing>() {
        override fun toString() = "Error ($cause)"
    }

    fun getSuccessValueOrThrow(): T {
        return when (this) {
            is Error -> throw this.cause
            is Success -> this.value
        }
    }
}
