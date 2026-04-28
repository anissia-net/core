package anissia.domain

import anissia.domain.anime.command.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("간단한 Anime Command 들")
class SimpleAnimeCommandsTest {

    @Test
    fun `DeleteAnimeCommand - 0이면 IllegalArgumentException`() {
        assertThrows(IllegalArgumentException::class.java) {
            DeleteAnimeCommand(animeNo = 0).validate()
        }
    }

    @Test
    fun `DeleteAnimeCommand - 양수면 통과`() {
        assertDoesNotThrow { DeleteAnimeCommand(animeNo = 1).validate() }
    }

    @Test
    fun `RecoverAnimeCommand - 0이면 IllegalArgumentException`() {
        assertThrows(IllegalArgumentException::class.java) {
            RecoverAnimeCommand(agendaNo = 0).validate()
        }
    }

    @Test
    fun `RecoverAnimeCommand - 양수면 통과`() {
        assertDoesNotThrow { RecoverAnimeCommand(agendaNo = 5).validate() }
    }

    @Test
    fun `GetAnimeCommand 기본값은 0이다`() {
        assertEquals(0L, GetAnimeCommand().animeNo)
    }

    @Test
    fun `GetAnimeListCommand 기본값`() {
        val cmd = GetAnimeListCommand()
        assertEquals("", cmd.q)
        assertEquals(0, cmd.page)
    }

    @Test
    fun `GetAutocorrectAnimeCommand 기본값`() {
        assertEquals("", GetAutocorrectAnimeCommand().q)
    }
}
