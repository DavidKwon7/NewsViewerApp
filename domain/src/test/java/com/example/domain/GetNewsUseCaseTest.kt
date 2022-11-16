package com.example.domain

import com.example.domain.repository.LocalRepository
import com.example.domain.usecase.GetNewsUseCase
import com.example.domain.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class GetNewsUseCaseTest {

    @MockK
    private lateinit var localRepository: LocalRepository
    private lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun set_up() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getNewsUseCase = GetNewsUseCase(localRepository)
    }

    @Test
    fun test_get_news_success() = runTest {

        val newsData = TestDataGenerator.generateArticle()

        coEvery { localRepository.getAllNews() } returns newsData

        val result = getNewsUseCase.invoke()
        result.let { newsData ->
            Truth.assertThat(newsData).isEqualTo(newsData)
        }

        coVerify { localRepository.getAllNews() }
    }

    @Test(expected = java.lang.Exception::class)
    fun test_get_news_fail() = runTest {

        val newsData = TestDataGenerator.generateArticle()

        coEvery { localRepository.getAllNews() } throws java.lang.Exception()

        getNewsUseCase.invoke()

        coVerify { localRepository.getAllNews() }
    }
}