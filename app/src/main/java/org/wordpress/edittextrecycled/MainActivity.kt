package org.wordpress.edittextrecycled

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.method.ScrollingMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.DrawableMarginSpan
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textview)

        val spanned = SpannableString("q\nline2")
        spanned.setSpan(DrawableMarginSpan(resources.getDrawable(R.drawable.empty), 0), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanned.setSpan(BackgroundColorSpan(resources.getColor(android.R.color.holo_orange_dark)), 2, spanned.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.movementMethod = ScrollingMovementMethod()
        textView.text = spanned
    }
}
