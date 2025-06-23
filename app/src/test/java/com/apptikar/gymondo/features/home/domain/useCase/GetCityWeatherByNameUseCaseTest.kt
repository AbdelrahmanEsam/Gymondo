package com.apptikar.gymondo.features.home.domain.useCase

import com.apptikar.gymondo.core.utils.network.NetworkError
import com.apptikar.gymondo.core.utils.network.onError
import com.apptikar.gymondo.core.utils.network.onSuccess
import com.apptikar.gymondo.features.home.data.dto.response.CityWeatherResponseDto
import com.apptikar.gymondo.features.home.data.dto.response.ForecastDayDto
import com.apptikar.gymondo.features.home.data.dto.response.ForecastDto
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
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class GetCityWeatherByNameUseCaseTest {

    val listOfWeatherDto: MutableList<CityWeatherResponseDto> = mutableListOf()
    val mapStore: MutableMap<String, String> = mutableMapOf()

    lateinit var homeRepository: HomeRepository
    lateinit var getCityWeatherByNameUseCase: GetCityWeatherByNameUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mainDispatcherRule = DispatcherTestingRule(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        initListWeather()
        mapStore["cityName"] = "berlin"
        homeRepository = FakeHomeRepositoryImpl(listOfWeatherDto, mapStore)
        getCityWeatherByNameUseCase = GetCityWeatherByNameUseCase(homeRepository)
    }


    private fun initListWeather() {

        val cairoLocationDto = mockk<LocationDto>(relaxed = true)
        val cairoForecastDto = mockk<ForecastDto>(relaxed = true)

        every { cairoLocationDto.name } returns "berlin"
        addForeCastDays(forecastDay = cairoForecastDto)


        var cairoCityWeather = mockk<CityWeatherResponseDto>(relaxed = true)
        every { cairoCityWeather.locationDto } returns cairoLocationDto
        every { cairoCityWeather.forecastDto } returns cairoForecastDto


        listOfWeatherDto.add(cairoCityWeather)
    }


    private fun addForeCastDays(forecastDay : ForecastDto)
    {
        val forecastDayList = mutableListOf<ForecastDayDto>()
        repeat(10)
        {
            forecastDayList.add(mockk<ForecastDayDto>(relaxed = true))
        }

        every { forecastDay.forecastDay } returns forecastDayList

    }


    @Test
    fun `GetCityWeatherByNameUseCase with valid city name and number should return a weather response match the city and the forecast days`() = runTest {

        val getCityWeatherByNameFlow = getCityWeatherByNameUseCase.execute("berlin",3)

        backgroundScope.launch(testDispatcher) {
            getCityWeatherByNameFlow.collect { cityName ->
                cityName.onSuccess { weatherResponse ->
                    assertEquals(weatherResponse.location.name, "berlin")
                    assertEquals(weatherResponse.forecast.forecastDay.size , 3)
                }.onError { error ->
                    assert(error is NetworkError.NotFound)
                }
            }
        }
    }


    @Test
    fun `GetCityWeatherByNameUseCase with valid city name and minus number should return a weather response match the city and only one day`() = runTest {

        val getCityWeatherByNameFlow = getCityWeatherByNameUseCase.execute("berlin",-3)

        backgroundScope.launch(testDispatcher) {
            getCityWeatherByNameFlow.collect { cityName ->
                cityName.onSuccess { weatherResponse ->
                    assertEquals(weatherResponse.location.name , "berlin")
                    assertEquals(weatherResponse.forecast.forecastDay.size , 1)
                }.onError { error ->
                    assert(error is NetworkError.NotFound)
                }
            }
        }
    }


    @Test
    fun `GetCityWeatherByNameUseCase with unValid city name and minus number should return a weather response match the city and only one day`() = runTest {

        val getCityWeatherByNameFlow = getCityWeatherByNameUseCase.execute("tokyo",-3)

        backgroundScope.launch(testDispatcher) {
            getCityWeatherByNameFlow.collect { cityName ->
                cityName.onSuccess { weatherResponse ->
                    assertEquals(weatherResponse.location.name , "berlin")
                    assertEquals(weatherResponse.forecast.forecastDay.size , 1)
                }.onError { error ->
                    assert(error is NetworkError.NotFound)
                }
            }
        }
    }

    @Test
    fun `GetCityWeatherByNameUseCase with empty city name and minus number should return a weather response match the city and only one day`() = runTest {

        val getCityWeatherByNameFlow = getCityWeatherByNameUseCase.execute("",-3)

        backgroundScope.launch(testDispatcher) {
            getCityWeatherByNameFlow.collect { cityName ->
                cityName.onError { error ->
                    assertTrue(error is NetworkError.NotFound, "Expected NetworkError.NotFound but got ${error::class.simpleName}")
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