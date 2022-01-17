package jp.ne.icchannel.batch

class Constant {
    companion object {
        val FETCH_URL_MAP = mapOf(
            "2ちゃんねるまとめのまとめ" to "http://calcal.net/2ch/index.rss",
            "まめ速" to "http://mamesoku.com/index.rdf",
            "NEWまとめサイト速報" to "https://newmatosoku.com/feed/main/rss2.xml",
            "がーるずレポート" to "http://girlsreport.net/index.rdf"
        )
        val FILTER_KEYWORD = listOf(
            "犬",
            "猫",
            "動物",
            "癒",
            "笑",
            "可愛"
        )

        val ELASTICSEARCH_HOST = "localhost"
        val ELASTICSEARCH_PORT = 9200
        val ELASTICSEARCH_SCHEME = "http"
    }
}