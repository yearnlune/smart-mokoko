package lab.yearnlune.service

import lab.yearnlune.crawling.WebDriverHandler
import lab.yearnlune.model.type.ElementTypes
import lab.yearnlune.model.type.PageTypes
import lab.yearnlune.model.type.TitleTypes
import org.openqa.selenium.support.ui.ExpectedConditions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val webDriverHandler: WebDriverHandler,
    @Value("\${mokoko.id}") private val id: String,
    @Value("\${mokoko.pw}") private val pw: String
) {

    fun login() {
        moveLoginPage()
        inputLoginId(id)
        inputLoginPw(pw)
        clickLoginButton()
    }

    private fun moveLoginPage() = webDriverHandler.movePage(PageTypes.LOGIN)
        .until(
            ExpectedConditions.titleIs(
                TitleTypes.LOGIN.getTitle()
            )
        )

    private fun inputLoginId(id: String) = webDriverHandler.input(ElementTypes.INPUT_LOGIN_ID, id)

    private fun inputLoginPw(pw: String) = webDriverHandler.input(ElementTypes.INPUT_LOGIN_PW, pw)

    private fun clickLoginButton() = webDriverHandler.click(ElementTypes.BUTTON_LOGIN)
        .until(
            ExpectedConditions.urlMatches(
                PageTypes.MAIN.getUrl()
            )
        )
}