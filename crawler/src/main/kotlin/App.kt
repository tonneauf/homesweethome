import crawler.puppeteer.Puppeteer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch

import search.SearchOptions
import search.lbc.LBCSeeker

fun main() {

    @OptIn(DelicateCoroutinesApi::class)
    GlobalScope.launch {

        val puppeteer: Puppeteer = util.require("puppeteer-firefox") as Puppeteer
        val browser = puppeteer.launch().await()

        val seeker = LBCSeeker(browser)
        val searchOptions = SearchOptions("Paris__48.851498164430275_2.3494660290817677_10000")

        seeker.search(searchOptions)

        browser.close().await()
    }

}