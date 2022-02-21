package com.assignment.androidmatrix

class Boxes {
    var touchMatrix: Array<BooleanArray> = Array(4) { BooleanArray(4) }
    private var firstTouchedColumn = -1
    private var firstTouchedRow = -1
    private var currentTouchedColumn = -1
    private var currentTouchedRow = -1

    fun updateBoxes(checkedColumn: Int, checkedRow: Int) {
        touchMatrix[checkedColumn][checkedRow] = !touchMatrix[checkedColumn][checkedRow]
        if (currentTouchedColumn > -1) {
            if (firstTouchedColumn > -1) {
                touchMatrix[firstTouchedColumn][firstTouchedRow] =
                    !touchMatrix[firstTouchedColumn][firstTouchedRow]
            }
            firstTouchedColumn = currentTouchedColumn
            firstTouchedRow = currentTouchedRow
        }
        currentTouchedColumn = checkedColumn
        currentTouchedRow = checkedRow
    }
}
