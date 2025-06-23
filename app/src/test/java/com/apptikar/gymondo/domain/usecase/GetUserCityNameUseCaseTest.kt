package com.apptikar.gymondo.domain.usecase

import com.apptikar.gymondo.core.utils.network.LocalError
import com.apptikar.gymondo.core.utils.network.onError
import com.apptikar.gymondo.core.utils.network.onSuccess
import com.apptikar.gymondo.domain.usecases.GetUserCityNameUseCase
import com.apptikar.gymondo.features.home.data.dto.response.CityWeatherResponseDto
import com.apptikar.gymondo.features.home.data.dto.response.LocationDto
import com.apptikar.gymondo.features.home.data.repository.FakeHomeRepositoryImpl
import com.apptikar.gymondo.features.home.data.utils.DispatcherTestingRule
import com.apptikar.gymondo.features.home.domain.repository.HomeRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class GetUserCityNameUseCaseTest {

    val listOfWeatherDto: MutableList<CityWeatherResponseDto> = mutableListOf()
    val mapStore: MutableMap<String, String> = mutableMapOf()
    lateinit var homeRepository: HomeRepository
    lateinit var getUserCityNameUseCase: GetUserCityNameUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()


    @get:Rule
    val mainDispatcherRule = DispatcherTestingRule(testDispatcher)

    @Before
    fun setUp() {
        initListWeather()
        mapStore["cityName"] = "berlin"
        homeRepository = FakeHomeRepositoryImpl(listOfWeatherDto, mapStore)
        getUserCityNameUseCase = GetUserCityNameUseCase(homeRepository)

    }

    private fun initListWeather() {
        val cairoLocationDto = mockk<LocationDto>(relaxed = true)
        every { cairoLocationDto.name } returns "cairo"

        var cairoCityWeather = mockk<CityWeatherResponseDto>(relaxed = true)
        every { cairoCityWeather.locationDto } returns cairoLocationDto

        listOfWeatherDto.add(cairoCityWeather)
    }


    @Test
    fun `getUserCityNameUseCase with valid key return a valid city name`() = runTest {

        val getCityNameFlow = homeRepository.getCityName("cityName")

        backgroundScope.launch(testDispatcher) {
            getCityNameFlow.collect { cityName ->
                cityName.onSuccess { city ->
                    assertEquals(city,"berlin")
                }.onError { error ->
                    assertTrue(error is LocalError.DataStoreError, "Expected LocalError.DataStoreError but got ${error::class.simpleName}")
                }
            }
        }
    }

    @Test
    fun `getUserCityNameUseCase with unValid key return DataStoreError`() = runTest {

        val getCityNameFlow = homeRepository.getCityName("anything")
        backgroundScope.launch(testDispatcher) {
            getCityNameFlow.collect { cityName ->
                cityName.onSuccess { city ->
                    assertNotEquals(city,"berlin")
                }.onError { error ->
                    assertTrue(error is LocalError.DataStoreError, "Expected LocalError.DataStoreError but got ${error::class.simpleName}")
                }
            }
        }
    }

    @Test
    fun `getUserCityNameUseCase with empty key return a DataStoreError`() = runTest {

        val getCityNameFlow = homeRepository.getCityName("")

        backgroundScope.launch(testDispatcher) {
            getCityNameFlow.collect { cityName ->
                cityName.onSuccess { city ->
                    assertNotEquals(city,"berlin")
                }.onError { error ->
                    assertTrue(error is LocalError.DataStoreError, "Expected LocalError.DataStoreError but got ${error::class.simpleName}")
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