package search.lbc

import crawler.puppeteer.Browser
import crawler.puppeteer.Page
import crawler.puppeteer.searchAttributes
import crawler.puppeteer.searchHtmlElement
import kotlinx.coroutines.await
import search.RealEstateSeeker
import search.SearchOptions
import search.model.PropertyAdvertisement
import util.extractNumber

class LBCSeeker(private val crawler: Browser) : RealEstateSeeker {

    private val lbcUrl = "https://www.leboncoin.fr"
    private val baseSearchURL: String = "$lbcUrl/recherche"

    override suspend fun search(searchOptions: SearchOptions): List<PropertyAdvertisement> {
        val searchPage = crawler.newPage().await()

        searchPage.goto(
            "$baseSearchURL?category=9" +
                    "&locations=${searchOptions.location}" +
                    "&price=min-${searchOptions.maxPrice}" +
                    "&square=${searchOptions.minSurfaceArea}-max"
        )
            .await()

        val urls = getURLsFromSearchPage(searchPage)

        return extractAds(urls)
    }

    private suspend fun extractAds(urls: List<String?>): List<PropertyAdvertisement> {
        val ads = mutableListOf<PropertyAdvertisement>()
        for (i in 0 until ads.size) {
            if (urls[i] != null) {
                ads += extractAd(lbcUrl + urls[i]!!)
            }
        }
        return ads
    }

    private suspend fun extractAd(url: String): PropertyAdvertisement {
        val adPage = crawler.newPage().await()
        adPage.goto(url).await()

        val title = getTitle(adPage)
        val surfaceArea = getSurfaceArea(adPage)
        val price = getPrice(adPage)
        val photos = getPhotos(adPage)
        val description = getDescription(adPage)
        val roomNumber = getRoomNumber(adPage)

        return PropertyAdvertisement(title!!, url, surfaceArea, roomNumber, price, photos, description!!)
    }

    private suspend fun getURLsFromSearchPage(searchPage: Page) =
        searchAttributes(searchPage, "href", "[data-qa-id='aditem_container']")

    private suspend fun getTitle(adPage: Page) =
        searchHtmlElement(
            adPage,
            "[data-qa-id='adview_spotlight_description_container'] > div:nth-child(1) > div:nth-child(1) > h1"
        )

    private suspend fun getSurfaceArea(adPage: Page) =
        extractNumber(
            searchHtmlElement(
                adPage,
                "[data-qa-id='adview_spotlight_description_container'] > div:nth-child(2) > div:nth-child(1) > span:nth-child(2)"
            )!!
        )

    private suspend fun getPrice(adPage: Page) =
        extractNumber(searchHtmlElement(adPage, "[data-qa-id='adview_price']")!!)

    private suspend fun getPhotos(adPage: Page) =
        searchAttributes(adPage, "src", "[data-qa-id='adview_spotlight_container'] picture img")

    private suspend fun getDescription(adPage: Page) =
        searchHtmlElement(adPage, "[data-qa-id='adview_description_container']")

    private suspend fun getRoomNumber(adPage: Page) =
        extractNumber(
            searchHtmlElement(
                adPage,
                "[data-qa-id='adview_spotlight_description_container'] > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)"
            )!!
        )
}