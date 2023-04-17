package anissia.domain.anime.core.model

class UpdateAnimeDocumentCommand(
    val animeNo: Long,
    val isDelete: Boolean = false
) {
    fun validate() {
        require(animeNo > 0) { "animeNo must be greater than 0" }
    }
}
