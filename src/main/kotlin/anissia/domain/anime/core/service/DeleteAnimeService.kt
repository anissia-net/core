package anissia.domain.anime.core.service

import anissia.domain.activePanel.core.model.NewActivePanelTextCommand
import anissia.domain.activePanel.core.ports.inbound.NewActivePanelText
import anissia.domain.agenda.core.Agenda
import anissia.domain.agenda.core.ports.outbound.AgendaRepository
import anissia.domain.anime.core.model.AnimeItem
import anissia.domain.anime.core.model.DeleteAnimeCommand
import anissia.domain.anime.core.ports.inbound.DeleteAnime
import anissia.domain.anime.core.ports.inbound.UpdateAnimeDocument
import anissia.domain.anime.core.ports.outbound.AnimeCaptionRepository
import anissia.domain.anime.core.ports.outbound.AnimeRepository
import anissia.domain.session.core.model.Session
import anissia.infrastructure.common.As
import anissia.shared.ResultWrapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteAnimeService(
    private val animeRepository: AnimeRepository,
    private val animeCaptionRepository: AnimeCaptionRepository,
    private val updateAnimeDocument: UpdateAnimeDocument,
    private val newActivePanelText: NewActivePanelText,
    private val agendaRepository: AgendaRepository,
): DeleteAnime {

    @Transactional
    override fun handle(cmd: DeleteAnimeCommand, session: Session): ResultWrapper<Unit> {
        cmd.validate()
        session.validateAdmin()

        val animeNo = cmd.animeNo
        val agenda = Agenda(code = "ANIME-DEL", status = "wait", an = session.an)

        val anime = animeRepository.findWithCaptionsByAnimeNo(animeNo)
            ?.also { agenda.data1 = As.toJsonString(AnimeItem(it, true)) }
            ?: return ResultWrapper.fail("존재하지 않는 애니메이션입니다.")

        newActivePanelText.handle(NewActivePanelTextCommand("[${session.name}]님이 애니메이션 [${anime.subject}]을(를) 삭제하였습니다."), null)

        animeCaptionRepository.deleteByAnimeNo(animeNo)
        animeRepository.delete(anime)
        updateAnimeDocument.handle(anime)
        agendaRepository.save(agenda)

        return ResultWrapper.ok()
    }
}
