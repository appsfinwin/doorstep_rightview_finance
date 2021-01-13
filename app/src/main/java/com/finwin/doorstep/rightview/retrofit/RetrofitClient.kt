package com.finwin.doorstep.rightview.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private var instance: Retrofit? = null
   // private var retrofitIfsc: Retrofit? = null

    public fun RetrofitClient(): Retrofit? {
        if (instance == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .build()

            instance = Retrofit.Builder()
                .baseUrl("http://doorstep.rvgn.digicob.in/")
                //.baseUrl("https://custmateuvnl.digicob.in")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return instance
    }
//
//    fun RetrofitIfsc(): Retrofit? {
//        if (retrofitIfsc == null) {
////            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
////            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
////            OkHttpClient client = getClient(token, logging, 10, TimeUnit.SECONDS);
//            val client: OkHttpClient = OkHttpClient.Builder()
//                .readTimeout(300, TimeUnit.SECONDS)
//                .connectTimeout(300, TimeUnit.SECONDS)
//                .build()
//            retrofitIfsc = Retrofit.Builder()
//                .baseUrl("http://www.finwintechnologies.com:82/api/")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//        return retrofitIfsc
//    }
}