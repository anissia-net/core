package anissia.domain

import anissia.domain.account.AccountRole
import anissia.domain.session.model.SessionItem
import gs.shared.FailException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("SessionItem 권한 및 유효성")
class SessionItemTest {

    @Test
    fun `an 이 0 이면 비로그인 상태이다`() {
        val s = SessionItem()
        assertFalse(s.isLogin)
        assertFalse(s.isAdmin)
        assertFalse(s.isRoot)
        assertFalse(s.isTranslator)
    }

    @Test
    fun `an 이 양수이고 ROOT 롤이면 root, admin 모두 true 이다`() {
        val s = SessionItem(an = 1L, roles = listOf(AccountRole.ROOT.name))
        assertTrue(s.isLogin)
        assertTrue(s.isRoot)
        assertTrue(s.isAdmin)
    }

    @Test
    fun `TRANSLATOR 만 있으면 admin true, root false 이다`() {
        val s = SessionItem(an = 2L, roles = listOf(AccountRole.TRANSLATOR.name))
        assertTrue(s.isAdmin)
        assertTrue(s.isTranslator)
        assertFalse(s.isRoot)
    }

    @Test
    fun `validateLogin 은 비로그인 시 FailException 을 던진다`() {
        val s = SessionItem()
        val ex = assertThrows(FailException::class.java) { s.validateLogin() }
        assertEquals("로그인이 필요합니다.", ex.message)
    }

    @Test
    fun `validateAdmin 은 관리자가 아닐 때 FailException 을 던진다`() {
        val s = SessionItem(an = 1L) // 로그인은 됐지만 권한 없음
        val ex = assertThrows(FailException::class.java) { s.validateAdmin() }
        assertEquals("권한이 없습니다.", ex.message)
    }

    @Test
    fun `validateRoot 는 root 가 아닐 때 FailException 을 던진다`() {
        val s = SessionItem(an = 1L, roles = listOf(AccountRole.TRANSLATOR.name))
        assertThrows(FailException::class.java) { s.validateRoot() }
    }

    @Test
    fun `로그인 상태에서 validateLogin 은 정상 통과한다`() {
        val s = SessionItem(an = 10L)
        s.validateLogin() // throw 안 함
    }

    @Test
    fun `관리자(ROOT) 는 validateAdmin, validateRoot 모두 통과한다`() {
        val s = SessionItem(an = 10L, roles = listOf(AccountRole.ROOT.name))
        s.validateAdmin()
        s.validateRoot()
    }
}
