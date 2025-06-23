package com.apptikar.gymondo.features.home.data.repository

import com.apptikar.gymondo.core.utils.network.LocalError
import com.apptikar.gymondo.core.utils.network.NetworkError
import com.apptikar.gymondo.core.utils.network.onError
import com.apptikar.gymondo.core.utils.network.onSuccess
import com.apptikar.gymondo.features.home.data.dataSource.local.FakeHomeLocalDataSourceImpl
import com.apptikar.gymondo.features.home.data.dataSource.local.HomeLocalDataSource
import com.apptikar.gymondo.features.home.data.dataSource.remote.FakeHomeRemoteDataSourceImpl
import com.apptikar.gymondo.features.home.data.dataSource.remote.HomeRemoteDataSource
import com.apptikar.gymondo.features.home.data.dto.response.CityWeatherResponseDto
import com.apptikar.gymondo.features.home.data.dto.response.ForecastDto
import com.apptikar.gymondo.features.home.data.dto.response.LocationDto
import com.apptikar.gymondo.features.home.data.utils.DispatcherTestingRule
import com.apptikar.gymondo.features.home.domain.repository.HomeRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class HomeRepositoryImplTest {

    lateinit var homeRepository: HomeRepository
    lateinit var homeRemoteDataSource: HomeRemoteDataSource
    lateinit var homeLocalDataSource: HomeLocalDataSource
    val listOfWeatherDto: MutableList<CityWeatherResponseDto> = mutableListOf()
    val mapStore: MutableMap<String, String> = mutableMapOf()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mainDispatcherRule = DispatcherTestingRule(testDispatcher)


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        initListWeather()
        mapStore["cityName"] = "berlin"
        homeRemoteDataSource = FakeHomeRemoteDataSourceImpl(listOfWeatherDto)
        homeLocalDataSource = FakeHomeLocalDataSourceImpl(mapStore)
        homeRepository = HomeRepositoryImpl(homeRemoteDataSource, homeLocalDataSource)
    }

    private fun initListWeather() {

        val cairoLocationDto = mockk<LocationDto>(relaxed = true)
        val cairoForecastDto = mockk<ForecastDto>(relaxed = true)
        every { cairoLocationDto.name } returns "cairo"


        var cairoCityWeather = mockk<CityWeatherResponseDto>(relaxed = true)
        every { cairoCityWeather.locationDto } returns cairoLocationDto
        every { cairoCityWeather.forecastDto } returns cairoForecastDto


        listOfWeatherDto.add(cairoCityWeather)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get city by valid name from remote should return the city that matches the name`() =
        runTest {

            val getCityFlow = homeRepository.getCityWeatherByName("cairo", numberOfDays = 1)

            backgroundScope.launch(testDispatcher) {
                getCityFlow.collect { cityResponse ->
                    cityResponse.onSuccess { city ->
                        assertEquals(city.location.name,"cairo")
                    }.onError { error ->
                        fail("Forced failure for testing")
                    }
                }
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get city by unValid name from remote should return not found Error`() = runTest {

        val getCityFlow = homeRepository.getCityWeatherByName("tokyo", numberOfDays = 1)

        backgroundScope.launch(testDispatcher) {
            getCityFlow.collect { cityResponse ->
                cityResponse.onSuccess { city ->
                    assertEquals(city.location.name,"tokyo")
                }.onError { error ->
                    assertTrue(error is NetworkError.NotFound, "Expected NetworkError.NotFound but got ${error::class.simpleName}")
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get city by empty name from remote should  return not found Error`() = runTest {

        val getCityFlow = homeRepository.getCityWeatherByName("", numberOfDays = 1)

        backgroundScope.launch(testDispatcher) {
            getCityFlow.collect { cityResponse ->
                cityResponse.onSuccess { city ->
                    assertEquals(city.location.name,"")
                }.onError { error ->
                    assertTrue(error is NetworkError.NotFound, "Expected NetworkError.NotFound but got ${error::class.simpleName}")
                }
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get city name from local with valid key should return the current value`() = runTest {

        val getCityNameFlow = homeRepository.getCityName("cityName")

        backgroundScope.launch(testDispatcher) {
            getCityNameFlow.collect { cityName ->
                cityName.onSuccess { city ->
                    assertEquals(city,"berlin")
                }.onError { error ->
                    assertTrue(error is LocalError.DataStoreError, "Expected NetworkError.DataStoreError but got ${error::class.simpleName}")
                }
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get city name from local with unValid key should return the current value`() = runTest {

        val getCityNameFlow = homeRepository.getCityName("city")
        advanceUntilIdle()

        backgroundScope.launch(testDispatcher) {
            getCityNameFlow.collect { cityName ->
                cityName.onSuccess { city ->
                    assertNotEquals(city,"berlin")
                }.onError { error ->
                    assertTrue(error is LocalError.DataStoreError, "Expected NetworkError.DataStoreError but got ${error::class.simpleName}")
                }
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get city name from local with empty key should return the current value`() = runTest {

        val getCityNameFlow = homeRepository.getCityName("")
        advanceUntilIdle()

        backgroundScope.launch(testDispatcher) {
            getCityNameFlow.collect { cityName ->
                cityName.onSuccess { city ->
                    assertNotEquals(city,"berlin")
                }.onError { error ->
                    assertTrue(error is LocalError.DataStoreError, "Expected NetworkError.DataStoreError but got ${error::class.simpleName}")
                }
            }
        }
    }


    @After
    fun tearDown() {
        listOfWeatherDto.clear()
        mapStore.clear()
    }
}