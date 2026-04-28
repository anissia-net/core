package anissia.domain

import anissia.domain.board.command.NewPostCommand
import gs.shared.FailException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("NewPostCommand.validate")
class NewPostCommandTest {

    @Test
    fun `topicNo 가 0 이하이면 FailException - 기본 메시지`() {
        val ex = assertThrows(FailException::class.java) {
            NewPostCommand(topicNo = 0, content = "abc").validate()
        }
        assertEquals("알수없는 오류입니다.", ex.message)
    }

    @Test
    fun `content 가 비면 FailException - 내용 메시지`() {
        val ex = assertThrows(FailException::class.java) {
            NewPostCommand(topicNo = 1, content = "  ").validate()
        }
        assertEquals("내용을 입력해 주세요.", ex.message)
    }

    @Test
    fun `정상이면 통과`() {
        assertDoesNotThrow {
            NewPostCommand(topicNo = 1, content = "hi").validate()
        }
    }
}
