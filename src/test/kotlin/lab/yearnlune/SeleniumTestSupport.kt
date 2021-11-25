package lab.yearnlune

import SpringTestSupport
import lab.yearnlune.crawling.WebDriverHandler
import org.springframework.beans.factory.annotation.Autowired

internal class SeleniumTestSupport : SpringTestSupport() {

    @field:Autowired
    protected lateinit var webDriverHandler: WebDriverHandler
}