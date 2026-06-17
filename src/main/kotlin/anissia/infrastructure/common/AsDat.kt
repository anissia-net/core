package anissia.infrastructure.common

import anissia.domain.account.Account
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
            val dat = exchange.request.headers.getFirst("dat")
            if (!dat.isNullOrBlank()) {
                try {
                    val payload = MANAGER.parse(dat)
                    val split = SPLITOR.read(payload.plain)
                    if (split.isNotEmpty()) {
                        return SessionItem(
                            an = payload.secure.toLong(),
                            email = split[0],
                            name = split[1],
                            roles = split[2].takeIf { it.isNotBlank() }?.split(",") ?: listOf(),
                            ip = ip,
                        )
                    }
                } catch (e: Exception) {
                    log.info("Dat Error: $dat ${e.message}")
                    exchange.response.headers.set("Dat-Error", "INVALID")
                }

            }
            return SessionItem.cast(Account(), ip)
        }

        fun issue(sessionItem: SessionItem): String = try {
            val plain = SPLITOR.write(sessionItem.email, sessionItem.name, sessionItem.roles.joinToString(","))
            MANAGER.issue(plain, sessionItem.an.toString())
        } catch (e: Exception) {
            throw SecurityException(e.message)
        }
    }
}
