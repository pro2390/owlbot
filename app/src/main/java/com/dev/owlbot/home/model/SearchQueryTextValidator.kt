package com.dev.owlbot.home.model

//Validation for search text entered
class SearchQueryTextValidator {
    companion object {
        const val EMPTY = ""
        const val MIN_QUERY_LENGTH = 2
    }

    fun validateQuery(queryText: String): Boolean {
        if (queryText == EMPTY || queryText.length < MIN_QUERY_LENGTH )
            return false
        return true
    }
}