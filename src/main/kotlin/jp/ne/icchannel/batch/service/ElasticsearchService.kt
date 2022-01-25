package jp.ne.icchannel.batch.service

import jp.ne.icchannel.batch.Constant.Companion.ELASTICSEARCH_INDEX_THREAD
import jp.ne.icchannel.batch.domain.Thread
import jp.ne.icchannel.batch.elasticsearch.ElasticsearchClientRepository
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.index.query.BoolQueryBuilder
import org.elasticsearch.index.query.TermQueryBuilder
import org.elasticsearch.search.SearchHit
import org.elasticsearch.search.builder.SearchSourceBuilder

class ElasticsearchService(private val elasticsearchClientRepository: ElasticsearchClientRepository) {

    fun bulkRegisterThread(threadMap: Map<String, List<Thread>>) {
        for (site in threadMap) {
            registerThread(site.value)
        }
    }

    fun deleteExpiredThread() {
        // TODO
    }

    private fun registerThread(threadList: List<Thread>) {
        for (thread in threadList) {
            if (isExistsThreadByUrl(thread.url)) {
                continue
            }
            registerDocument(thread)
        }
    }

    private fun isExistsThreadByUrl(url: String): Boolean {
        return searchByUrl(url).isNotEmpty()
    }

    private fun searchByUrl(url: String): Array<out SearchHit> {
        val searchSourceBuilder = SearchSourceBuilder()
        val boolQueryBuilder = BoolQueryBuilder()
        boolQueryBuilder.filter(TermQueryBuilder("url", url))
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