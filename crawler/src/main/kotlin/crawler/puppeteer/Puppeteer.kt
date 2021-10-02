package crawler.puppeteer

import org.w3c.dom.Element
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
    @JsName("\$eval")
    fun eval(cssSelector: String, func: (element : Element) -> String?) : Promise<String?>
    @JsName("\$\$eval")
    fun evalMultiple(cssSelector: String, func: (elements : Array<Element>) -> Array<String?>) : Promise<Array<String?>>
}