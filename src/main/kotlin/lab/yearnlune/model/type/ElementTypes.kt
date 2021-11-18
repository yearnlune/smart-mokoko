package lab.yearnlune.model.type

enum class ElementTypes(private val xpath: String) {

    INPUT_LOGIN_ID("//*[@id=\"user_id\"]"),
    INPUT_LOGIN_PW("//*[@id=\"user_pwd\"]"),
    BUTTON_LOGIN("//*[@id=\"idLogin\"]/div[3]/button"),
    BUTTON_ABC("//*[@id=\"lostark-wrapper\"]/div/main/div/div[3]/div[1]/ul/li[8]/ul/li[1]/a");

    fun getXpath(): String {
        return this.xpath
    }
}