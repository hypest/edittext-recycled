package org.wordpress.edittextrecycled

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.text.Spannable
import android.text.SpannableString
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
        Log.i("qwe", "Scroll: horiz = $horiz, vert  = $vert, oldHoriz = $oldHoriz, oldVert = $oldVert")

        val topLinePrev = layout.getLineForVertical(oldVert)
        val topLine = layout.getLineForVertical(vert)
//        Log.i("qwe", "line = $topLine")

        if (topLine > topLinePrev) {
            Log.i("qwe", "topLine = $topLine, topLinePrev = $topLinePrev")

            val endHidden = layout.getLineEnd(topLinePrev)
//            Log.i("qwe", "offset line = $topLineCalc, end = $end")

            val prevBottom = layout.getLineBottom(topLinePrev)
//            Log.i("qwe", "vert = $vert, offset line = $topLinePrev, end = $endHidden, Prev bottom = $prevBottom")
            Log.i("qwe", "hiddenEnd = $endHidden, prevBottom = $prevBottom")

            val shape = ShapeDrawable(RectShape())
            shape.intrinsicWidth = 1
            shape.intrinsicHeight = prevBottom
            shape.setBounds(0, 0, shape.intrinsicWidth, shape.intrinsicHeight)

            if (span != null) {
                text.removeSpan(span)
            }

            val spannable = SpannableString("\n".padStart(10))
            span = DrawableMarginSpan(shape, 0)
            spannable.setSpan(span, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            post {
                setTextKeepState(text.replace(0, endHidden, spannable))
                invalidate()
            }
//            postDelayed({ scrollY = vert }, 500)
            Log.i("qwe", "text = ${text.substring(0, 50)}")
        }
    }
}
