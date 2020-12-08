package anissia.repository

import anissia.domain.AnimeGenre
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface AnimeGenreRepository : JpaRepository<AnimeGenre, String>, QuerydslPredicateExecutor<AnimeGenre>