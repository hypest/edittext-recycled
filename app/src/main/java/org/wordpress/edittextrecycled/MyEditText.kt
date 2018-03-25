package org.wordpress.edittextrecycled

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.DrawableMarginSpan
import android.util.AttributeSet
import android.widget.EditText

class MyEditText : EditText {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var span: Any? = null

    override fun onScrollChanged(horiz: Int, vert: Int, oldHoriz: Int, oldVert: Int) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert)

        val topLinePrev = layout.getLineForVertical(oldVert)
        val topLine = layout.getLineForVertical(vert)

        // wait till we have couple of lines hidden
        if (topLine > topLinePrev && topLine > 1) {
            // replace two lines up the current one
            val hiddenLine = topLine - 2

            val endHidden = layout.getLineEnd(hiddenLine)

            val prevBottom = layout.getLineBottom(hiddenLine)

            val shape = ShapeDrawable(RectShape())
            shape.intrinsicWidth = 1
            shape.intrinsicHeight = prevBottom // the span will be as tall as the replaced lines
            shape.setBounds(0, 0, shape.intrinsicWidth, shape.intrinsicHeight)

            // Cast to SpannableStringBuilder otherwise the `replace` call will mess up the spans and kill the scroll
            val t = text as SpannableStringBuilder
            if (span != null) {
                t.removeSpan(span)
            }

            val spannable = SpannableString("@\n".padStart(10))
            span = DrawableMarginSpan(shape, 0)
            spannable.setSpan(span, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            post {
                t.replace(0, endHidden, spannable)
                refreshDrawableState()
            }
        }
    }
}
