package lab.yearnlune.service

import lab.yearnlune.SeleniumTestSupport
import lab.yearnlune.model.type.MarketCategoryTypes
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class MarketServiceTest @Autowired constructor(
    private val loginService: LoginService,
    private val marketService: MarketService
) : SeleniumTestSupport() {

    @BeforeEach
    internal fun login() {
        loginService.login()
    }

    @Test
    internal fun crawling() {
        /* GIVEN */
        val category = MarketCategoryTypes.LIFE
        val itemName = "들꽃"

        /* WHEN */
        marketService.crawling(category)

        /* THEN */
        MatcherAssert.assertThat(marketService.getPrice(itemName), CoreMatchers.notNullValue())
    }
}