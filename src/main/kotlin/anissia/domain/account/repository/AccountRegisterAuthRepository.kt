package anissia.domain.account.repository

import anissia.domain.account.AccountRegisterAuth
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

interface AccountRegisterAuthRepository : CoroutineCrudRepository<AccountRegisterAuth, Long> { //, QuerydslPredicateExecutor<AccountRegisterAuth> {

    suspend fun existsByEmailAndExpDtAfter(email: String, expDt: OffsetDateTime): Boolean

    suspend fun findByNoAndTokenAndExpDtAfterAndUsedDtNull(no: Long, token: String, expDt: OffsetDateTime): AccountRegisterAuth?

    @Transactional
    @Modifying
    @Query("DELETE FROM AccountRegisterAuth WHERE expDt < :expDt")
    suspend fun deleteAllByExpDtBefore(expDt: OffsetDateTime = OffsetDateTime.now().minusDays(30))
}
