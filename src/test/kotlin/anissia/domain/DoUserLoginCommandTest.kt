package anissia.domain


import anissia.domain.session.command.DoUserLoginCommand
import gs.shared.FailException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("DoUserLoginCommand.validate")
class DoUserLoginCommandTest {

    @Test
    fun `이메일 형식이 잘못되면 FailException - 이메일 메시지`() {
        val ex = assertThrows(FailException::class.java) {
            DoUserLoginCommand(email = "notmail", password = "12345678").validate()
        }
        assertEquals("아이디는 E-MAIL 형식입니다.", ex.message)
    }

    @Test
    fun `이메일이 너무 짧으면 FailException`() {
        val ex = assertThrows(FailException::class.java) {
            DoUserLoginCommand(email = "a@b", password = "12345678").validate()
        }
        assertEquals("아이디는 E-MAIL 형식입니다.", ex.message)
    }

    @Test
    fun `비밀번호가 8자 미만이면 FailException - 암호 메시지`() {
        val ex = assertThrows(FailException::class.java) {
            DoUserLoginCommand(email = "user@example.com", password = "1234567").validate()
        }
        assertEquals("암호는 8자리 이상 128자리 이하로 작성해야합니다.", ex.message)
    }

    @Test
    fun `비밀번호가 128자 초과면 FailException`() {
        val ex = assertThrows(FailException::class.java) {
            DoUserLoginCommand(email = "user@example.com", password = "x".repeat(129)).validate()
        }
        assertEquals("암호는 8자리 이상 128자리 이하로 작성해야합니다.", ex.message)
    }

    @Test
    fun `정상 입력은 통과`() {
        assertDoesNotThrow {
            DoUserLoginCommand(email = "user@example.com", password = "12345678").validate()
        }
    }
}
