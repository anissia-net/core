package anissia.domain.board.repository

import anissia.domain.board.BoardTicker
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BoardTickerRepository : CoroutineCrudRepository<BoardTicker, String> { //, QuerydslPredicateExecutor<BoardTicker> {

//    @EntityGraph(attributePaths = ["user"])
//    fun findAllWithUserByCodeOrderByBnDesc(code: String, pageable: Pageable): Page<BoardTopic>
//
//    @EntityGraph(attributePaths = ["user"])
//    fun findAllWithUserBy(pageable: Pageable): List<BoardTopic>
//
//    @EntityGraph(attributePaths = ["user"])
//    fun findWithUserByBn(bn: Long): Optional<BoardTopic>
}
