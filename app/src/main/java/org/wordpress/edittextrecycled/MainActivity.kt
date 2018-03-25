package org.wordpress.edittextrecycled

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edittext = findViewById<MyEditText>(R.id.edittext)

        val spanned = SpannableStringBuilder()
        for (i in 0..100) {
            spanned.append("$i\n".padStart(10, ' '))
        }
        val shape = ShapeDrawable(RectShape())
        shape.intrinsicWidth = 1
        shape.intrinsicHeight = 600
        shape.setBounds(0, 0, shape.intrinsicWidth, shape.intrinsicHeight)
//        spanned.setSpan(DrawableMarginSpan(shape, 0), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        edittext.setText(spanned, TextView.BufferType.EDITABLE)
        edittext.addOnLayoutChangeListener({ view: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int ->
            Log.i("qwe", "Layout:\nleft  = $left\ntop   = $top\nright  = $right\nbottom = $bottom")
        })
    }
}
