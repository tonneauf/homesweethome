package puppeteer

import kotlin.js.Promise

external fun require(module: String): Puppeteer

external class Puppeteer {
    fun launch() : Promise<Browser>
}

data class LaunchOptions(var product : String)

external class Browser {
    fun newPage() : Promise<Page>
    fun close() : Promise<dynamic>
}

external class Page {
    fun goto(url : String) : Promise<dynamic>
    fun title() : Promise<String>
    fun content() : Promise<String>
}