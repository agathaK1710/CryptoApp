package com.android.cryptoapp.data.network

import com.android.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.android.cryptoapp.data.network.model.CoinNamesListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_TSYMS) tsyms: String = CURRENCY,
        @Query(QUERY_PARAM_FSYMS) fsyms: String
    ): CoinInfoJsonContainerDto

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TSYM) tsym: String = CURRENCY
    ): CoinNamesListDto

    companion object {
        private const val API_KEY = "3d225ebf8b3653dfa41a94694185064565a7acb233915bda48ae13ecdd659a0c"
        private const val CURRENCY = "USD"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_FSYMS = "fsyms"
        private const val QUERY_PARAM_TSYMS = "tsyms"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TSYM = "tsym"
    }
}