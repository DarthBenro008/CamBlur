package com.benrostudios.camblur

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.ViewGroup


class CustomCanvas : ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    public override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
    }

    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        val viewportMargin = 32
        val viewportCornerRadius = 8
        val eraser = Paint()
        eraser.isAntiAlias = true
        eraser.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        val width = width.toFloat() - viewportMargin
        val height = width * 1.toFloat()
        val rect =
            RectF(viewportMargin.toFloat(), 300.toFloat(), width, height)
        val frame = RectF(
            viewportMargin.toFloat() - 2,
            300.toFloat() - 2,
            width + 4,
            height + 4
        )
        val path = Path()
        val stroke = Paint()
        stroke.isAntiAlias = true
        stroke.strokeWidth = 4F
        stroke.color = Color.WHITE
        stroke.style = Paint.Style.STROKE
        path.addRoundRect(
            frame,
            viewportCornerRadius.toFloat(),
            viewportCornerRadius.toFloat(),
            Path.Direction.CW
        )
        canvas.drawPath(path, stroke)
        canvas.drawRoundRect(
            rect,
            viewportCornerRadius.toFloat(),
            viewportCornerRadius.toFloat(),
            eraser
        )
    }
}