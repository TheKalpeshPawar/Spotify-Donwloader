package com.example.network

import android.util.Log
import com.example.network.dataAndError.DataOrError
import com.example.network.dataAndError.Error
import com.example.network.dataAndError.Result
import com.example.network.module.Search
import com.example.network.module.download.Download
import com.example.network.module.trackDetails.TrackDetails
import com.example.network.module.trackDetails.trackRecommendations.TrackRecommendations
import com.example.network.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class KtorClient{

    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(OkHttp){
        install(ContentNegotiation){
            this.json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }

        defaultRequest {
            header("X-RapidAPI-Key", Constants.X_RAPIDAPI_KEY)
            header("X-RapidAPI-Host" , Constants.X_RAPIDAPI_HOST)
        }
    }

    suspend fun search(query: String): Search {
        val httpResponse = client.request(
            Constants.search(query)
        )

        Log.d("HttpResponse",
            "${httpResponse.status}")
        Log.d("HttpResponse",
            "${httpResponse.headers}")
        Log.d("HttpResponse",
            httpResponse.body<String>()
        )

        val response = safeClientRequest<Search>(httpResponse)

        return when(response.data){
            null ->{
                Search(
                    data = null,
                    success = false,
                    message = response.message
                )
            }
            else ->{
                Search(
                    data = response.data.data,
                    success = true,
                    message = null
                )
            }
        }

    }

    suspend fun trackDetails(trackId: String): TrackDetails{
        val httpResponse = client.request(
            Constants.track(trackId)
        )

        val response = safeClientRequest<TrackDetails>(httpResponse)

        return when(response.data){
            null ->{
                TrackDetails(
                    data = null,
                    success = false,
                    message = response.message
                )
            }
            else ->{
                TrackDetails(
                    data = response.data.data,
                    success = true,
                    message = null
                )
            }
        }

    }


    suspend fun downloadSong(trackId: String): Download {
        val httpResponse = client.request(
            Constants.download(trackId)
        )

        val response = safeClientRequest<Download>(httpResponse)

        return when(response.data){
            null ->{
                Download(
                    data = null,
                    success = false,
                    message = response.message
                )
            }
            else ->{
                Download(
                    data = response.data.data,
                    success = true,
                    message = null
                )
            }
        }

    }



    suspend fun getTrackRecommendations(
        trackIds: List<String>,
        genre: String = "",
        limit: Int = 100
    ): TrackRecommendations{
        val httpResponse = client.request(
            Constants.trackRecommendation(trackIds, genre, limit)
        )

        val response = safeClientRequest<TrackRecommendations>(httpResponse)

        return when(response.data){
            null ->{
                TrackRecommendations(
                    data = null,
                    success = false,
                    message = response.message
                )
            }
            else ->{
                TrackRecommendations(
                    data = response.data.data,
                    success = true,
                    message = null
                )
            }
        }

    }

}

suspend inline fun <reified D> safeClientRequest(httpResponse: HttpResponse): DataOrError<D>{

    val result = validateResponse(httpResponse)

    return when(result){
        is Result.Error -> {
            DataOrError(
                data = null,
                success = false,
                message = result.error.name
            )
        }
        is Result.Success -> {
            DataOrError(data = httpResponse.body<D>(), success = true, message = null)
        }
    }
}

fun validateResponse(httpResponse: HttpResponse): Result<Unit,  RequestErrors> {

    return when(httpResponse.status.value){
        200 -> {
            Result.Success(Unit)
        }
        503 -> {
            Result.Error(error = RequestErrors.SERVER_DOWN)
        }
        429 -> {
            Result.Error(error = RequestErrors.DAILY_REQUEST_REACHED)
        }
        else -> {
            Result.Error(error = RequestErrors.UNKNOWN_ERROR)
        }
    }
}

enum class RequestErrors: Error {
    SERVER_DOWN,
    DAILY_REQUEST_REACHED,
    UNKNOWN_ERROR
}