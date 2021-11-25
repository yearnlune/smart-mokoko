package lab.yearnlune.service

import lab.yearnlune.crawling.WebDriverHandler
import lab.yearnlune.model.type.ElementTypes
import lab.yearnlune.model.type.PageTypes
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class LoginService(
    private val webDriverHandler: WebDriverHandler,
    @Value("\${mokoko.id}") private val id: String,
    @Value("\${mokoko.pw}") private val pw: String
) {

    fun login() {
        login(this.id, this.pw)
    }

    fun login(id: String, pw: String) {
        moveLoginPage()
        inputLoginId(id)
        inputLoginPw(pw)
        clickLoginButton()
    }

    fun logout() {
        if (isLogin()) {
            webDriverHandler.click(ElementTypes.BUTTON_USER_INFO.xpath)
                .until(ExpectedConditions.elementToBeClickable(webDriverHandler.findElement(ElementTypes.BUTTON_LOGOUT)))
                .click()
        }
    }

    fun isLogin(): Boolean {
        var isLogin = false;
        try {
            isLogin = webDriverHandler.movePage(PageTypes.MEMBER)
                .withTimeout(Duration.ofSeconds(3))
                .until(
                    ExpectedConditions.urlToBe(
                        PageTypes.MEMBER.url
                    )
                )
        } catch (e: TimeoutException) {
        }

        return isLogin
    }

    private fun moveLoginPage() = webDriverHandler.movePage(PageTypes.LOGIN)
        .withTimeout(Duration.ofSeconds(3))
        .until(
            ExpectedConditions.urlToBe(
                PageTypes.LOGIN.url
            )
        )

    private fun inputLoginId(id: String) = webDriverHandler.input(ElementTypes.INPUT_LOGIN_ID, id)

    private fun inputLoginPw(pw: String) = webDriverHandler.input(ElementTypes.INPUT_LOGIN_PW, pw)

    private fun clickLoginButton() = webDriverHandler.click(ElementTypes.BUTTON_LOGIN)
        .until(
            ExpectedConditions.urlToBe(
                PageTypes.MAIN.url
            )
        )
}