package lab.yearnlune.model.type

enum class PageTypes(val url: String) {

    LOGIN("https://member.onstove.com/auth/login"),
    MAIN("https://www.onstove.com/"),
    MEMBER("https://member.onstove.com/member/info"),
    MARKET("https://lostark.game.onstove.com/Market"),
    AUCTION("https://lostark.game.onstove.com/Auction");
}