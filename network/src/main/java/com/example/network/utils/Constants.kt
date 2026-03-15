package com.example.network.utils

object Constants {

    const val X_RAPIDAPI_KEY: String= ""

    const val X_RAPIDAPI_HOST: String = ""

    private const val BASE_URL = "https://spotify-downloader9.p.rapidapi.com"

    private const val SEARCH = "$BASE_URL/search?q="

    private const val TRACK = "$BASE_URL/tracks?ids="

    private const val DOWNLOAD = "$BASE_URL/downloadSong?songId=https%3A%2F%2Fopen.spotify.com%2Ftrack%2F"

    const val DEFAULT_DOWNLOAD_LINK = "https://node01.dlapi.app/dl?hash=ZgQ%2FI6DLeM67QRUDoiEztJI7zRh1ddoiZCt7Yq7vcILDb%2FBprHl8edNRi%2BZMwBkTXuv3FaTCycROWeGK8Rhm9SxyRQ6dhdFw2R3jIi8Oeura2i%2B9Z6Dz7BZ3Ugk3%2BB541AsYHMDQE7uT19JhoJHI2wq0Rq1XfgrLQu44h7HBu%2BUJiEhyxITDVPTy1CbQ383VvDUtRM3ja60bUZNp0K0xBtgaPhtjEQb9XA4y%2FzXuzWhbBk%2B8Cf1iFhNa6V3ermXj"

    const val DEFAULT_SONG_ID = "6AsOmRcEY4jgLZCuQwKe7w"

    private const val TRACK_RECOMMENDATION= "$BASE_URL/recommendations?seedTracks="


    fun track(id: String) = TRACK + id

    fun search(query: String) =
        SEARCH + query.replace(" ", "%20") + "&type=multi&limit=100&offset=0&noOfTopResults=20"

    fun download(trackId: String) = DOWNLOAD + trackId

    fun trackRecommendation(
        trackIds: List<String> = listOf(DEFAULT_SONG_ID),
        genre: String = "",
        limit: Int = 100
    ): String{
        println(
            TRACK_RECOMMENDATION + trackIds.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "%2C")
                .replace(" ", "%20") + "&seedGenres=$genre&limit=$limit"
        )
        return TRACK_RECOMMENDATION + trackIds.toString()
            .replace("[", "")
            .replace("]", "")
            .replace(",", "%2C")
            .replace(" ", "%20") + "&seedGenres=$genre&limit=$limit"

    }
}