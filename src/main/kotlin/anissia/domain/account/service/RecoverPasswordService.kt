package anissia.domain.account.service

import anissia.domain.account.command.CompleteRecoverPasswordCommand
import anissia.domain.account.command.RequestRecoverPasswordCommand
import anissia.domain.account.command.ValidateRecoverPasswordCommand
import anissia.domain.session.model.SessionItem
import anissia.shared.ResultWrapper

interface RecoverPasswordService {
    suspend fun request(cmd: RequestRecoverPasswordCommand, sessionItem: SessionItem): ResultWrapper<Unit>
    suspend fun validate(cmd: ValidateRecoverPasswordCommand): ResultWrapper<Unit>
    suspend fun complete(cmd: CompleteRecoverPasswordCommand): ResultWrapper<Unit>
}
