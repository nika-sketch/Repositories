package ge.nlatsabidze.noxttontask.gittask.presentation.extensions

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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

fun showDialogError(message: String, context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setMessage(message)
    builder.setPositiveButton("yes") { _: DialogInterface, _: Int -> }
    builder.show()
}

fun findName(name: String): String {

    var firstHalf = ""
    for (i in name.indices) {
        firstHalf += name[i]
        if (name[i] == '/') {
            break
        }
    }
    firstHalf = firstHalf.dropLast(1)
    return firstHalf
}

fun findRepositoryName(repo: String): String {
    var index = 0
    var current = repo
    for (i in current.indices) {
        if (current[i] == '/') {
            index = i
        }
    }
    current = current.drop(1)

    return current.substring(index)
}