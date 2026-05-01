package anissia.domain.activePanel.repository

import anissia.domain.activePanel.ActivePanel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

interface ActivePanelRepository : CoroutineCrudRepository<ActivePanel, Long> { //, QuerydslPredicateExecutor<ActivePanel> {

    fun findAllByOrderByApNoDesc(pageable: Pageable): Page<ActivePanel>

    @Transactional
    @Modifying
    @Query("DELETE FROM ActivePanel WHERE regDt < :regDt")
    fun deleteAllByRegDtBefore(regDt: OffsetDateTime = OffsetDateTime.now().minusDays(90))
}
