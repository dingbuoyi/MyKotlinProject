package org.dean.mykotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_photo.*

/**
 * Created by dean on 2017/11/28.
 */

class PhotoActivity : AppCompatActivity() {
    // 变量可以为空
    private var selectedPhotoUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_photo)

        selectedPhotoUrl = intent.getStringExtra(PhotoActivity.PHOTO_KEY)
        GlideApp.with(this)
                .load(selectedPhotoUrl)
                .centerCrop()
                .into(photoImageView)
    }

    companion object {
        // 相当于java里面的静态变量，并且一定要定义在class里面, 调用时候class.xx
        private const val PHOTO_KEY = "PHOTO" // 用 const 修饰 真正的常量
        // 没用cosnt修饰的，都是用的getter去获取的，猜测应该是编译成了备用字段，然后生成了getter方法。而用了const修饰符的属性则直接是静态属性。
        // const只能修饰val，不能修饰var
        // top-level
        // const val name = "Kotlin"
        //
        // object中
        // class DemoConstant {
        //     companion object {
        //         const val subName = ""
        //     }
        //  }
    }
}