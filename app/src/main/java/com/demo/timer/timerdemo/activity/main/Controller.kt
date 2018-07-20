package com.demo.timer.timerdemo.activity.main

import com.demo.timer.timerdemo.util.MathUtil
import com.demo.timer.timerdemo.util.TimeUtil
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.timer_scene.*
import java.util.concurrent.TimeUnit


class Controller(val activity: MainActivity) {

    var start = false;

    private fun getRemainingTime(time: Long, it: Long) =
            (time - it).toInt()

    private fun getArcSwipe(beginingArcSwipe: Float, time: Long, it: Long) : Float{
        return MathUtil.getAngleFromTime(
                beginingArcSwipe,
                0.0f,
                0,
                it,
                time)
    }

    fun timerStart(timerIndex: Int, beginingArcSwipe: Float){
        start = true
        val time = TimeUtil.formatIndexToTimeLong(timerIndex)
        Observable.interval(10, TimeUnit.MILLISECONDS)
                .takeWhile { shouldTake(it, time) }
                .doOnComplete (this::onComplete)
                .subscribe { timerTick(time, it, beginingArcSwipe) }
    }

    private fun shouldTake(it: Long, time: Long) = start && it < time

    private fun timerTick(time: Long, it: Long, beginingArcSwipe: Float) {
        activity.setTime(TimeUtil.formatTime(getRemainingTime(time, it)))
        activity.setArcSwipe(getArcSwipe(beginingArcSwipe, time, it))
    }

    private fun onComplete() {
        if (start) {
            activity.flash();
        }
        activity.onComplete()
        start = false
    }

    fun cancelTimer() {
        start=false
    }
}
