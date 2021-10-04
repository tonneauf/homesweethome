@file:JsModule("puppeteer-firefox")
package crawler.puppeteer

import org.w3c.dom.Element
import kotlin.js.Promise

external class Puppeteer {
    fun launch(): Promise<Browser>
}

external class Browser {
    fun newPage(): Promise<Page>
    fun close(): Promise<dynamic>
}

external class Page {
    fun goto(url: String): Promise<dynamic>
    fun title(): Promise<String>
    fun content(): Promise<String>

    @JsName("\$eval")
    fun eval(cssSelector: String, func: (element: Element) -> String?): Promise<String?>

    @JsName("\$\$eval")
    fun evalMultiple(
        cssSelector: String,
        func: (elements: Array<Element>, varargs: Array<Any>) -> Array<String?>,
        varargs: Array<Any>
    ): Promise<Array<String?>>
}