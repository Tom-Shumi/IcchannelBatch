package jp.ne.icchannel.batch

class Constant {
    companion object {
        val FETCH_URL_MAP = mapOf(
            "2ちゃんねるまとめのまとめ" to "http://calcal.net/2ch/index.rss",
            "まめ速" to "http://mamesoku.com/index.rdf",
            "NEWまとめサイト速報" to "https://newmatosoku.com/feed/main/rss2.xml",
            "がーるずレポート" to "http://girlsreport.net/index.rdf"
        )
        val FILTER_KEYWORD_MAP = mapOf(
            "犬" to "dog",
            "猫" to "cat",
            "動物" to "animal",
            "癒" to "relax",
            "笑" to "laugh",
            "可愛" to "cute",
            "イッヌ" to "dog",
            "ネッコ" to "cat",
            "ゾウ" to "animal"
        )

        const val ELASTICSEARCH_HOST = "localhost"
        const val ELASTICSEARCH_PORT = 9200
        const val ELASTICSEARCH_SCHEME = "http"
        const val ELASTICSEARCH_INDEX_THREAD = "thread"
    }
}