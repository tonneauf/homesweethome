package search.lbc

import crawler.puppeteer.Browser
import crawler.puppeteer.Page
import crawler.puppeteer.searchHtmlElements
import crawler.puppeteer.searchUrls
import kotlinx.coroutines.await
import search.RealEstateSeeker
import search.SearchOptions
import search.model.PropertyAdvertisement

class LBCSeeker(private val crawler: Browser) : RealEstateSeeker {

    private val baseSearchURL: String = "https://www.leboncoin.fr/recherche"

    override suspend fun search(searchOptions: SearchOptions): List<PropertyAdvertisement> {
        val searchPage = crawler.newPage().await()

        searchPage.goto(
            "$baseSearchURL?category=9" +
                    "&locations=${searchOptions.location}" +
                    "&price=min-${searchOptions.maxPrice}" +
                    "&square=${searchOptions.minSurfaceArea}-max"
        )
            .await()

        val ads = mutableListOf<PropertyAdvertisement>()

        val titles = getTitlesFromSearchPage(searchPage)
        val urls = getURLsFromSearchPage(searchPage)

        if (titles.size != urls.size) {
            throw RuntimeException("should have the same number of title and URL")
        }

        for (i in titles.indices) {
            if (titles[i] != null && urls[i] != null) {
                ads += PropertyAdvertisement(titles[i]!!, urls[i]!!)
            }
        }

        return ads
    }

    private suspend fun getURLsFromSearchPage(searchPage: Page) =
        searchUrls(searchPage, "[data-qa-id='aditem_container']")

    private suspend fun getTitlesFromSearchPage(searchPage: Page) =
        searchHtmlElements(searchPage, "[data-qa-id='aditem_title']")
}