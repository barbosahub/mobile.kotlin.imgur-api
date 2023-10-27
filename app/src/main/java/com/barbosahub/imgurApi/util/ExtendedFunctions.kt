package com.barbosahub.imgurApi.util

import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.children
import com.google.android.material.appbar.MaterialToolbar


fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()

fun Int.pxToDp(displayMetrics: DisplayMetrics): Int = (this / displayMetrics.density).toInt()

fun MaterialToolbar.setOnBackClickListener(listener: View.OnClickListener){
    children.forEach {
        if (it is AppCompatImageButton){
            it.setOnClickListener(listener)
        }
    }
}

//fun getJson(path: String, context: Context): String{
//    val uri = context.javaClass.classLoader!!.getResource(path)
//    val file = File(path)
//    return  String(file.readBytes())
//}