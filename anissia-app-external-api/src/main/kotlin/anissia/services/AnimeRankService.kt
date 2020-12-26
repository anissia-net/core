package anissia.services

import anissia.domain.AnimeHit
import anissia.domain.AnimeStore
import anissia.dto.AnimeRankDto
import anissia.misc.As
import anissia.repository.AnimeHitHourRepository
import anissia.repository.AnimeHitRepository
import anissia.repository.AnimeStoreRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AnimeRankService(
    private val animeHitRepository: AnimeHitRepository,
    private val animeHitHourRepository: AnimeHitHourRepository,
    private val animeStoreRepository: AnimeStoreRepository
) {
    
    fun getRank(type: String): String = when (type) {
        "day", "week", "month" -> animeStoreRepository.findById("rank.$type").map { it.data }.orElse("[]")
        else -> "[]"
    }
    
    /**
     * anime rank system batch
     */
    @Transactional
    fun animeRankBatch() {
        // step 1. remove hit history older then 96 days
        animeHitHourRepository.deleteByHourLessThan(LocalDateTime.now().minusDays(96).format(As.DTF_RANK_HOUR))
        // step 2. merge anime hits
        mergeAnimeHit()
        // step 3. extract and bind rank
        extractRank()
    }

    @Async
    fun hitAsync(animeNo: Long, ip: String, hour: String = LocalDateTime.now().format(As.DTF_RANK_HOUR)) =
        animeHitRepository.save(AnimeHit(animeNo = animeNo, ip = ip, hour = hour))

    private fun mergeAnimeHit() {
        val hour = LocalDateTime.now().format(As.DTF_RANK_HOUR)
        animeHitRepository.extractAllAnimeHitHour(hour)
            .map { r -> animeHitHourRepository.findById(r.key).map { it.hit += r.hit; it }.orElse(r) }
            .also { animeHitHourRepository.saveAll(it) }
            .also { animeHitRepository.deleteByHourLessThan(hour) }
    }

    private fun extractRank() {
        val dt = LocalDateTime.now()
        val day48List = extractRank(dt.minusDays(48).format(As.DTF_RANK_HOUR))
        val day28List = extractRank(dt.minusDays(28).format(As.DTF_RANK_HOUR)).apply { bindDiff(this, day48List) }
        val day7List = extractRank(dt.minusDays(7).format(As.DTF_RANK_HOUR)).apply { bindDiff(this, day28List) }
        val day1List = extractRank(dt.minusHours(24).format(As.DTF_RANK_HOUR)).apply { bindDiff(this, day7List) }
        animeStoreRepository.save(AnimeStore("rank.day", "", As.toJsonString(day1List.subList(0, 30))))
        animeStoreRepository.save(AnimeStore("rank.week", "", As.toJsonString(day7List.subList(0, 30))))
        animeStoreRepository.save(AnimeStore("rank.month", "", As.toJsonString(day28List.subList(0, 30))))
    }

    private fun extractRank(startHour: String) =
        animeHitHourRepository
            .extractAllAnimeRank(startHour)
            .filter { it.subject != "" } // remove not exist anime
            .apply {
                var rank = 0
                var hit = -1L
                forEachIndexed { index, node ->
                    if (node.hit != hit) {
                        hit = node.hit
                        rank = index + 1
                    }
                    node.rank = rank
                }
            }

    private fun bindDiff(nowList: List<AnimeRankDto>, prevList: List<AnimeRankDto>) =
        nowList.forEach { now -> prevList.find { prev -> prev.animeNo == now.animeNo }?.also { prev -> now.diff = -(now.rank - prev.rank) } }
}