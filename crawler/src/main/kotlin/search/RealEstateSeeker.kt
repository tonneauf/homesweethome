package search

import crawler.puppeteer.Puppeteer
import kotlinx.coroutines.await
import search.lbc.LBCSupplier
import search.model.PropertyAdvertisement

class RealEstateSeeker {

    suspend fun getAds(): List<PropertyAdvertisement> {
        val puppeteer: Puppeteer = util.require("puppeteer-firefox")
        val browser = puppeteer.launch().await()

        val seeker = LBCSupplier(browser)
        val searchOptions = SearchOptions("Paris__48.848517486904306_2.352639092559715_10000")
        searchOptions.maxResult = 1

        val adsToStore = seeker.search(searchOptions)

        browser.close().await()

        return adsToStore
    }
}