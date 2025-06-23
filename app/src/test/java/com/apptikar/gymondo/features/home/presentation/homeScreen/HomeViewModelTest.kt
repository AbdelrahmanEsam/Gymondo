package com.apptikar.gymondo.features.home.presentation.homeScreen

import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.domain.usecases.GetUserCityNameUseCase
import com.apptikar.gymondo.features.home.data.utils.DispatcherTestingRule
import com.apptikar.gymondo.features.home.domain.model.response.CityWeatherResponseModel
import com.apptikar.gymondo.features.home.domain.model.response.CurrentModel
import com.apptikar.gymondo.features.home.domain.useCase.GetCityWeatherByNameUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var getCityWeatherByNameUseCase: GetCityWeatherByNameUseCase
    private lateinit var getUserCityNameUseCase: GetUserCityNameUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mainDispatcherRule = DispatcherTestingRule(testDispatcher)

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        clearAllMocks()

        getCityWeatherByNameUseCase = mockk<GetCityWeatherByNameUseCase>(relaxed = true)
        getUserCityNameUseCase = mockk<GetUserCityNameUseCase>(relaxed = true)

        homeViewModel =
            HomeViewModel(testDispatcher, getCityWeatherByNameUseCase, getUserCityNameUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getCityWeatherByNameUseCase with valid city and numberOfDays return the right temp`() =
        runTest {
            val cityWeatherResponseModel = mockk<CityWeatherResponseModel>(relaxed = true)
            val currentWeatherModel = mockk<CurrentModel>(relaxed = true)
            every { currentWeatherModel.tempC } returns 55.0
            every { cityWeatherResponseModel.currentWeather } returns currentWeatherModel
            coEvery {
                getCityWeatherByNameUseCase.execute(
                    "cairo",
                    any()
                )
            } returns flowOf(Result.Success(cityWeatherResponseModel))

            homeViewModel.onEvent(HomeIntent.GetCityWeatherByName("cairo"))
            advanceUntilIdle()

            assertEquals(homeViewModel.state.value.temperature, 55.0)


            coVerify {
                getCityWeatherByNameUseCase.execute("cairo", any())
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getUserCityNameUseCase with valid key returns a valid city name`() = runTest {
        coEvery { getUserCityNameUseCase.execute("city") } returns flowOf(
            Result.Success("berlin")
        )

        homeViewModel.onEvent(HomeIntent.GetCityByName("city"))
        advanceUntilIdle()

        assertEquals(homeViewModel.state.value.cityName, "berlin")
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}