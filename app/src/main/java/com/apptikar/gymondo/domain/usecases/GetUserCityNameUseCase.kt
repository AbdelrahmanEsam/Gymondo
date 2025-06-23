package com.apptikar.gymondo.domain.usecases

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserCityNameUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun execute(
        cityKey: String,
    )  : Flow<Result<String, Error>> = homeRepository.getCityName(cityKey)
}