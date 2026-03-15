package com.example.network

interface Downloader {

    fun downloadFile(url: String, songName: String = "music"): Long

}