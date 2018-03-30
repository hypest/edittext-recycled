package org.wordpress.edittextrecycled

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.DrawableMarginSpan
import android.util.AttributeSet
import android.util.Log
import android.widget.EditText

class MyEditText : EditText {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var span: Any? = null

    override fun onScrollChanged(horiz: Int, vert: Int, oldHoriz: Int, oldVert: Int) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert)

        val topLine = layout.getLineForVertical(vert)

        // wait till we have couple of lines hidden
        if (topLine > 2) {
            // replace two lines up the current one
            val hideUpTo = topLine - 2

            val endHide = layout.getLineEnd(hideUpTo)

            val toBottom = layout.getLineBottom(hideUpTo)

            val shape = ShapeDrawable(RectShape())
            shape.intrinsicWidth = 100
            shape.intrinsicHeight = toBottom // the span will be as tall as the replaced lines
            shape.setBounds(0, 0, shape.intrinsicWidth, shape.intrinsicHeight)

            // Cast to SpannableStringBuilder otherwise the `replace` call will mess up the spans and kill the scroll
            val t = text as SpannableStringBuilder
            if (span != null) {
                t.removeSpan(span)
            }

            val spannable = SpannableString("@\n".padStart(10))
            span = DrawableMarginSpan(shape, 0)
            spannable.setSpan(span, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            t.replace(0, endHide, spannable)

            refreshDrawableState()
        }
    }
}
