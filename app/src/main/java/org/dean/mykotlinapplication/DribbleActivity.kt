package org.dean.mykotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_dribble.*
import org.dean.mykotlinapplication.api.DribbleApiService
import org.dean.mykotlinapplication.extensions.toast

/**
 * Created by dean on 2017/11/27.
 */
class DribbleActivity : AppCompatActivity() {

    private lateinit var gridLayoutManager: GridLayoutManager //可修改的变量
    private lateinit var adapter: DribbleAdapter
    // 不用lateinit的话，必须初始化 像下面这种
    // private var adapter: DribbleAdapter? = null

    // lazy 模式更有利于封装你的初始化代码。
    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar) // lazy init toolbar
    }


    // 翻译：Kotlin 变量, 使用 Lazy 还是 Late?
    // http://ebnbin.com/2017/06/16/kotlin_variable_to_be_lazy_or_to_be_late/

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
                    toast(error.message)
                })

        toolbar.title = "APP"
    }
}