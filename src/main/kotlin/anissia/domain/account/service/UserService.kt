package anissia.domain.account.service

import anissia.domain.account.command.EditUserNameCommand
import anissia.domain.account.command.EditUserPasswordCommand
import anissia.domain.account.model.AccountUserItem
import anissia.domain.session.model.SessionItem
import anissia.shared.ResultWrapper

interface UserService {
    suspend fun get(sessionItem: SessionItem): AccountUserItem
    suspend fun editName(cmd: EditUserNameCommand, sessionItem: SessionItem): ResultWrapper<Unit>
    suspend fun editPassword(cmd: EditUserPasswordCommand, sessionItem: SessionItem): ResultWrapper<Unit>
    suspend fun validateCriticalSession(sessionItem: SessionItem)
}
