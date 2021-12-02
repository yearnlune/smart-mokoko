package lab.yearnlune.service

import lab.yearnlune.crawling.WebDriverHandler
import lab.yearnlune.logger
import lab.yearnlune.model.type.ElementTypes
import lab.yearnlune.model.type.MarketCategoryTypes
import lab.yearnlune.model.type.PageTypes
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class MarketService(private val webDriverHandler: WebDriverHandler) {

    private val itemMap: MutableMap<String, Double> = HashMap()

    fun crawling(marketCategoryTypes: MarketCategoryTypes = MarketCategoryTypes.LIFE) {
        moveMarketPage()
        getMarketItemList(marketCategoryTypes)
    }

    fun getPrice(itemName: String): Double? = this.itemMap[itemName]

    private fun moveMarketPage(): Boolean? = webDriverHandler.movePageSafely(PageTypes.MARKET)

    private fun clickMarketCategory(marketCategoryTypes: MarketCategoryTypes) {
        val marketCategory: String = getXpathWithMarketCategory(marketCategoryTypes)

        webDriverHandler.clickAndWait("$marketCategory/a")
            .withTimeout(Duration.ofSeconds(3))
            .until(
                ExpectedConditions.elementToBeClickable(
                    webDriverHandler.findElementSafely("$marketCategory/ul/li[1]/a")
                )
            )
    }

    private fun clickMarketSubCategory(marketCategoryTypes: MarketCategoryTypes) =
        webDriverHandler.clickAndWait(getXpathWithMarketCategoryAll(marketCategoryTypes))
            .withTimeout(Duration.ofSeconds(3))
            .until(
                ExpectedConditions.visibilityOfAllElements(
                    webDriverHandler.findElementSafely(ElementTypes.ELEMENT_MARKET_FIRST_ITEM)
                )
            )

    private fun getXpathWithMarketCategory(marketCategoryTypes: MarketCategoryTypes): String =
        ElementTypes.BUTTON_MARKET_CATEGORY.xpath + "/li[${marketCategoryTypes.value}]"

    private fun getXpathWithMarketCategoryAll(marketCategoryTypes: MarketCategoryTypes): String =
        getXpathWithMarketCategory(marketCategoryTypes) + "/ul/li[1]/a"

    private fun getXpathItem(i: Int) = ElementTypes.ELEMENT_MARKET_ITEM.xpath + "/tr[${i + 1}]"

    private fun getItemNameXpathWithItemXpath(xPath: String) = "$xPath/td[1]/div/span[2]"

    private fun getItemBundleXpathWithItemXpath(xPath: String) = "$xPath/td[1]/div/span[3]/em"

    private fun getItemPriceXpathWithItemXpath(xPath: String) = "$xPath/td[4]"

    private fun getItemNameWithItemIndex(index: Int) =
        webDriverHandler.findElementSafely("${getXpathItem(index)}/td[1]/div/span[2]").text

    private fun getItemPriceWithItemIndex(index: Int) =
        webDriverHandler.findElementSafely("${getXpathItem(index)}/td[4]").text

    private fun getItemBundleWithItemIndex(index: Int): Long {
        var bundle = 1L;

        try {
            val bundleText = webDriverHandler.findElementSafely("${getXpathItem(index)}/td[1]/div/span[3]/em").text
            bundle = "\\d++".toRegex().find(bundleText)?.value?.toLongOrNull() ?: 1L;
        } catch (e: NoSuchElementException) {
        }

        return bundle
    }

    private fun clickPageButton(pageNumber: Int, firstItemName: String = "") =
        webDriverHandler.clickAndWait(getPageNumberElementXpath(pageNumber))
            .withTimeout(Duration.ofSeconds(3))
            .ignoring(StaleElementReferenceException::class.java)
            .until(
                ExpectedConditions.invisibilityOfElementWithText(
                    By.xpath(
                        getItemNameXpathWithItemXpath(
                            getXpathItem(
                                0
                            )
                        )
                    ), firstItemName
                )
            )

    private fun getPageNumberElementXpath(pageNumber: Int): String {
        var elementNumber = 3
        if (pageNumber > 1) {
            elementNumber = (pageNumber % 11) + 1
        }
        return ElementTypes.BUTTON_MARKET_PAGINATION_START.xpath + "/a[$elementNumber]"
    }

    private fun isPageNumberElement(pageNumber: Int): Boolean {
        val pageNumberXpath = getPageNumberElementXpath(pageNumber)
        return webDriverHandler.hasElement(pageNumberXpath) &&
                (pageNumber == 1 || webDriverHandler.findElementSafely(pageNumberXpath).text.toLongOrNull() != null)
    }

    private fun getMarketItemList(marketCategoryTypes: MarketCategoryTypes) {
        clickMarketCategory(marketCategoryTypes)
        clickMarketSubCategory(marketCategoryTypes)

        var pageNumber = 1
        var firstItemName = ""

        while (isPageNumberElement(pageNumber)) {
            var itemIndex = 0

            if (pageNumber > 1) {
                logger().info("PAGE NUMBER, [$pageNumber, $firstItemName]")
                clickPageButton(pageNumber, firstItemName)
            }

            while (webDriverHandler.hasElement(getXpathItem(itemIndex))) {
                val itemName = getItemNameWithItemIndex(itemIndex)
                val itemBundle = getItemBundleWithItemIndex(itemIndex)

                if (itemIndex < 1) {
                    firstItemName = itemName
                }
                this.itemMap[itemName] =
                    getLongTypePrice(getItemPriceWithItemIndex(itemIndex++)) / itemBundle.toDouble()
                logger().info("[#$itemIndex]$itemName : ${this.itemMap[itemName]}")
            }
            pageNumber++
        }
    }

    private fun getLongTypePrice(price: String): Long = price.replace(",", "").toLong()
}