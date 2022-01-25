package jp.ne.icchannel.batch

import com.amazonaws.services.lambda.runtime.Context
import jp.ne.icchannel.batch.elasticsearch.ElasticsearchClientRepository
import jp.ne.icchannel.batch.elasticsearch.ElasticsearchConfig
import jp.ne.icchannel.batch.service.ElasticsearchService
import jp.ne.icchannel.batch.service.FeedFetchService

class App {

    private val feedFetchService = FeedFetchService()
    private val elasticsearchConfig = ElasticsearchConfig()
    private val restHighLevelClient = elasticsearchConfig.getClient()
    private val elasticsearchClientRepository = ElasticsearchClientRepository(restHighLevelClient, elasticsearchConfig)
    private val elasticsearchService = ElasticsearchService(elasticsearchClientRepository)

    /**
     * lambdaのエンドポイント
     */
    fun handler(context: Context): String {
        val lambdaLogger = context.logger
        lambdaLogger.log("start")

        execute()

        lambdaLogger.log("end")
        return "ok"
    }

    fun execute() {
        val feed = feedFetchService.fetchFeed()
        elasticsearchService.bulkRegisterThread(feed)
        elasticsearchService.deleteExpiredThread()
    }

}