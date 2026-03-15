package com.example.dettol.di


import android.content.Context
import android.content.SharedPreferences
import com.example.dettol.mediaReader.MediaReader
import com.example.dettol.repositories.DownloadRepository
import com.example.dettol.repositories.HomeScreenRepository
import com.example.dettol.repositories.SearchRepository
import com.example.dettol.repositories.TrackDetailRepository
import com.example.network.KtorClient
import com.example.network.SongDownloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKtorClient(): KtorClient{
        return KtorClient()
    }

    @Provides
    @Singleton
    fun provideSearchRepository(client: KtorClient) = SearchRepository(client)

    @Provides
    @Singleton
    fun provideTrackDetailRepository(client: KtorClient) = TrackDetailRepository(client)

    @Provides
    @Singleton
    fun provideDownloadRepository(client: KtorClient)= DownloadRepository(client)


    @Provides
    @Singleton
    fun provideSongDownloader(@ApplicationContext context: Context): SongDownloader {
        return SongDownloader(context)
    }

    @Provides
    @Singleton
    fun provideHomeScreenRepository(client: KtorClient) = HomeScreenRepository(client)

    @Provides
    @Singleton
    fun provideMediaReader(@ApplicationContext context: Context) = MediaReader(context)

//    @Provides
//    @Singleton
//    fun provideDataStore(@ApplicationContext context: Context) =
//        DataStoreFactory.create(
//            serializer = TrackIdsSerializer(),
//            produceFile = {
//                File("${context.cacheDir.path}/trackids.preferences_pd")
//            },
//            corruptionHandler = ReplaceFileCorruptionHandler { TracksIds(trackIds = "") }
//        )
//
//    @Provides
//    @Singleton
//    fun provideBroadcastReceiverViewModel(
//        @ApplicationContext context: Context
//    ): BroadcastReceiverViewModel {
//        return BroadcastReceiverViewModel(context)
//    }

}

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
    }
}