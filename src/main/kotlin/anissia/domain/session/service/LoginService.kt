package anissia.domain.session.service

import anissia.domain.session.command.DoTokenLoginCommand
import anissia.domain.session.command.DoUserLoginCommand
import anissia.domain.session.model.DatAuthInfoItem
import anissia.domain.session.model.SessionItem
import anissia.shared.ResultWrapper

interface LoginService {
    fun doUserLogin(cmd: DoUserLoginCommand, sessionItem: SessionItem): ResultWrapper<DatAuthInfoItem>
    fun doTokenLogin(cmd: DoTokenLoginCommand, sessionItem: SessionItem): ResultWrapper<DatAuthInfoItem>
    fun updateAuthInfo(sessionItem: SessionItem): ResultWrapper<DatAuthInfoItem>
}
