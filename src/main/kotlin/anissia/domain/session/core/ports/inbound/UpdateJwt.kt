package anissia.domain.session.core.ports.inbound

import anissia.domain.session.core.model.LoginInfoItem
import anissia.domain.session.core.model.Session
import anissia.shared.ResultWrapper

interface UpdateJwt {
    fun handle(session: Session): ResultWrapper<LoginInfoItem>
}
