package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.Owner
import kotlinx.parcelize.Parcelize

@Entity(tableName = "repositories")
@Parcelize
data class Item(
    @Json(name = "id")
    @PrimaryKey var id: Int?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "default_branch")
    val defaultBranch: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "fork")
    val fork: Boolean?,
    @Json(name = "forks")
    val forks: Int?,
    @Json(name = "forks_count")
    val forksCount: Int?,
    @Json(name = "full_name")
    val fullName: String?,
    @Json(name = "has_pages")
    val hasPages: Boolean?,
    @Json(name = "html_url")
    val htmlUrl: String?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "node_id")
    val nodeId: String?,
    @Json(name = "open_issues_count")
    val openIssuesCount: Int?,
    @Json(name = "owner")
    @Embedded val owner: Owner?,
    @Json(name = "pushed_at")
    val pushedAt: String?,
    @Json(name = "releases_url")
    val releasesUrl: String?,
    @Json(name = "stargazers_count")
    val stargazersCount: Int?,
    @Json(name = "stargazers_url")
    val stargazersUrl: String?,
    @Json(name = "statuses_url")
    val statusesUrl: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "watchers")
    val watchers: Int?,
    @Json(name = "watchers_count")
    val watchersCount: Int?
) : Parcelable
