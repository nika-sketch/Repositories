package ge.nlatsabidze.noxttontask.gittask.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import ge.nlatsabidze.noxttontask.R

fun ImageView.setImage(url:String?) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_git_repository).into(this)
}