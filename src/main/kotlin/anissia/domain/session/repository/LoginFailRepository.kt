package anissia.domain.session.repository

import anissia.domain.session.LoginFail
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

interface LoginFailRepository : CoroutineCrudRepository<LoginFail, Long> {

    fun countByIpAndEmailAndFailDtAfter(ip: String, email: String, failDt: OffsetDateTime): Long

    @Modifying
    @Query("DELETE FROM LoginFail WHERE ip = :ip AND email = :email")
    fun deleteByIpAndEmail(ip: String, email: String)

    @Transactional
    @Modifying
    @Query("DELETE FROM LoginFail WHERE failDt < :failDt")
    fun deleteAllByFailDtBefore(failDt: OffsetDateTime = OffsetDateTime.now().minusDays(90))
}
