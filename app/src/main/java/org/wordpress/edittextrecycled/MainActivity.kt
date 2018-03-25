package org.wordpress.edittextrecycled

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.DrawableMarginSpan
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
            spanned.append("line $i\n")
        }
        spanned.setSpan(DrawableMarginSpan(resources.getDrawable(R.drawable.empty), 0), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        edittext.setText(spanned, TextView.BufferType.EDITABLE)
        edittext.addOnLayoutChangeListener({ view: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int ->
            Log.i("qwe", "Layout:\nleft  = $left\ntop   = $top\nright  = $right\nbottom = $bottom")
        })
    }
}
