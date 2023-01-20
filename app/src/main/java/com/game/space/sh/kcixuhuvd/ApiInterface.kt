package com.game.space.sh.kcixuhuvd
import com.game.space.sh.qpwosjid.CountryCodeJS

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("json/?key=ZSSz86ONagSoYRv")
    suspend fun getData(): Response<CountryCodeJS>


}



