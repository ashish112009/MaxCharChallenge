package com.codechallenge.maxcharoccurrence.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codechallenge.maxcharoccurrence.usecase.CalculateMaxCharOccurrenceUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private lateinit var useCase: CalculateMaxCharOccurrenceUseCase
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        useCase = mockk(relaxed = true)
    }

    @Test
    fun testCalculateMaxCharacterOccurrences_WithActualText() = runBlockingTest {
        val text = "abccc"
        val expectedResult = " c occurred maximum 3\n"
        viewModel = spyk(HomeViewModel(useCase))
        coEvery { useCase.run(text) } returns expectedResult
        viewModel.calculateMaxCharacterOccurrences(text)
        coVerify(exactly = 1) { useCase.run(text) }
        Assert.assertEquals(expectedResult, viewModel.liveMaxOccurrenceValue.value)
    }

    @Test
    fun testCalculateMaxCharacterOccurrences_WithEmptyText() = runBlockingTest {
        val text = ""
        val expectedResult = "Enter some text"
        viewModel = HomeViewModel(useCase)
        coEvery { useCase.run(text) } returns expectedResult
        viewModel.calculateMaxCharacterOccurrences(text)
        coVerify(exactly = 1) { useCase.run(text) }
        Assert.assertEquals(expectedResult, viewModel.liveMaxOccurrenceValue.value)
    }
}