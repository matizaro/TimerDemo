package com.demo.timer.timerdemo.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RelativeLayout

import com.demo.timer.timerdemo.R
import com.google.common.collect.HashBiMap

import kotlinx.android.synthetic.main.divider.view.*


open class SwipeView : RelativeLayout {

    var size = 12

    private val customRadioGroup = HashBiMap.create<RadioButton, Int>()
    private var previousButton: RadioButton? = null

    protected open val dividerId: Int
        get() = R.layout.divider

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
                R.styleable.SwipeView,
                0, 0)
        try {
            size = Math.max(a.getInt(R.styleable.SwipeView_buttonsNumber, MIN_BUTTONS_NUMBER), MIN_BUTTONS_NUMBER)
            initializeViews(context, dividerId)
        } finally {
            a.recycle()
        }
    }

    private fun initializeViews(context: Context, dividerId: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var index = 0
        var angle = START_ANGLE.toFloat()
        while (angle > START_ANGLE - FULL_ANGLE) {
            val rotationLayout = inflater.inflate(dividerId, this, false)
            rotationLayout.divider_layout.angle = angle.toInt()
            customRadioGroup[rotationLayout.timer_cb] = index++
            rotationLayout.timer_cb.isClickable = false
            addView(rotationLayout)
            angle -= FULL_ANGLE.toFloat() / size
        }
    }

    fun getAngle(index: Int): Float {
        return index * FULL_ANGLE.toFloat() / size
    }

    fun getClosest(angle: Float): Int {
        return Math.max(1 , Math.round(angle * size / FULL_ANGLE))
    }

    fun selectButton(button: RadioButton?) {
        previousButton?.isChecked = false
        previousButton?.isSelected = false
        button?.isChecked = true
        button?.isSelected = true
        previousButton = button
    }

    fun selectButton(index: Int) {
        selectButton(customRadioGroup.inverse()[index.rem(size)])
    }

    companion object {
        private val MIN_BUTTONS_NUMBER = 2
        private val FULL_ANGLE = 360
        private val START_ANGLE = FULL_ANGLE + 90
    }
}
