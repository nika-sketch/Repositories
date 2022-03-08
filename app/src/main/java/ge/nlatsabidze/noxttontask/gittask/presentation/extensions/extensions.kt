package ge.nlatsabidze.noxttontask.gittask.presentation.extensions

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ge.nlatsabidze.noxttontask.R
import java.text.SimpleDateFormat
import java.util.*


fun ImageView.setImage(url:String?) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_git_repository).into(this)
}

fun String.dateFormatter(): String {
    val dateInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val dateOutput = SimpleDateFormat("dd-MM-yyyy | HH:mm:ss")
    val date: Date = dateInput.parse(this)
    return dateOutput.format(date)
}

fun onSnack(view: View, text: String, color: Int) {
    val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
    snackbar.setActionTextColor(Color.BLUE)
    snackbar.view.setBackgroundColor(color)
    val view: View = snackbar.getView()
    val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    val params = view.layoutParams as FrameLayout.LayoutParams
    view.layoutParams = params
    params.gravity = Gravity.TOP
    textView.textSize = 13f
    snackbar.show()
}