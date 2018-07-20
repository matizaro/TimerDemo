package com.demo.timer.timerdemo.custom

import android.content.Context
import android.util.AttributeSet

import com.demo.timer.timerdemo.R

class SwipeViewBlue : SwipeView {

    override val dividerId: Int
        get() = R.layout.divider_blue

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}
}
