package com.jiangkang.widget.view

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator

/**
 * Created by jiangkang on 2018/1/4.
 * description：
 */
open class AnimatedShapeView : View {

    private var mRadius: Int = 0

    private var mRotate: Int = 0

    private var mBackgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : super(context) {
        initAttributes(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(context, attrs)
    }


    fun initAttributes(context: Context?, attrs: AttributeSet?) {
        mBackgroundPaint.color = Color.parseColor("#00897B")
    }

    override fun onDraw(canvas: Canvas?) {
        val viewWidth = width / 2
        val viewHeight = height / 2

        val leftTopX = viewWidth - 300
        val leftTopY = viewHeight - 300

        val rightBottomX = viewWidth + 300
        val rightBottomY = viewHeight + 300

        println(viewHeight)
        println(viewWidth)
        println(mRadius)

        canvas!!.rotate(mRotate.toFloat(), viewWidth.toFloat(), viewHeight.toFloat())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas!!.drawRoundRect(leftTopX.toFloat(), leftTopY.toFloat(), rightBottomX.toFloat(), rightBottomY.toFloat(), mRadius.toFloat(), mRadius.toFloat(), mBackgroundPaint)
        }


    }


    fun start() {
        var animator = ValueAnimator.ofInt(0, 300)
        animator.duration = 3000
        animator.addUpdateListener({ animation ->
            mRadius = animation.animatedValue as Int
            invalidate()
        })
        animator.start()
    }


    fun startRotate() {
        val propertyRadius = PropertyValuesHolder.ofInt("PropertyRadius", 0, 300)
        val propertyRotate = PropertyValuesHolder.ofInt("PropertyRotate", 0, 360)

        var animator = ValueAnimator()
        animator.setValues(propertyRadius, propertyRotate)
        animator.duration = 3000
        animator.interpolator = BounceInterpolator(context, null)
        animator.addUpdateListener { animation ->
            mRadius = animation.getAnimatedValue("PropertyRadius") as Int
            mRotate = animation.getAnimatedValue("PropertyRotate") as Int
            invalidate()
        }
        animator.start()

    }


}