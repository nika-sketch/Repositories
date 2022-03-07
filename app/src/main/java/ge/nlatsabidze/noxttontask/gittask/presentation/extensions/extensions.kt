package ge.nlatsabidze.noxttontask.gittask.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
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