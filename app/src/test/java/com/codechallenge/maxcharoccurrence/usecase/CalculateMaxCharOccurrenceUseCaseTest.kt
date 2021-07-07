package com.codechallenge.maxcharoccurrence.usecase

import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CalculateMaxCharOccurrenceUseCaseTest {

    private lateinit var useCase: CalculateMaxCharOccurrenceUseCase

    @Before
    fun setup() {
        useCase = spyk(CalculateMaxCharOccurrenceUseCase())
    }

    @Test
    fun testExecute_multipleMaxValues() = runBlockingTest {
        val text = "aabbcc"
        val expectedResult = " a occurred maximum 2\n b occurred maximum 2\n c occurred maximum 2\n"

        val result = useCase.execute(text)

        coVerify(exactly = 1) { useCase.calculateMaxOccurrences(text) }
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun testExecute_singleMaxValue() = runBlockingTest {
        val text = "abccc"
        val expectedResult = " c occurred maximum 3\n"

        val result = useCase.execute(text)

        coVerify(exactly = 1) { useCase.calculateMaxOccurrences(text) }
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun testExecute_emptyValue() = runBlockingTest {
        val text = ""
        val expectedResult = "Enter some text"

        val result = useCase.execute(text)

        coVerify(exactly = 0) { useCase.calculateMaxOccurrences(text) }
        Assert.assertEquals(expectedResult, result)
    }
}