package anissia.domain.anime.repository

import anissia.domain.anime.AnimeHit
import anissia.domain.anime.AnimeHitHour
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query

interface AnimeHitRepository : CoroutineCrudRepository<AnimeHit, Long> { //, QuerydslPredicateExecutor<AnimeHit> {
    @Modifying
    @Query("DELETE FROM AnimeHit WHERE hour < :hour")
    fun deleteByHourLessThan(hour: Long): Int

    @Query(
        """
        SELECT
            new anissia.domain.anime.AnimeHitHour(a.hour, a.animeNo, count(distinct a.ip))
        FROM AnimeHit a
        WHERE a.hour < :hour
        GROUP BY a.hour, a.animeNo
    """
    )
    fun extractAllAnimeHitHour(hour: Long): List<AnimeHitHour>
}
