package anissia.domain.anime.command

class UpdateAnimeDocumentCommand(
    val animeNo: Long = 0,
    val isDelete: Boolean = false
) {
    fun validate() {
        require(animeNo > 0) { "animeNo must be greater than 0" }
    }
}
