package org.dean.mykotlinapplication.extensions

import android.app.Activity
import android.widget.Toast

/**
 * Created by dean on 2018/3/28.
 */
fun Activity.toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
