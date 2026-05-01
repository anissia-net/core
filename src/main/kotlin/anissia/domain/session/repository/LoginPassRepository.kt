package anissia.domain.session.repository

import anissia.domain.session.LoginPass
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

interface LoginPassRepository : CoroutineCrudRepository<LoginPass, Long> {


    @Transactional
    @Modifying
    @Query("DELETE FROM LoginPass WHERE passDt < :passDt")
    fun deleteAllByPassDtBefore(passDt: OffsetDateTime = OffsetDateTime.now().minusDays(90))
}
