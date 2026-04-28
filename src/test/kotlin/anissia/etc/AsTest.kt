package anissia.etc

import anissia.infrastructure.common.As
import anissia.infrastructure.common.As.Companion.decodeBase64Url
import anissia.infrastructure.common.As.Companion.encodeBase64Url
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.MethodArgumentNotValidException

@DisplayName("As 유틸 함수")
class AsTest {

    @Test
    fun `IS_MAIL_REGEX 는 올바른 메일을 통과시키고 잘못된 메일은 거부한다`() {
        assertTrue(As.IS_MAIL_REGEX.matches("user@example.com"))
        assertTrue(As.IS_MAIL_REGEX.matches("a_b-c@sub.example.co.kr"))
        assertFalse(As.IS_MAIL_REGEX.matches("not-a-email"))
        assertFalse(As.IS_MAIL_REGEX.matches("a@b"))
        assertFalse(As.IS_MAIL_REGEX.matches("@example.com"))
    }

    @Test
    fun `isWebSite 는 http 또는 https 시작을 허용한다`() {
        assertTrue(As.isWebSite("http://a.com"))
        assertTrue(As.isWebSite("https://a.com"))
        assertFalse(As.isWebSite("ftp://a.com"))
        assertFalse(As.isWebSite("a.com"))
    }

    @Test
    fun `isWebSite 는 allowEmpty 가 true 이면 빈 문자열을 허용한다`() {
        assertTrue(As.isWebSite("", true))
        assertFalse(As.isWebSite("", false))
    }

    @Test
    fun `isAsAnimeDate 는 빈 문자열, 정상날짜, 99 패턴을 허용한다`() {
        assertTrue(As.isAsAnimeDate(""))
        assertTrue(As.isAsAnimeDate("2024-01-15"))
        assertTrue(As.isAsAnimeDate("2024-01-99"))
        assertTrue(As.isAsAnimeDate("2024-99-99"))
    }

    @Test
    fun `isAsAnimeDate 는 잘못된 형식을 거부한다`() {
        assertFalse(As.isAsAnimeDate("2024/01/15"))
        assertFalse(As.isAsAnimeDate("2024-1-1"))
        assertFalse(As.isAsAnimeDate("abcd-ef-gh"))
        assertFalse(As.isAsAnimeDate("2024-13-01"))
    }

    @Test
    fun `encodeBase64Url 와 decodeBase64Url 는 서로 역연산이다`() {
        val origin = "안녕하세요 hello!?"
        val encoded = encodeBase64Url(origin)
        assertEquals(origin, decodeBase64Url(encoded))
    }

    @Test
    fun `same 은 길이가 같고 정렬 후 같은 원소를 가지면 true 이다`() {
        assertTrue(As.same(listOf<Int>(), listOf<Int>()))
        assertTrue(As.same(listOf(1, 2, 3), listOf(3, 2, 1)))
        assertTrue(As.same(listOf("a", "b"), listOf("b", "a")))
    }

    @Test
    fun `same 은 길이가 다르거나 원소가 다르면 false 이다`() {
        assertFalse(As.same(listOf(1, 2), listOf(1, 2, 3)))
        assertFalse(As.same(listOf(1, 2, 3), listOf(1, 2, 4)))
    }

    @Test
    fun `replacePage 는 동일한 pageable과 totalElements 를 유지하며 컨텐츠만 교체한다`() {
        val original = PageImpl(listOf(1L, 2L, 3L), PageRequest.of(0, 30), 100L)
        val replaced = As.replacePage(original, listOf("a", "b", "c"))
        assertEquals(listOf("a", "b", "c"), replaced.content)
        assertEquals(100L, replaced.totalElements)
        assertEquals(original.pageable, replaced.pageable)
    }

    @Test
    fun `filterPage 는 주어진 조건으로 필터링한 컨텐츠를 반환한다`() {
        val origin = PageImpl(listOf(1, 2, 3, 4), PageRequest.of(0, 30), 4L)
        val filtered = As.filterPage(origin) { it % 2 == 0 }
        assertEquals(listOf(2, 4), filtered.content)
        assertEquals(2L, filtered.totalElements)
    }

    @Test
    fun `throwHttp400 은 MethodArgumentNotValidException 을 던진다`() {
        val ex = assertThrows(MethodArgumentNotValidException::class.java) {
            As.throwHttp400("잘못된 요청")
        }
        assertTrue(ex.bindingResult.allErrors.any { "잘못된 요청" in (it.defaultMessage ?: "") })
    }

    @Test
    fun `throwHttp400If 는 조건이 true 일 때만 예외를 던진다`() {
        // false 일 때는 예외 없음
        As.throwHttp400If("err", false)
        // true 일 때 예외
        assertThrows(MethodArgumentNotValidException::class.java) {
            As.throwHttp400If("err", true)
        }
    }

    @Test
    fun `throwHttp400Exception 는 내부 람다가 예외를 던지면 400 으로 변환한다`() {
        assertThrows(MethodArgumentNotValidException::class.java) {
            As.throwHttp400Exception("변환됨") { error("inside") }
        }
    }

    @Test
    fun `toJsonString 은 객체를 JSON 으로 직렬화한다`() {
        val json = As.toJsonString(mapOf("a" to 1, "b" to "x"))
        assertTrue(json.contains("\"a\":1"))
        assertTrue(json.contains("\"b\":\"x\""))
    }
}
