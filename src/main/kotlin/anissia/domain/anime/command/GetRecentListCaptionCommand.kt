package anissia.domain.anime.command

class GetRecentListCaptionCommand(
    val page: Int = 0
) {
    fun validate() {
        require(page > -2) { "잘못된 pageNo" }
    }
}
