package com.demo.timer.timerdemo.util

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MathUtilTest {

    @Test
    fun angleBetween() {
        assertEquals(MathUtil.angleBetween(0f, 0f, 1f, 0f, 0f, 2f),90.0,0.01);
        assertEquals(MathUtil.angleBetween(0f, 0f, 1f, 0f, -1f, 0f),180.0,0.01);
        assertEquals(MathUtil.angleBetween(0f, 0f, 0f, 0f, 0f, 2f),0.0,0.01);
        assertEquals(MathUtil.angleBetween(0f, 0f, 0f, 0f, 0f, 0f),0.0,0.01);
    }

    @Test
    fun getAngleFromTime() {
        assertEquals(MathUtil.getAngleFromTime(180.0f,0.0f,0,100,200),90.0f,0.01f)
        assertEquals(MathUtil.getAngleFromTime(360.0f,0.0f,0,100,200),180.0f,0.01f)
        assertEquals(MathUtil.getAngleFromTime(0.0f,0.0f,0,100,200),0.0f,0.01f)
        assertEquals(MathUtil.getAngleFromTime(-5.0f,0.0f,0,100,200),0.0f,0.01f)
        assertEquals(MathUtil.getAngleFromTime(-5.0f,0.0f,200,100,0),0.0f,0.01f)
    }
}