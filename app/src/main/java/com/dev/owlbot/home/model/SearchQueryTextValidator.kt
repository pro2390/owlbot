package com.dev.owlbot.home.model

class SearchQueryTextValidator {
    companion object {
        val EMPTY = ""
        val MIN_QUERY_LENGTH = 2
    }

    fun validateQuery(queryText: String): Boolean {
        if (queryText == EMPTY || queryText.length < MIN_QUERY_LENGTH )
            return false
        return true
    }
}