package anissia.domain.board.repository

import anissia.domain.board.BoardPost
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query

interface BoardPostRepository : CoroutineCrudRepository<BoardPost, Long> { //, QuerydslPredicateExecutor<BoardPost> {


    @EntityGraph(attributePaths = ["account"])
    fun findAllWithAccountByTopicNoOrderByPostNo(topicNo: Long): List<BoardPost>

    @EntityGraph(attributePaths = ["account"])
    fun findWithAccountByTopicNoAndRootIsTrue(topicNo: Long): BoardPost?

    @Modifying
    @Query("DELETE FROM BoardPost A WHERE A.topicNo = :topicNo")
    fun deleteAllByTopicNo(topicNo: Long): Int

//    @EntityGraph(attributePaths = ["user"])
//    fun findAllWithUserByCodeOrderByBnDesc(code: String, pageable: Pageable): Page<BoardTopic>
//
//    @EntityGraph(attributePaths = ["user"])
//    fun findAllWithUserBy(pageable: Pageable): List<BoardTopic>
//
//    @EntityGraph(attributePaths = ["user"])
//    fun findWithUserByBn(bn: Long): Optional<BoardTopic>
}
