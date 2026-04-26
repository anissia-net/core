package anissia.domain.session.command

import anissia.domain.session.model.SessionItem

class GetDatAuthInfoCommand(
    val sessionItem: SessionItem,
    val makeLoginToken: Boolean
)
