package jp.ne.icchannel.batch.domain

import java.time.LocalDateTime

data class Thread(
    val title: String,
    val url: String,
    val publishedDate: LocalDateTime,
    val siteName: String
) {
    fun toMap(): Map<String, Any> {
        return mapOf("title" to title,
            "url" to url,
            "publishedDate" to publishedDate,
            "siteName" to siteName)
    }
}