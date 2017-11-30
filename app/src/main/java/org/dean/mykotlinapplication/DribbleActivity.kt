package org.dean.mykotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_dribble.*
import org.dean.mykotlinapplication.api.DribbleApiService

/**
 * Created by dean on 2017/11/27.
 */
class DribbleActivity : AppCompatActivity() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: DribbleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dribble)

        gridLayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = gridLayoutManager

        val apiService = DribbleApiService.create()
        apiService.search().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ list ->
                    list.forEach { Log.d("Teaser", it.toString()) }
                    adapter = DribbleAdapter(list)
                    recyclerView.adapter = adapter
                }, { error ->
                    error.printStackTrace()
                })
    }
}