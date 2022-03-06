package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories

data class GithubRepository(
    val incomplete_results: Boolean?,
    val items: List<Item>?,
    val total_count: Int?
)