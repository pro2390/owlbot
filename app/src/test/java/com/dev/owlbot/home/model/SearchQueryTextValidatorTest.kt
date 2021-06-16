package com.dev.owlbot.home.model

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

import org.junit.Test


class SearchQueryTextValidatorTest : TestCase() {

    private val validator = SearchQueryTextValidator()

    @Test
    fun testEmptyInvalid() {
        val result = validator.validateQuery("")
        assertThat(result).isFalse()
    }

    @Test
    fun testLengthInvalid() {
        val result = validator.validateQuery("o")
        assertThat(result).isFalse()
    }

    @Test
    fun testValidQuery() {
        val result = validator.validateQuery("bird")
        assertThat(result).isTrue()
    }

    @Test
    fun testValidLengthQuery() {
        val result = validator.validateQuery("to")
        assertThat(result).isTrue()
    }

}