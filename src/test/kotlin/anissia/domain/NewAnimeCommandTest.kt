package anissia.domain

import anissia.domain.anime.command.NewAnimeCommand
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.bind.MethodArgumentNotValidException

@DisplayName("NewAnimeCommand.validate")
class NewAnimeCommandTest {

    private fun valid() = NewAnimeCommand(
        status = "ON",
        week = "1",
        time = "12:30",
        subject = "신작 애니",
        originalSubject = "Original",
        genres = "액션,코미디",
        startDate = "2024-01-01",
        endDate = "2024-12-31",
        website = "https://a.com",
        twitter = "https://t.com",
    )

    @Test
    fun `정상 입력은 검증을 통과한다`() {
        // 정상 케이스에서는 IllegalArgumentException/400 둘 다 던지지 않아야 한다.
        assertDoesNotThrow { valid().validate() }
    }

    @Test
    fun `time 이 형식과 다르면 IllegalArgumentException`() {
        val cmd = NewAnimeCommand(
            status = "ON", week = "1", time = "1:2",
            subject = "s", genres = "액션"
        )
        assertThrows(IllegalArgumentException::class.java) { cmd.validate() }
    }

    @Test
    fun `week 가 0~8 외이면 IllegalArgumentException`() {
        val cmd = NewAnimeCommand(
            status = "ON", week = "9", time = "12:00",
            subject = "s", genres = "액션"
        )
        assertThrows(IllegalArgumentException::class.java) { cmd.validate() }
    }

    @Test
    fun `subject 가 공백이면 400 예외`() {
        val cmd = NewAnimeCommand(
            status = "ON", week = "0", time = "12:00",
            subject = "   ", genres = "액션"
        )
        assertThrows(MethodArgumentNotValidException::class.java) { cmd.validate() }
    }

    @Test
    fun `subject 앞뒤 공백이 있으면 400 예외`() {
        val cmd = valid()
        val bad = NewAnimeCommand(
            status = cmd.status, week = cmd.week, time = cmd.time,
            subject = " 신작 ", originalSubject = cmd.originalSubject,
            genres = cmd.genres, startDate = cmd.startDate, endDate = cmd.endDate,
            website = cmd.website, twitter = cmd.twitter
        )
        assertThrows(MethodArgumentNotValidException::class.java) { bad.validate() }
    }

    @Test
    fun `존재하지 않는 status 값이면 400 예외`() {
        val cmd = NewAnimeCommand(
            status = "UNKNOWN", week = "1", time = "12:00",
            subject = "s", genres = "액션"
        )
        assertThrows(MethodArgumentNotValidException::class.java) { cmd.validate() }
    }

    @Test
    fun `genres 가 비었으면 400 예외`() {
        val cmd = NewAnimeCommand(
            status = "ON", week = "1", time = "12:00",
            subject = "s", genres = ""
        )
        assertThrows(MethodArgumentNotValidException::class.java) { cmd.validate() }
    }

    @Test
    fun `genres 가 4개 이상이면 400 예외`() {
        val cmd = NewAnimeCommand(
            status = "ON", week = "1", time = "12:00",
            subject = "s", genres = "a,b,c,d"
        )
        assertThrows(MethodArgumentNotValidException::class.java) { cmd.validate() }
    }

    @Test
    fun `startDate 가 endDate 보다 미래이면 400 예외`() {
        val cmd = NewAnimeCommand(
            status = "ON", week = "1", time = "12:00",
            subject = "s", genres = "액션",
            startDate = "2024-12-31", endDate = "2024-01-01"
        )
        assertThrows(MethodArgumentNotValidException::class.java) { cmd.validate() }
    }

    @Test
    fun `website 가 http 또는 https 로 시작하지 않으면 400 예외`() {
        val cmd = NewAnimeCommand(
            status = "ON", week = "1", time = "12:00",
            subject = "s", genres = "액션",
            website = "ftp://x"
        )
        assertThrows(MethodArgumentNotValidException::class.java) { cmd.validate() }
    }

    @Test
    fun `twitter 가 http 또는 https 로 시작하지 않으면 400 예외`() {
        val cmd = NewAnimeCommand(
            status = "ON", week = "1", time = "12:00",
            subject = "s", genres = "액션",
            twitter = "ftp://x"
        )
        assertThrows(MethodArgumentNotValidException::class.java) { cmd.validate() }
    }

    @Test
    fun `genresList 는 콤마로 분리한 결과를 반환한다`() {
        val cmd = NewAnimeCommand(genres = "a,b,c")
        val list = cmd.genresList
        assert(list == listOf("a", "b", "c"))
    }
}
