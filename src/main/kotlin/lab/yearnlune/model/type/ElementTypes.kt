package lab.yearnlune.model.type

enum class ElementTypes(val xpath: String) {

    INPUT_LOGIN_ID("//*[@id=\"user_id\"]"),
    INPUT_LOGIN_PW("//*[@id=\"user_pwd\"]"),
    BUTTON_LOGIN("//*[@id=\"idLogin\"]/div[3]/button"),
    BUTTON_LOGOUT("//*[@id=\"logout-btn\"]/span[2]"),
    BUTTON_USER_INFO("//*[@id=\"user-wrapper\"]/div[1]/button/span/img"),
    BUTTON_MARKET_CATEGORY("//*[@id=\"lostark-wrapper\"]/div/main/div/div[3]/div[1]/ul"),
    ELEMENT_MARKET_ITEM("//*[@id=\"tbodyItemList\"]"),
    ELEMENT_MARKET_FIRST_ITEM("${ELEMENT_MARKET_ITEM.xpath}/tr[1]"),
    BUTTON_MARKET_PAGINATION_START("//*[@id=\"marketList\"]/div[2]");
}