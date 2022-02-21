package com.assignment.androidmatrix

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class MatrixView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var numColumns = 0
    var numRows: Int = 0
    private var cellWidth = 0
    private var cellHeight: Int = 0
    private val redPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()
    private lateinit var cellChecked: Array<BooleanArray>
    private var boxes = Boxes()

    init {
        redPaint.color = Color.RED
    }

    private fun calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return
        }
        cellWidth = width / numColumns
        cellHeight = height / numRows
        cellChecked = Array(numColumns) { BooleanArray(numRows) }
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calculateDimensions()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.BLUE)
        if (numColumns == 0 || numRows == 0) {
            return
        }
        val width = width
        val height = height
        for (i in 0 until numColumns) {
            for (j in 0 until numRows) {
                if (boxes.touchMatrix[i][j]) {
                    canvas.drawRect(
                        (i * cellWidth).toFloat(), (j * cellHeight).toFloat(),
                        ((i + 1) * cellWidth).toFloat(), ((j + 1) * cellHeight).toFloat(),
                        redPaint
                    )
                }
            }
        }
        for (i in 1 until numColumns) {
            canvas.drawLine(
                (i * cellWidth).toFloat(), 0F, (i * cellWidth).toFloat(),
                height.toFloat(), blackPaint
            )
        }
        for (i in 1 until numRows) {
            canvas.drawLine(
                0F, (i * cellHeight).toFloat(),
                width.toFloat(), (i * cellHeight).toFloat(), blackPaint
            )
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        updateTouchedBoxes(event);
        invalidate();
        return true;
    }

    private fun updateTouchedBoxes(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val column = (event.x / cellWidth).toInt()
            val row = (event.y / cellHeight).toInt()
            boxes.updateBoxes(column, row)
        }
    }


}