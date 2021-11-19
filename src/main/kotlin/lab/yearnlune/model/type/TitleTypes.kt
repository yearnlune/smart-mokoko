package lab.yearnlune.model.type

enum class TitleTypes(private val title: String) {

    LOGIN("STOVE"),
    MAIN("STOVE - 스토브, 식지 않는 재미"),
    MARKET("로스트아크 - 거래소");

    fun getTitle(): String {
        return this.title;
    }
}