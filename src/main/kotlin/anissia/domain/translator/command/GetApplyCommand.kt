package anissia.domain.translator.command

class GetApplyCommand(
    val applyNo: Long = 0
) {
    fun validate() {
        require(applyNo > 0) { "잘못된 번호" }
    }
}
