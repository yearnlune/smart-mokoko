package lab.yearnlune.model.type

enum class TitleTypes(private val title: String) {
    LOGIN("STOVE"),
    MARKET("로스트아크 - 거래소");

    fun getTitle(): String {
        return this.title;
    }
}