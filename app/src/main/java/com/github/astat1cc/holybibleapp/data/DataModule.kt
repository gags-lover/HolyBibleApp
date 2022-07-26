package com.github.astat1cc.holybibleapp.data

import androidx.room.Room
import com.github.astat1cc.holybibleapp.data.cache.BookCacheMapper
import com.github.astat1cc.holybibleapp.data.cache.BooksDatabase
import com.github.astat1cc.holybibleapp.data.network.BookCloudMapper
import com.github.astat1cc.holybibleapp.data.network.BooksService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            BooksDatabase::class.java,
            BooksDatabase.DATABASE_NAME
        ).build()
    }
}

val dataModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder().addInterceptor(get() as HttpLoggingInterceptor).build()
    }
    single {
        provideBooksService(retrofit = get())
    }
    single {
        provideBooksDao(database = get())
    }
    single<BooksRepository> {
        BooksRepository.Base(
            BooksCloudDataSource.Base(service = get()),
            BooksCacheDataSource.Base(booksDao = get()),
            BooksCloudMapper.Base(BookCloudMapper.Base()),
            BooksCacheMapper.Base(BookCacheMapper.Base())
        )
    }
}

fun provideBooksService(retrofit: Retrofit): BooksService =
    retrofit.create(BooksService::class.java)

fun provideBooksDao(database: BooksDatabase) = database.booksDao()