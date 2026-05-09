package anissia.domain.session.service

import anissia.infrastructure.common.As
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class DatServiceImpl(
    @Value($$"${dat.uri}") private val datUri: String,
    private val restTemplate: RestTemplate,
): DatService {
    private val log = As.logger<DatServiceImpl>()

    override fun sync() {
        val keys: String? = restTemplate.getForObject("$datUri/certificates/signing", String::class.java)
        if (keys != null) {
            As.DAT_MANAGER.imports(keys, false)
            log.info("Sync DAT Key List: " + As.DAT_MANAGER.exportsCertificates().size)
        }
    }
}
