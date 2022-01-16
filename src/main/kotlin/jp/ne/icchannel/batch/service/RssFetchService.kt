package jp.ne.icchannel.batch.service

import jp.ne.icchannel.batch.Constant
import jp.ne.icchannel.batch.domain.Thread

class RssFetchService {

    fun fetchRssFeed() {
        val rssMap = mutableMapOf<String, List<Thread>>()
        for (site in Constant.FETCH_URL_MAP) {
            // TODO
        }
    }
}