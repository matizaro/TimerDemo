package com.demo.timer.timerdemo.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View

import com.demo.timer.timerdemo.R

class TimerView : View {
    internal var arcSwipe = 180.0f
    private var swipeSize = 40.0f
    lateinit var bgColor: Paint

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.TimerView,
                0, 0)
        try {
            arcSwipe = a.getFloat(R.styleable.TimerView_arcAngle, 180.0f)
        } finally {
            a.recycle()
        }
        bgColor = Paint()
        bgColor.color = ContextCompat.getColor(context, R.color.clock_color)
        bgColor.style = Paint.Style.STROKE
        swipeSize = context.resources.getDimension(R.dimen.ring_thickness)
        bgColor.strokeWidth = swipeSize
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(swipeSize / 2,
                swipeSize / 2,
                width - swipeSize / 2,
                height - swipeSize / 2,
                START_ARC_ANGLE.toFloat(),
                getArcSwipe(),
                false, bgColor)
    }

    fun getArcSwipe(): Float {
        return arcSwipe
    }

    fun setArcSwipe(arcSwipe: Float) {
        this.arcSwipe = arcSwipe
        invalidate()
    }

    companion object {
        private val START_ARC_ANGLE = -90
    }
}
