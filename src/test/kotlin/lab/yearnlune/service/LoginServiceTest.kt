package lab.yearnlune.service

import lab.yearnlune.SeleniumTestSupport
import lab.yearnlune.model.type.PageTypes
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.openqa.selenium.TimeoutException
import org.springframework.beans.factory.annotation.Autowired

internal class LoginServiceTest @Autowired constructor(
    private val loginService: LoginService
) : SeleniumTestSupport() {

    @AfterEach
    internal fun tearDown() {
        loginService.logout()
    }

    @Test
    internal fun login_invalidAccount_shouldBeThrowTimeoutException() {
        /* GIVEN */
        val id: String = ""
        val pw: String = ""

        /* THEN */
        assertThrows<TimeoutException> {
            /* WHEN */
            loginService.login(id, pw)
        }
    }

    @Test
    internal fun login_existsAccount_couldBeGoToMainPage() {
        /* WHEN */
        loginService.login()

        /* THEN */
        assertThat(webDriverHandler.getCurrentUrl(), `is`(PageTypes.MAIN.url))
    }

    @Test
    internal fun isLogin_alreadyLogin_shouldBeTrue() {
        /* WHEN */
        loginService.login()

        var isLogin = false;
        try {
            isLogin = loginService.isLogin()
        } catch (e: TimeoutException) {
        }

        assertThat(isLogin, `is`(true))
    }

    @Test
    internal fun isLogin_notLogin_shouldBeFalse() {
        /* GIVEN */
        // NOT LOGIN

        /* WHEN */
        val isLogin = loginService.isLogin();

        /* THEN */
        assertThat(isLogin, `is`(false))
    }

    @Test
    internal fun logout_alreadyLogin_shouldBeLogout() {
        /* GIVEN */
        loginService.login()

        /* WHEN */
        loginService.logout()

        /* THEN */
        assertThat(loginService.isLogin(), `is`(false))
    }

    @Test
    internal fun logout_notLogin_shouldBeLogout() {
        /* GIVEN */
        // NOT LOGIN

        /* WHEN */
        loginService.logout()

        /* THEN */
        assertThat(loginService.isLogin(), `is`(false))
    }
}