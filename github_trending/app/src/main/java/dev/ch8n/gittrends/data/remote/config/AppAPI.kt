package dev.ch8n.gittrends.data.remote.config

import dev.ch8n.gittrends.BuildConfig

object BaseUrl {

    private const val PROTOCOL_HTTP = BuildConfig.PROTOCOL_HTTP
    private const val BASE_URL = BuildConfig.BASE_URL

    var BASE_SERVER = "$PROTOCOL_HTTP$BASE_URL"
        private set
}

object AppAPI {
    const val GET_TRENDING_REPO = "/developers"
}


const val NETWORK_TIMEOUT = 20L
