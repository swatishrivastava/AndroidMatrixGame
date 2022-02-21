package com.assignment.androidmatrix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var matrixView = MatrixView(this)
        matrixView.numColumns = 4
        matrixView.numRows = 4
        setContentView(matrixView)
    }
}