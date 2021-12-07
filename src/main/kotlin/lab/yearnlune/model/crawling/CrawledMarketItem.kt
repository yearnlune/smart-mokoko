package lab.yearnlune.model.crawling

import lab.yearnlune.model.type.GradeTypes

data class CrawledMarketItem(

    val name: String,

    val grade: GradeTypes,

    val bundle: Long,

    val trade: Long,

    val average: Double,

    val recent: Long,

    val current: Long
) {
}