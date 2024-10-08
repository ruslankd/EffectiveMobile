package ru.kabirov.effectivemobile.ui

import android.content.Context
import android.util.DisplayMetrics

fun Float.pxToDp(context: Context): Float =
    (this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))