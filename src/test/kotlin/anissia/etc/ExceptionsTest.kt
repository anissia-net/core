package anissia.etc

import gs.shared.ErrorException
import gs.shared.FailException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExceptionsTest {

    @Test
    fun `FailException 기본 메시지는 알수없는 오류이다`() {
        val e = FailException()
        assertEquals("알수없는 오류입니다.", e.message)
    }

    @Test
    fun `FailException 메시지를 직접 지정할 수 있다`() {
        val e = FailException("커스텀 오류")
        assertEquals("커스텀 오류", e.message)
    }

    @Test
    fun `ErrorException 메시지를 직접 지정할 수 있다`() {
        val e = ErrorException("심각 오류")
        assertEquals("심각 오류", e.message)
    }
}
