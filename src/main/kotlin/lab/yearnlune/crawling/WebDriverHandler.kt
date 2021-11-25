package lab.yearnlune.crawling

import lab.yearnlune.model.type.ElementTypes
import lab.yearnlune.model.type.PageTypes
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class WebDriverHandler() {

    private val options: ChromeOptions

    private val browser: WebDriver

    init {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe")
        this.options = ChromeOptions()
        this.options.setHeadless(true)
        this.browser = ChromeDriver(options)
        this.browser.manage().timeouts().pageLoadTimeout(10L, TimeUnit.SECONDS)
        this.browser.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS)
    }

    fun movePage(pageTypes: PageTypes, timeout: Long = 10L, sleep: Long = 3000L): WebDriverWait {
        this.browser.get(pageTypes.url)
        return WebDriverWait(this.browser, timeout, sleep)
    }

    fun click(elementTypes: ElementTypes, timeout: Long = 10L, sleep: Long = 3000L): WebDriverWait {
        return click(elementTypes.xpath, timeout, sleep)
    }

    fun click(xPath: String, timeout: Long = 10L, sleep: Long = 3000L): WebDriverWait {
        findElementXpath(xPath)
            .until(ExpectedConditions.elementToBeClickable(findElementWithXpath(xPath)))
            .click()

        return WebDriverWait(this.browser, timeout, sleep)
    }

    fun input(elementTypes: ElementTypes, value: String) = findElement(elementTypes).sendKeys(value)

    fun findElement(elementTypes: ElementTypes): WebElement = findElementWithXpath(elementTypes.xpath)

    fun hasElement(xPath: String): Boolean = findElementWithXpath(xPath).isEnabled

    fun findElementXpath(xPath: String): WebDriverWait {
        findElementWithXpath(xPath)
        return WebDriverWait(this.browser, 3L)
    }

    fun findElementWithXpath(xPath: String): WebElement = this.browser.findElement(By.xpath(xPath))

    fun getCurrentUrl(): String = this.browser.currentUrl;

    fun close() = this.browser.close()
}
