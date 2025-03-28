package anissia.domain.activePanel.command

class GetListActivePanelCommand(
    val mode: String,
    val page: Int
) {
    fun validate() {
        require(mode in listOf("public", "admin")) { "지원하지 않는 mode 입니다." }
        require(page >= 0) { "잘못된 page 입니다." }
    }
}
