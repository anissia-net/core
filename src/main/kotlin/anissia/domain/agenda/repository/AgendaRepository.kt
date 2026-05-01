package anissia.domain.agenda.repository

import anissia.domain.agenda.Agenda
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.time.OffsetDateTime

interface AgendaRepository : CoroutineCrudRepository<Agenda, Long> { //, QuerydslPredicateExecutor<Agenda> {

    suspend fun findAllByCodeAndStatusOrderByAgendaNoDesc(code: String, status: String, pageable: Pageable = PageRequest.of(0, 100)): Page<Agenda>

    suspend fun findAllByCodeOrderByStatusAscAgendaNoDesc(code: String, pageable: Pageable = PageRequest.of(0, 100)): Page<Agenda>

    suspend fun countByCodeAndStatus(code: String, status: String): Int

    suspend fun existsByCodeAndStatusAndAn(code: String, status: String, an: Long): Boolean

    suspend fun existsByCodeAndStatusAndAnAndUpdDtAfter(code: String, status: String, an: Long, updDt: OffsetDateTime): Boolean

    @Modifying
    @Query("DELETE FROM Agenda a WHERE a.code = 'ANIME-DEL' and a.updDt < :baseDateTime")
    suspend fun deleteDeletePadding(baseDateTime: OffsetDateTime = OffsetDateTime.now().minusDays(30)): Int


    @Query("SELECT a FROM Agenda a WHERE a.an = :an AND a.code = 'TRANSLATOR-APPLY' and a.status = 'DONE' ORDER BY a.agendaNo DESC")
    suspend fun findPassedTranslatorApply(an: Long): List<Agenda>
}
