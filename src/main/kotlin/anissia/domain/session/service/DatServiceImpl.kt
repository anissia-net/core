package anissia.domain.session.service

import anissia.infrastructure.common.AsDat
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class DatServiceImpl(
    @Value($$"${dat.uri}") private val datUri: String,
    private val restTemplate: RestTemplate,
): DatService {
    override fun sync() {
        val certificates: String? = restTemplate.getForObject<String>("$datUri/certificates/signing")
        AsDat.imports(certificates)
    }
}
