package lab.yearnlune.crawling

import lab.yearnlune.model.type.ElementTypes
import lab.yearnlune.model.type.PageTypes
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Component

@Component
class WebDriverHandler() {

    private val browser: WebDriver by lazy {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe")
        ChromeDriver()
    };

    fun movePage(pageTypes: PageTypes, timeout: Long = 10000L, sleep: Long = 3000L): WebDriverWait {
        this.browser.get(pageTypes.getUrl())
        return WebDriverWait(this.browser, timeout, sleep)
    }

    fun click(elementTypes: ElementTypes, timeout: Long = 10000L, sleep: Long = 3000L): WebDriverWait {
        findElement(elementTypes).click()
        return WebDriverWait(this.browser, timeout, sleep)
    }

    fun click(xPath: String, timeout: Long = 10000L, sleep: Long = 3000L): WebDriverWait {
        findElementXpath(xPath)
            .click()
        return WebDriverWait(this.browser, timeout, sleep)
    }


    fun input(elementTypes: ElementTypes, value: String) = findElement(elementTypes).sendKeys(value)

    fun findElement(elementTypes: ElementTypes): WebElement = findElementXpath(elementTypes.getXpath())

    fun hasElement(xPath: String): Boolean = findElementXpath(xPath).isEnabled

    private fun findElementXpath(xPath: String): WebElement = this.browser.findElement(By.xpath(xPath))
}
