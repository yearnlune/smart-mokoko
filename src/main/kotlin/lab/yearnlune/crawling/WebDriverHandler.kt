package lab.yearnlune.crawling

import lab.yearnlune.model.type.ElementTypes
import lab.yearnlune.model.type.PageTypes
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
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

    fun movePageSafely(pageTypes: PageTypes, timeout: Long = 10L): Boolean? {
        return movePage(pageTypes, timeout)
            .until(ExpectedConditions.urlToBe(pageTypes.url))
    }

    fun movePage(pageTypes: PageTypes, timeout: Long = 10L): WebDriverWait {
        this.browser.get(pageTypes.url)
        return WebDriverWait(this.browser, timeout)
    }

    fun findElementAndWait(xPath: String): WebDriverWait {
        this.browser.findElement(By.xpath(xPath))
        return WebDriverWait(this.browser, 3L)
    }

    fun findElementSafely(elementTypes: ElementTypes): WebElement = findElementSafely(elementTypes.xpath)

    fun findElementSafely(xPath: String): WebElement =
        findElementAndWait(xPath).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)))

    fun hasElement(xPath: String): Boolean {
        var hasElement = false;
        try {
            hasElement = this.browser.findElement(By.xpath(xPath)).isEnabled
        } catch (e: NoSuchElementException) {
        }
        return hasElement
    }

    fun clickAndWait(elementTypes: ElementTypes, timeout: Long = 10L): WebDriverWait =
        clickAndWait(elementTypes.xpath, timeout)

    fun clickAndWait(xPath: String, timeout: Long = 10L): WebDriverWait {
        findElementAndWait(xPath)
            .until(ExpectedConditions.elementToBeClickable(this.browser.findElement(By.xpath(xPath))))
            .click()

        return WebDriverWait(this.browser, timeout)
    }

    fun input(elementTypes: ElementTypes, value: String) = findElementSafely(elementTypes).sendKeys(value)

    fun getCurrentUrl(): String = this.browser.currentUrl;

    fun close() = this.browser.close()
}
