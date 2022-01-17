package jp.ne.icchannel.batch.elasticsearch

import org.elasticsearch.action.DocWriteResponse
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.reindex.DeleteByQueryRequest
import java.lang.Exception

class ElasticsearchClientRepository(private var restHighLevelClient: RestHighLevelClient,
                                    private val elasticsearchClientConfig: ElasticsearchConfig) {


    fun setClient(restHighLevelClient: RestHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient
    }

    fun search(request: SearchRequest): SearchResponse? {
        return try {
            restHighLevelClient.search(request, RequestOptions.DEFAULT)
        } catch (e: Exception) {
            setClient(elasticsearchClientConfig.getRecreateClient())
            null
        }
    }

    fun createIndex(request: CreateIndexRequest) {
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT)
    }

    fun deleteAllDocument(request: DeleteByQueryRequest) {
        request.setQuery(QueryBuilders.matchAllQuery())
        restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT)
    }

    fun registerDocument(request: IndexRequest): Boolean {
        return try {
            val response = restHighLevelClient.index(request, RequestOptions.DEFAULT)
            response.result == DocWriteResponse.Result.CREATED;
        } catch (e: Exception) {
            e.printStackTrace()
            setClient(elasticsearchClientConfig.getRecreateClient())
            false
        }
    }

    fun bulkRegisterDocument(request: BulkRequest): Boolean {
        return try {
            val response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT)
            response.hasFailures()
        } catch (e: Exception) {
            e.printStackTrace()
            setClient(elasticsearchClientConfig.getRecreateClient())
            false
        }
    }
}