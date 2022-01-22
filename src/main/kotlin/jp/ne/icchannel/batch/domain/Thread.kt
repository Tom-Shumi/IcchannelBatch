package jp.ne.icchannel.batch.domain

import java.time.LocalDateTime

data class Thread(
    val title: String,
    val url: String,
    val publishedDate: LocalDateTime,
    val category: String,
    val siteName: String
) {
    fun toMap(): Map<String, Any> {
        return mapOf("title" to title,
            "url" to url,
            "publishedDate" to publishedDate,
            "category" to category,
            "siteName" to siteName)
    }
}