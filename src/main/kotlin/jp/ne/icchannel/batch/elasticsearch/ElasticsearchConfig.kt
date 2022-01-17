package jp.ne.icchannel.batch.elasticsearch

import jp.ne.icchannel.batch.Constant.Companion.ELASTICSEARCH_HOST
import jp.ne.icchannel.batch.Constant.Companion.ELASTICSEARCH_PORT
import jp.ne.icchannel.batch.Constant.Companion.ELASTICSEARCH_SCHEME
import org.apache.http.HttpHost
import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient

class ElasticsearchConfig {

    fun client(): RestHighLevelClient {
        return getClient()
    }

    fun getClient(): RestHighLevelClient {
        return RestHighLevelClient(
            RestClient.builder(HttpHost(ELASTICSEARCH_HOST, ELASTICSEARCH_PORT, ELASTICSEARCH_SCHEME))
                .setHttpClientConfigCallback { httpAsyncClientBuilder: HttpAsyncClientBuilder -> httpAsyncClientBuilder }
                .setRequestConfigCallback { requestConfigBuilder: RequestConfig.Builder -> requestConfigBuilder }
        )
    }

    fun getRecreateClient(): RestHighLevelClient {
        return getClient()
    }
}