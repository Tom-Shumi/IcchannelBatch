package jp.ne.icchannel.batch.service

import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import jp.ne.icchannel.batch.Constant
import jp.ne.icchannel.batch.domain.Thread
import jp.ne.icchannel.batch.util.DateTimeUtil
import java.lang.Exception
import java.net.URL
import java.util.*

class FeedFetchService {

    fun fetchFeed(): Map<String, List<Thread>> {
        val rssMap = mutableMapOf<String, List<Thread>>()
        for (site in Constant.FETCH_URL_MAP) {
            rssMap[site.key] = createThreadList(site.key, site.value)
        }
        return rssMap
    }

    private fun createThreadList(siteName: String, url: String): List<Thread> {
        try {
            XmlReader(URL(url)).use { reader ->
            return fetchAndCreateList(siteName, reader)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return listOf()
        }
    }

    private fun fetchAndCreateList(siteName: String, reader: XmlReader): List<Thread> {
        val entryList = SyndFeedInput().build(reader).entries
        val filteredEntryList =  entryList.filter { filterEntry(it) }
        return filteredEntryList.map { toThread(it, siteName) }
    }

    private fun filterEntry(e: SyndEntry): Boolean {
        for (keyword in Constant.FILTER_KEYWORD) {
            if (e.title.contains(keyword) ||
                (Objects.nonNull(e.description) && Objects.nonNull(e.description.value) && e.description.value.contains(keyword))) {
                return true
            }
        }
        return false
    }

    private fun toThread(e: SyndEntry, siteName: String): Thread {
        return Thread(e.title, e.uri, DateTimeUtil.toDateTimeUtil(e.publishedDate), siteName)
    }
}