package anissia.domain.activePanel.service


import anissia.domain.activePanel.command.DoCommandActivePanelCommand
import anissia.domain.session.model.SessionItem
import anissia.shared.ResultWrapper

interface ActivePanelCommandService {
    suspend fun doCommand(cmd: DoCommandActivePanelCommand, sessionItem: SessionItem): ResultWrapper<Unit>
}
