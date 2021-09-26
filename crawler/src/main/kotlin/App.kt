import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch

fun main() {

    @OptIn(DelicateCoroutinesApi::class)
    GlobalScope.launch {

        val puppeteer = puppeteer.require("puppeteer-firefox")
        val browser = puppeteer.launch().await()

        val page = browser.newPage().await()

        page.goto("www.google.com").await()

        console.log(page.title().await())

        browser.close().await()
    }

}