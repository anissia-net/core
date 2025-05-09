package anissia.domain.board.service

import anissia.domain.account.service.UserService
import anissia.domain.activePanel.ActivePanel
import anissia.domain.activePanel.repository.ActivePanelRepository
import anissia.domain.board.BoardPost
import anissia.domain.board.BoardTopic
import anissia.domain.board.command.*
import anissia.domain.board.model.BoardTopicItem
import anissia.domain.board.repository.BoardPostRepository
import anissia.domain.board.repository.BoardTickerRepository
import anissia.domain.board.repository.BoardTopicRepository
import anissia.domain.session.model.SessionItem
import anissia.shared.ResultWrapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicServiceImpl(
    private val boardTopicRepository: BoardTopicRepository,
    private val boardPostRepository: BoardPostRepository,
    private val boardTickerRepository: BoardTickerRepository,
    private val activePanelRepository: ActivePanelRepository,
    private val userService: UserService,
): TopicService {
    override fun get(cmd: GetTopicCommand): BoardTopicItem =
        boardTopicRepository
            .findWithAccountByTickerAndTopicNo(cmd.ticker, cmd.topicNo)
            ?.let { BoardTopicItem(it, boardPostRepository.findAllWithAccountByTopicNoOrderByPostNo(it.topicNo)) }
            ?: BoardTopicItem()

    override fun getList(cmd: GetTopicListCommand): Page<BoardTopicItem> =
        boardTopicRepository
            .findAllWithAccountByTickerOrderByTickerAscFixedDescTopicNoDesc(cmd.ticker, PageRequest.of(cmd.page, 20))
            .map { BoardTopicItem(it) }

    override fun getMainRecent(): Map<String, List<Map<String, Any>>> =
        mapOf("notice" to getRecent("notice"), "inquiry" to getRecent("inquiry"))

    private fun getRecent(ticker: String): List<Map<String, Any>> =
        boardTopicRepository
            .findTop5ByTickerAndFixedOrderByTopicNoDesc(ticker)
            .map { mapOf(
                "topicNo" to it.topicNo,
                "topic" to it.topic,
                "postCount" to it.postCount,
                "regTime" to it.regDt.toEpochSecond()
            ) }

    @Transactional
    override fun add(cmd: NewTopicCommand, sessionItem: SessionItem): ResultWrapper<Long> {
        userService.validateCriticalSession(sessionItem)

        return cmd.ticker
            .takeIf { permission(it, sessionItem) }
            ?.let {
                val topic = boardTopicRepository.saveAndFlush(
                    BoardTopic.create(
                        ticker = cmd.ticker,
                        topic = cmd.topic,
                        an = sessionItem.an,
                    )
                )
                boardPostRepository.saveAndFlush(
                    BoardPost.createRootPost(
                        topicNo = topic.topicNo,
                        content = cmd.content,
                        an = sessionItem.an,
                    )
                )
                ResultWrapper.ok(topic.topicNo)
            }
            ?: ResultWrapper.fail("권한이 없습니다.", -1)
    }

    private fun permission(ticker: String, sessionItem: SessionItem): Boolean =
        boardTickerRepository.findByIdOrNull(ticker)?.run {
            writeTopicRoles.isEmpty() || sessionItem.roles.any { it in writeTopicRoles }
        } ?: false

    @Transactional
    override fun edit(cmd: EditTopicCommand, sessionItem: SessionItem): ResultWrapper<Unit> {
        cmd.validate()
        sessionItem.validateLogin()

        return boardTopicRepository
            .findByIdOrNull(cmd.topicNo)
            ?.takeIf { it.an == sessionItem.an }
            ?.let { node ->
                boardPostRepository
                    .findWithAccountByTopicNoAndRootIsTrue(cmd.topicNo)
                    ?.also { boardPostRepository.save(it.apply { edit(cmd.content) }) }
                    ?: ResultWrapper.fail("권한이 없거나 존재하지 않는 글입니다.", null)
                boardTopicRepository.save(node.apply { edit(cmd.topic) })
                ResultWrapper.ok()
            }
            ?: ResultWrapper.fail("권한이 없거나 존재하지 않는 글입니다.")
    }

    @Transactional
    override fun delete(cmd: DeleteTopicCommand, sessionItem: SessionItem): ResultWrapper<Unit> {
        cmd.validate()
        sessionItem.validateLogin()

        return boardTopicRepository
            .findByIdOrNull(cmd.topicNo)
            ?.takeIf { it.an == sessionItem.an || sessionItem.isAdmin }
            ?.let {
                if (it.an != sessionItem.an) {
                    activePanelRepository.save(
                        ActivePanel(
                            published = false,
                            code = "DEL",
                            an = sessionItem.an,
                            data1 = "[${sessionItem.name}]님이 글을 삭제했습니다.",
                            data2 = "작성자/회원번호: ${it.account?.name}/${it.an}",
                            data3 = "${it.topic}\n${boardPostRepository.findWithAccountByTopicNoAndRootIsTrue(it.topicNo)?.content}",
                        )
                    )
                }
                boardPostRepository.deleteAllByTopicNo(cmd.topicNo)
                boardTopicRepository.delete(it)
                ResultWrapper.ok()
            }
            ?: ResultWrapper.fail("권한이 없거나 존재하지 않는 글입니다.")
    }
}
