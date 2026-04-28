package anissia.domain

import anissia.domain.anime.command.EditAnimeCommand
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.bind.MethodArgumentNotValidException

@DisplayName("EditAnimeCommand.validate")
class EditAnimeCommandTest {

    private fun valid() = EditAnimeCommand(
        animeNo = 10,
        status = "ON",
        week = "1",
        time = "12:30",
        subject = "수정 애니",
        originalSubject = "Original",
        genres = "액션",
        startDate = "2024-01-01",
        endDate = "2024-12-31",
        website = "https://a.com",
        twitter = "https://t.com",
        note = "메모"
    )

    @Test
    fun `정상 입력은 검증을 통과한다`() {
        assertDoesNotThrow { valid().validate() }
    }

    @Test
    fun `animeNo 가 0 이하이면 IllegalArgumentException`() {
        val cmd = EditAnimeCommand(
            animeNo = 0, status = "ON", week = "1", time = "12:00",
            subject = "s", genres = "액션"
        )
        assertThrows(IllegalArgumentException::class.java) { cmd.validate() }
    }

    @Test
    fun `note 길이가 512 초과이면 400 예외`() {
        val cmd = EditAnimeCommand(
            animeNo = 1, status = "ON", week = "1", time = "12:00",
            subject = "s", genres = "액션",
            note = "x".repeat(513)
        )
        assertThrows(MethodArgumentNotValidException::class.java) { cmd.validate() }
    }
}
