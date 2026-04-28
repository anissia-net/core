package anissia.infrastructure.common

class RecordSplitor(
    val version: String,
    val recordCount: Int,
    val separator: Char
) {
    constructor(version: String, recordCount: Int): this(version, recordCount, 0x1e.toChar())

    private val separatorStr = separator.toString()

    init {
        if (version.isEmpty()) {
            throw IllegalArgumentException("version must not be empty")
        }
        if (version.contains(separator)) {
            throw IllegalArgumentException("version must not contain separator")
        }
        if (recordCount <= 0) {
            throw IllegalArgumentException("minimum record count is 1, but was $recordCount")
        }
        if (separatorStr.isEmpty()) {
            throw IllegalArgumentException("separator must not be empty")
        }
    }

    fun read(str: String): List<String> {
        val split = str.split(separator)
        return if (recordCount == (split.size - 1) && split[0] == version) {
            split.subList(1, split.size)
        } else {
            listOf()
        }
    }

    fun write(vararg list: String): String {
        return "${version}${separator}${list.joinToString(separatorStr)}"
    }
}
