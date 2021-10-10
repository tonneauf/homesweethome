import crawler.puppeteer.Puppeteer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import search.SearchOptions
import search.lbc.LBCSeeker
import storage.PropertyAdvertisementStore
import storage.newDatabaseConnection

fun main() {

    @OptIn(DelicateCoroutinesApi::class)
    GlobalScope.launch {

        val puppeteer: Puppeteer = util.require("puppeteer-firefox")
        val browser = puppeteer.launch().await()

        val seeker = LBCSeeker(browser)
        val searchOptions = SearchOptions("Paris__48.851498164430275_2.3494660290817677_10000")

        val adsToStore = seeker.search(searchOptions)

        browser.close().await()

        val database = newDatabaseConnection()
        val propertyAdvertisementStore = PropertyAdvertisementStore(database)
        propertyAdvertisementStore.store(adsToStore)

        for (ad in propertyAdvertisementStore.findAll()) {
            console.log(ad)
        }
    }

}