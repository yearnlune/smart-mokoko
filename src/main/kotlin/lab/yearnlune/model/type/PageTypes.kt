package lab.yearnlune.model.type

enum class PageTypes(private val url: String) {

    LOGIN("https://member.onstove.com/auth/login"),
    MAIN("https://www.onstove.com/"),
    MARKET("https://lostark.game.onstove.com/Market"),
    AUCTION("https://lostark.game.onstove.com/Auction");

    fun getUrl(): String {
        return this.url
    }
}