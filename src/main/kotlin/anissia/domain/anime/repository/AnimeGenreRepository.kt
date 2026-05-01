package anissia.domain.anime.repository

import anissia.domain.anime.AnimeGenre
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AnimeGenreRepository : CoroutineCrudRepository<AnimeGenre, String> { //, QuerydslPredicateExecutor<AnimeGenre> {
    fun countByGenreIn(genre: List<String>): Long
}
