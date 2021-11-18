package lab.yearnlune.model.type

enum class PageTypes(private val url: String) {

//    LOGIN_PAGE("https://member.onstove.com/auth/login?redirect_url=https%3A%2F%2Flostark.game.onstove.com%2FMarket"),
    LOGIN_PAGE("https://member.onstove.com/auth/login"),
    MARKET_PAGE("https://lostark.game.onstove.com/Market"),
    AUCTION_PAGE("https://lostark.game.onstove.com/Auction");

    fun getUrl(): String {
        return this.url
    }
}