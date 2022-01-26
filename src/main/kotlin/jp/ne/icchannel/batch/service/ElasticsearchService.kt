package jp.ne.icchannel.batch.service

import jp.ne.icchannel.batch.Constant.Companion.ELASTICSEARCH_INDEX_THREAD
import jp.ne.icchannel.batch.Constant.Companion.EXPIRED_PERIOD
import jp.ne.icchannel.batch.domain.Thread
import jp.ne.icchannel.batch.elasticsearch.ElasticsearchClientRepository
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.index.query.*
import org.elasticsearch.index.reindex.DeleteByQueryRequest
import org.elasticsearch.search.SearchHit
import org.elasticsearch.search.builder.SearchSourceBuilder

class ElasticsearchService(private val elasticsearchClientRepository: ElasticsearchClientRepository) {

    fun bulkRegisterThread(threadMap: Map<String, List<Thread>>) {
        for (site in threadMap) {
            registerThread(site.value)
        }
    }

    fun deleteExpiredThread() {
        val request = DeleteByQueryRequest(ELASTICSEARCH_INDEX_THREAD)
        request.setQuery(QueryBuilders.rangeQuery("publishedDate").lt(EXPIRED_PERIOD))
        elasticsearchClientRepository.deleteDocument(request)
    }

    private fun registerThread(threadList: List<Thread>) {
        for (thread in threadList) {
            if (isExistsThreadByUrl(thread.title)) {
                continue
            }
            registerDocument(thread)
        }
    }

    private fun isExistsThreadByUrl(title: String): Boolean {
        return searchByUrl(title).isNotEmpty()
    }

    private fun searchByUrl(title: String): Array<out SearchHit> {
        val searchSourceBuilder = SearchSourceBuilder()
        val boolQueryBuilder = BoolQueryBuilder()
        boolQueryBuilder.filter(MatchQueryBuilder("title", title).operator(Operator.AND))
        searchSourceBuilder.query(boolQueryBuilder)

        val request = SearchRequest(ELASTICSEARCH_INDEX_THREAD).source(searchSourceBuilder)
        val response = elasticsearchClientRepository.search(request)
        return response.hits.hits
    }

    private fun registerDocument(thread: Thread): Boolean {
        val request = IndexRequest(ELASTICSEARCH_INDEX_THREAD).source(thread.toMap())

        return elasticsearchClientRepository.registerDocument(request)
    }
}