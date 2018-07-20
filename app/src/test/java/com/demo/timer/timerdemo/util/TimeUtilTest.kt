package com.demo.timer.timerdemo.util

import org.junit.Test

import org.junit.Assert.*

class TimeUtilTest {

    @Test
    fun formatTime() {
        assertEquals(TimeUtil.formatTime(5),"00:05")
        assertEquals(TimeUtil.formatTime(100),"01:00")
    }

    @Test
    fun formatIndexToTime() {
        assertEquals(TimeUtil.formatIndexToTime(5),"05:00")
        assertEquals(TimeUtil.formatIndexToTime(100),"100:00")
    }

    @Test
    fun formatIndexToTimeLong() {
        assertEquals(TimeUtil.formatIndexToTimeLong(5),500)
        assertEquals(TimeUtil.formatIndexToTimeLong(100),10000)
    }
}