package anissia.domain.anime.core.ports.outbound

import anissia.domain.anime.core.AnimeHit
import anissia.domain.anime.core.AnimeHitHour
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AnimeHitRepository : JpaRepository<AnimeHit, Long> { //, QuerydslPredicateExecutor<AnimeHit> {
    @Modifying
    @Query("DELETE FROM AnimeHit WHERE hour < :hour")
    fun deleteByHourLessThan(hour: Long): Int

    @Query(
        """
        SELECT
            new anissia.domain.anime.core.AnimeHitHour(a.hour, a.animeNo, count(distinct a.ip))
        FROM AnimeHit a
        WHERE a.hour < :hour
        GROUP BY a.hour, a.animeNo
    """
    )
    fun extractAllAnimeHitHour(hour: Long): List<AnimeHitHour>
}
