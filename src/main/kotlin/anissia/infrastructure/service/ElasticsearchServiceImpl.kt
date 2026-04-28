package anissia.infrastructure.service

import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.elasticsearch.client.Request
import org.elasticsearch.client.Response
import org.elasticsearch.client.RestClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("!local")
class ElasticsearchServiceImpl(
    @Value("\${elasticsearch.url}")
    private val url: String,
    @Value("\${elasticsearch.username}")
    private val username: String,
    @Value("\${elasticsearch.password}")
    private val password: String,
): ElasticsearchService {
    private val credentialsProvider = BasicCredentialsProvider()
        .apply {
            if (username.isNotEmpty() && password.isNotEmpty()) {
                setCredentials(AuthScope.ANY, UsernamePasswordCredentials(username, password))
            }
        }

    private fun open(): RestClient = RestClient
        .builder(HttpHost.create(url))
        .setHttpClientConfigCallback { it.setDefaultCredentialsProvider(credentialsProvider) }
        .build()

    override fun request(method: String, endpoint: String, body: String?): Response = open().use {
        val req = Request(method, endpoint)
        if (body != null) {
            req.setJsonEntity(body)
        }
        it.performRequest(req)
    }
}
