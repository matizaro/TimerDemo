package com.demo.timer.timerdemo.activity.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.main_activity.*
import android.view.View
import android.view.animation.AnimationUtils
import com.demo.timer.timerdemo.R
import com.demo.timer.timerdemo.util.MathUtil
import com.demo.timer.timerdemo.util.TimeUtil
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.main_activity.view.*
import kotlinx.android.synthetic.main.timer_blue_scene.*
import kotlinx.android.synthetic.main.timer_blue_scene.view.*
import kotlinx.android.synthetic.main.timer_scene.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {


    private lateinit var controller: Controller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        controller = Controller(this)

        setTimeText(circular_clock.prevSelected)

        circular_clock.onChangeListener = Consumer { index->setTimeText(index)}

        timer_start.setOnClickListener{
            startAnimation()
            controller.timerStart(circular_clock.prevSelected, circular_clock.getArcSwipe())
        }

        timer_end.setOnClickListener {
            controller.cancelTimer()}

    }

    fun onComplete(){
        runOnUiThread {
            endAnimation()
            endCountingTime()
        }
    }

    fun flash() {
        runOnUiThread {
            flash.animation = AnimationUtils.loadAnimation(applicationContext, R.anim.animation_flash)
            flash.animate()
        }
    }

    private fun endCountingTime() {
        circular_clock.lock = false
    }

    fun startAnimation() {
        timer_blue_scene.alpha = 0.0f
        timer_blue_scene.visibility = View.VISIBLE
        timer_blue_scene.animate().alpha(1.0f).duration = 500;
    }

    private fun endAnimation() {
        time_tv.text = TimeUtil.formatIndexToTime(circular_clock.prevSelected)
        timer_blue_scene.alpha = 1.0f
        timer_blue_scene.animate().alpha(0.0f).setDuration(500).withEndAction {
            timer_blue_scene.visibility = View.GONE
        }
    }

    private fun setTimeText(index: Int) {
        time_tv.text = TimeUtil.formatIndexToTime(index)
    }

    fun setTime(formatTime: String) {
        runOnUiThread {
            time_tv.text = formatTime
        }
    }

    fun setArcSwipe(arcSwipe: Float) {
        runOnUiThread {
            timer_blue_scene.timer_view.setArcSwipe(arcSwipe)
        }
    }

    override fun onStop() {
        super.onStop()
        controller.cancelTimer()
    }
}
