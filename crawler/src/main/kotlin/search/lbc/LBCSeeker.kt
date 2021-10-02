package search.lbc

import crawler.puppeteer.Browser
import crawler.puppeteer.Page
import kotlinx.coroutines.await
import search.RealEstateSeeker
import search.SearchOptions
import search.model.PropertyAdvertisement

class LBCSeeker(private val crawler: Browser) : RealEstateSeeker {

    private val baseSearchURL: String = "https://www.leboncoin.fr/recherche"

    override suspend fun search(searchOptions: SearchOptions): List<PropertyAdvertisement> {
        val searchPage = crawler.newPage().await()

        searchPage.goto("$baseSearchURL?category=9&locations=${searchOptions.location}")
            .await()

        searchHtmlElements(searchPage, "[data-qa-id='aditem_price']")

        return listOf()
    }

    private suspend fun searchHtmlElement(page : Page, cssSelector : String) : String? {
        return page.eval(cssSelector) { element -> element.textContent }.await()
    }

    private suspend fun searchHtmlElements(page: Page, cssSelector: String) : Array<String?> {
        return page.evalMultiple(cssSelector) { elements ->
            var strings: Array<String?> = arrayOf()
            for (element in elements) {
                strings += element.textContent
            }
            strings
        }.await()
    }
}