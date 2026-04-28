package anissia.infrastructure.service

import anissia.infrastructure.common.As
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ServerWebExchange

@Profile("local")
@Controller
@RequestMapping("/local/fake/elasticsearch")
class ElasticsearchLocalFakeController {
    private val log = As.logger<ElasticsearchLocalFakeController>()

    @RequestMapping
    fun fake(exchange: ServerWebExchange): String {
        val req = exchange.request
        val body = req.body.toString()
        log.info("Local Fake Elasticsearch: ${req.method} ${req.uri}${if (body.isNotBlank()) "\n${req.body}" else ""}")
        return """{"hits":{"total":{"value":0},"hits":[]}}"""
    }
}
