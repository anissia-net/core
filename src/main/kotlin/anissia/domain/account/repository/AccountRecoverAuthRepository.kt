package anissia.domain.account.repository

import anissia.domain.account.AccountRecoverAuth
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

interface AccountRecoverAuthRepository : CoroutineCrudRepository<AccountRecoverAuth, Long> { //, QuerydslPredicateExecutor<AccountRecoverAuth> {

    suspend fun existsByAnAndExpDtAfter(an: Long, expDt: OffsetDateTime): Boolean

    suspend fun findByNoAndTokenAndExpDtAfterAndUsedDtNull(no: Long, token: String, expDt: OffsetDateTime): AccountRecoverAuth?

    @Transactional
    @Modifying
    @Query("DELETE FROM AccountRecoverAuth WHERE expDt < :expDt")
    suspend fun deleteAllByExpDtBefore(expDt: OffsetDateTime = OffsetDateTime.now().minusDays(30))
}
