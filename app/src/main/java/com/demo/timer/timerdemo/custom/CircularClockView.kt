package com.demo.timer.timerdemo.custom

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout

import com.demo.timer.timerdemo.R
import com.demo.timer.timerdemo.util.MathUtil

import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.circular_timer.view.*

class CircularClockView : LinearLayout {
    lateinit internal var buttonsView: SwipeView
    lateinit internal var clockView: TimerView

    var lock = false

    var prevSelected = 1
    var onChangeListener: Consumer<Int>? = null

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
        initalizeViews(context)
    }

    private fun initalizeViews(context: Context) {
        val timer = LayoutInflater.from(context).inflate(R.layout.circular_timer, this)
        buttonsView = timer.swipe_view
        clockView = timer.timer_view
        selectButton(prevSelected)
}

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(!lock){
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> moveAction(event)
            }
        }
        return true
    }

    fun getArcSwipe(): Float{
        return clockView.getArcSwipe()
    }
    fun reset(){
        selectButton(1)
    }
    fun selectButton(index: Int){
        prevSelected = index
        buttonsView.selectButton(index)
        clockView.setArcSwipe(buttonsView.getAngle(index))
    }
    private fun moveAction(event: MotionEvent) {
        val index = getSelectedButtonIndex(event)
        if (index != prevSelected) {
            onChangeListener?.accept(index)
            selectButton(index)
        }
    }

    private fun getSelectedButtonIndex(event: MotionEvent): Int {
        return buttonsView.getClosest(
                MathUtil.angleBetween(
                        pivotX,
                        pivotY,
                        pivotX,
                        top.toFloat(),
                        event.x,
                        event.y
                ).toFloat())
    }
}
