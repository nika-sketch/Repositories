package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    @Json(name = "avatar_url")
    val ownerAvatarUrl: String?,
    @Json(name = "html_url")
    val ownerHtmlUrl: String?,
    @Json(name = "id")
    val ownerId: Int?,
    @Json(name = "login")
    val ownerLogin: String?,
    @Json(name = "node_id")
    val ownerNodeId: String?,
    @Json(name = "url")
    val ownerUrl: String?
) : Parcelable