package anissia.infrastructure.common

import anissia.domain.session.model.SessionItem
import me.saro.dat.dat.DatCmsManager
import org.springframework.web.server.ServerWebExchange

class AsDat {
    companion object {
        private val SPLITOR = RecordSplitor("2", 3)
        private val MANAGER = DatCmsManager.builder().build()
        private val log = As.logger<AsDat>()

        fun toSession(exchange: ServerWebExchange): SessionItem {
            val ip = exchange.request.remoteAddress?.address?.hostAddress?:"0.0.0.0"
            val dat: String? = exchange.request.headers.getFirst("dat")
            if (!dat.isNullOrBlank()) {
                return MANAGER.parse(dat).map { payload ->
                    val split = SPLITOR.read(payload.plain)
                    if (split.isNotEmpty()) {
                        return@map SessionItem(
                            an = payload.secure.toLong(),
                            email = split[0],
                            name = split[1],
                            roles = split[2].takeIf { it.isNotBlank() }?.split(",") ?: listOf(),
                            ip = ip,
                        )
                    }
                    return@map null
                }.getOrElse { SessionItem.noLogin(ip) }
            }
            return SessionItem.noLogin(ip)
        }

        fun issue(sessionItem: SessionItem): String {
            val plain = SPLITOR.write(sessionItem.email, sessionItem.name, sessionItem.roles.joinToString(","))
            return MANAGER.issue(plain, sessionItem.an.toString()).getOrNull() ?: throw SecurityException("Failed to issue dat")
        }
    }
}
