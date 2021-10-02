import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch

import crawler.puppeteer.require
import search.SearchOptions
import search.lbc.LBCSeeker

fun main() {

    @OptIn(DelicateCoroutinesApi::class)
    GlobalScope.launch {

        val puppeteer = require("puppeteer-firefox")
        val browser = puppeteer.launch().await()

        val seeker = LBCSeeker(browser)
        seeker.search(SearchOptions("Paris"))

        browser.close().await()
    }

}