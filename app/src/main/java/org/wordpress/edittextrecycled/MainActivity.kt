package org.wordpress.edittextrecycled

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.DrawableMarginSpan
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edittext = findViewById<TextView>(R.id.edittext)

        val spanned = SpannableStringBuilder()
        for (i in 0..1000) {
            spanned.append("line $i\n")
        }
        spanned.setSpan(DrawableMarginSpan(resources.getDrawable(R.drawable.empty), 0), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        edittext.setText(spanned, TextView.BufferType.EDITABLE)
    }
}
