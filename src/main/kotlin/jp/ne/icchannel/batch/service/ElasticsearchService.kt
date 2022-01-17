package jp.ne.icchannel.batch.service

import jp.ne.icchannel.batch.domain.Thread

class ElasticsearchService {

    fun bulkRegisterThread(threadMap: Map<String, List<Thread>>) {
        for (site in threadMap) {
            registerThread(site.key, site.value)
        }
    }

    private fun registerThread(siteName: String, threadList: List<Thread>) {
        for (thread in threadList) {
            if (isExistsThread(thread.url)) {
                continue
            }
            // TODO
        }
    }

    private fun isExistsThread(url: String): Boolean {
        // TODO
        return false
    }
}