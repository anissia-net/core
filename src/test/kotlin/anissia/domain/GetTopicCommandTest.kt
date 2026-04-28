package anissia.domain

import anissia.domain.board.command.GetTopicCommand
import anissia.domain.board.command.GetTopicListCommand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("GetTopicCommand / GetTopicListCommand 기본값")
class GetTopicCommandTest {

    @Test
    fun `GetTopicCommand 기본값`() {
        val cmd = GetTopicCommand()
        assertEquals("", cmd.ticker)
        assertEquals(0L, cmd.topicNo)
    }

    @Test
    fun `GetTopicListCommand 기본값과 page 변경`() {
        val cmd = GetTopicListCommand()
        assertEquals("", cmd.ticker)
        assertEquals(0, cmd.page)
        cmd.page = 3
        assertEquals(3, cmd.page)
    }
}
