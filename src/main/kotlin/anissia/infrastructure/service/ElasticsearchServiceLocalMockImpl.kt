package anissia.infrastructure.service

import anissia.infrastructure.common.As
import org.apache.http.HttpHost
import org.elasticsearch.client.Request
import org.elasticsearch.client.Response
import org.elasticsearch.client.RestClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
@RestController
@Profile("local")
class ElasticsearchServiceLocalMockImpl(
    @Value("\${server.port}") private val port: String,
): ElasticsearchService {
    private val log = As.logger<ElasticsearchServiceLocalMockImpl>()

    override fun request(method: String, endpoint: String, body: String?): Response {
        log.info("Local Mock Elasticsearch: $method ${endpoint}${body?.let { "\n${body}" } ?: ""}")
        return RestClient.builder(HttpHost.create("http://localhost:$port")).build().performRequest(Request("GET", FAKE_PATH))
    }

    @RequestMapping(FAKE_PATH, produces = ["application/json"])
    fun search() = """{"hits":{"total":{"value":0},"hits":[]}}"""

    companion object {
        private const val FAKE_PATH = "/local-mock/elasticsearch"
    }
}
