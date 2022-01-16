package jp.ne.icchannel.batch

import com.amazonaws.services.lambda.runtime.Context
import jp.ne.icchannel.batch.service.RssFetchService

class App {

    private val rssFetchService = RssFetchService()

    /**
     * lambdaのエンドポイント
     */
    fun handler(context: Context): String {
        val lambdaLogger = context.logger
        lambdaLogger.log("test log")

        execute()

        return "test"
    }

    fun execute() {
        print("test")
    }

}