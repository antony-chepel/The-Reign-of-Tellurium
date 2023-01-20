package com.game.space.sh.xdospdi

import com.game.space.sh.kcixuhuvd.ApiInterface
import com.game.space.sh.kcixuhuvd.HostInterface
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface, private val hostInterface: HostInterface) {

    suspend fun getData() = apiInterface.getData()
    suspend fun getDataDev() = hostInterface.getDataDev()
}