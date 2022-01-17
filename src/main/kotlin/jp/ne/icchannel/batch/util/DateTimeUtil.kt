package jp.ne.icchannel.batch.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class DateTimeUtil {
    companion object {
        fun toDateTimeUtil(date: Date): LocalDateTime {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        }
    }
}