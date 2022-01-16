package jp.ne.icchannel.batch.domain

import java.time.LocalDateTime

data class Thread(
    val title: String,
    val url: String,
    val publicDateTime: LocalDateTime
) {
}