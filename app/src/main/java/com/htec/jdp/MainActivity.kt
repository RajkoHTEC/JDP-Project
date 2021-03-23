package com.htec.jdp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById<TextView>(R.id.tvResult)

        val retrofit = with(Retrofit.Builder()) {
            baseUrl("https://api.github.com/")
            addConverterFactory(MoshiConverterFactory.create())
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            build()
        }

        val githubApi: GithubApi = retrofit.create(GithubApi::class.java)

        val repos = githubApi.getReposForUser("Rajko97")

        disposable = repos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {data -> onSuccess(data)}, {error -> onError(error)})
    }

    private fun onSuccess(data: List<RepoModel>) {
        tvResult.setText(data.map { "\n${it.name}"}.toString().removeSurrounding("[", "]"))
    }

    private fun onError(error: Throwable) {
    }
}