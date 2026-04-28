package anissia.infrastructure.service

import org.elasticsearch.client.Response

interface ElasticsearchService {
    fun request(method: String, endpoint: String, body: String? = null): Response

    fun existsIndex(index: String): Boolean =
        request("HEAD", "/$index").statusLine.statusCode == 200

    fun deleteIndex(index: String): Boolean =
        request("DELETE", "/$index").statusLine.statusCode == 200

    fun deleteIndexIfExists(index: String): Boolean =
        if (existsIndex(index)) deleteIndex(index) else false

    fun createIndex(index: String, body: String): Boolean =
        request("PUT", "/$index", body).statusLine.statusCode == 200

    fun updateIndex(forceCreate: Boolean, index: String, body: String): Boolean =
        request("PUT", "/$index/_mapping", body).statusLine.statusCode == 200
}
