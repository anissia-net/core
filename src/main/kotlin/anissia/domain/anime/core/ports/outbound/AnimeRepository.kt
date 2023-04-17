package anissia.domain.anime.core.ports.outbound

import anissia.domain.anime.core.Anime
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AnimeRepository : JpaRepository<Anime, Long> { //, QuerydslPredicateExecutor<Anime> {

    @Query("SELECT A FROM Anime A WHERE A.status <> anissia.domain.anime.core.AnimeStatus.END AND A.week = :week")
    fun findAllSchedule(week: String): List<Anime>

    fun findAllByOrderByAnimeNoDesc(pageable: Pageable): Page<Anime>

    fun findAllByAnimeNoInOrderByAnimeNoDesc(animeNo: Collection<Long>): List<Anime>

    fun existsBySubject(subject: String): Boolean

    fun existsBySubjectAndAnimeNoNot(subject: String, animeNo: Long): Boolean

    @EntityGraph(attributePaths = ["captions"])
    fun findWithCaptionsByAnimeNo(animeNo: Long): Anime?

    @Modifying
    @Query("UPDATE Anime A SET A.captionCount = size(A.captions) WHERE A.animeNo = :animeNo")
    fun updateCaptionCount(animeNo: Long): Int

    @Query("SELECT concat(a.anime_no, ' ', a.subject) FROM anime a WHERE a.autocorrect LIKE concat(:autocorrect, '%')", nativeQuery = true)
    fun findTop10ByAutocorrectStartsWith(autocorrect: String, pageable: Pageable = PageRequest.of(0, 10)): List<String>
}
