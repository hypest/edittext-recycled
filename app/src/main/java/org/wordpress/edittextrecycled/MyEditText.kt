package org.wordpress.edittextrecycled

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.EditText

class MyEditText : EditText {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onScrollChanged(horiz: Int, vert: Int, oldHoriz: Int, oldVert: Int) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert)
        Log.i("qwe", "Scroll: horiz = $horiz, vert  = $vert, oldHoriz = $oldHoriz, oldVert = $oldVert")
        Log.i("qwe", "line = ${layout.getLineForVertical(vert)}")
    }
}
