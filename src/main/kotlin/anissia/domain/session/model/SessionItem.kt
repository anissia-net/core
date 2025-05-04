package anissia.domain.session.model

import anissia.domain.account.Account
import anissia.domain.account.AccountRole
import anissia.infrastructure.common.As
import com.fasterxml.jackson.annotation.JsonIgnore
import gs.shared.FailException

class SessionItem (
        val an: Long = 0,
        val name: String = "",
        val email: String = "",
        val roles: List<String> = listOf(),
        val ip: String = "",
) {
    companion object {
        val sessionException = FailException("세션정보가 만료되었습니다.\n다시 로그인해주세요.")

        fun cast(account: Account, ip: String): SessionItem {
            if (account.isBan) {
                throw FailException("차단된 계정입니다.\n해제일 ${account.banExpireDt!!.format(As.DTF_USER_YMDHMS)}")
            }
            return SessionItem(
                an = account.an,
                name = account.name,
                email = account.email,
                roles = account.roles.map { it.name },
                ip = ip
            )
        }
    }

    fun validateLogin() {
        if (!isLogin) throw FailException("로그인이 필요합니다.")
    }

    fun validateAdmin() {
        if (!isAdmin) throw FailException("권한이 없습니다.")
    }

    fun validateRoot() {
        if (!isRoot) throw FailException("권한이 없습니다.")
    }

    fun validateSession(account: Account?) {
        validateLogin()
        account?.takeIf { account ->
            !account.isBan &&
                account.name == this.name &&
                account.email == this.email &&
                As.same(account.roles.map { it.name }, this.roles)
        } ?: throw sessionException
    }

    @get:JsonIgnore
    val isAdmin: Boolean get() = isLogin && (roles.contains(AccountRole.TRANSLATOR.name) || roles.contains(AccountRole.ROOT.name))
    @get:JsonIgnore
    val isTranslator: Boolean get() = isLogin && roles.contains(AccountRole.TRANSLATOR.name)
    @get:JsonIgnore
    val isRoot: Boolean get() = isLogin && roles.contains(AccountRole.ROOT.name)
    @get:JsonIgnore
    val isLogin: Boolean get() = an > 0L
}
