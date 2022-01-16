package jp.ne.icchannel.batch

import com.amazonaws.services.lambda.runtime.Context

class App {

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