package com.game.space.sh.kcixuhuvd


import com.game.space.sh.qpwosjid.GeoDev
import retrofit2.Response
import retrofit2.http.GET

interface HostInterface {

    @GET("const.json")
    suspend fun getDataDev(): Response<GeoDev>
}