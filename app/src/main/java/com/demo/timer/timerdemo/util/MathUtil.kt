package com.demo.timer.timerdemo.util

object MathUtil {
    fun angleBetween(centerX: Float, centerY: Float, x: Float, y: Float, prevX: Float, prevY: Float): Double {
        var angle = Math.toDegrees(
                Math.atan2((x - centerX).toDouble(), (y - centerY).toDouble())
                        - Math.atan2((prevX - centerX).toDouble(), (prevY - centerY).toDouble()))
        if (angle < 0) {
            angle += 360.0
        }
        return angle
    }
    fun getAngleFromTime(startAngle: Float,endAngle: Float, startTime: Long, currentTime: Long, endTime: Long): Float{
        if(endTime-startTime<=0) return 0.0f
        if(currentTime>endTime) return 0.0f;
        if(startAngle<endAngle) return 0.0f
        val elapsed = 1.0-currentTime.toFloat()/(endTime-startTime)
        return ((startAngle-endAngle)*elapsed).toFloat()
    }
}
