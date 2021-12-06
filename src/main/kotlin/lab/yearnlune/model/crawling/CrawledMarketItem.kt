package lab.yearnlune.model.crawling

data class CrawledMarketItem(

    val name: String,

    val bundle: Long,

    val trade: Long,

    val average: Double,

    val recent: Long,

    val current: Long
) {
}