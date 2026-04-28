package anissia.domain

import anissia.domain.board.command.NewTopicCommand
import gs.shared.FailException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("NewTopicCommand.validate")
class NewTopicCommandTest {

    @Test
    fun `ticker 가 비면 FailException(기본 메시지)`() {
        val ex = assertThrows(FailException::class.java) {
            NewTopicCommand(ticker = "", topic = "t", content = "c").validate()
        }
        assertEquals("알수없는 오류입니다.", ex.message)
    }

    @Test
    fun `topic 이 비면 FailException - 제목 메시지`() {
        val ex = assertThrows(FailException::class.java) {
            NewTopicCommand(ticker = "free", topic = "  ", content = "c").validate()
        }
        assertEquals("제목을 입력해 주세요.", ex.message)
    }

    @Test
    fun `content 가 비면 FailException - 내용 메시지`() {
        val ex = assertThrows(FailException::class.java) {
            NewTopicCommand(ticker = "free", topic = "t", content = "").validate()
        }
        assertEquals("내용을 입력해 주세요.", ex.message)
    }

    @Test
    fun `정상이면 통과`() {
        assertDoesNotThrow {
            NewTopicCommand(ticker = "free", topic = "t", content = "c").validate()
        }
    }
}
