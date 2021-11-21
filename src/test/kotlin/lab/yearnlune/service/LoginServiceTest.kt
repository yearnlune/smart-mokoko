package lab.yearnlune.service

import SpringTestSupport
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class LoginServiceTest @Autowired constructor(private val loginService: LoginService) : SpringTestSupport() {

    @Test
    fun login() {
        loginService.login()
    }
}