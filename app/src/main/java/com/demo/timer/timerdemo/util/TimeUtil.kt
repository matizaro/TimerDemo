package com.demo.timer.timerdemo.util

object TimeUtil{
    fun formatTime(time: Int): String{
        return String.format("%02d", time/100)+":"+String.format("%02d", time%100)
    }
    fun formatIndexToTime(index: Int): String = formatTime(100*index);
    fun formatIndexToTimeLong(index: Int): Long = 100L*index;
}