package crawler.puppeteer

import kotlinx.coroutines.await
import org.w3c.dom.Element
import kotlin.js.Promise

external fun require(module: String): Puppeteer

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

suspend fun searchAttributes(page: Page, htmlAttribute: String, cssSelector: String): List<String?> {
    return page.evalMultiple(cssSelector, { elements, attributes ->
        var strings: Array<String?> = arrayOf()
        for (element in elements) {
            strings += element.getAttribute(attributes[0] as String)
        }
        strings
    }, arrayOf(htmlAttribute)).await().toList()
}

suspend fun searchHtmlElement(page: Page, cssSelector: String): String? {
    return page.eval(cssSelector, Element::textContent).await()
}

suspend fun searchHtmlElements(page: Page, cssSelector: String): List<String?> {
    return page.evalMultiple(cssSelector, { elements, _ ->
        var strings: Array<String?> = arrayOf()
        for (element in elements) {
            strings += element.textContent
        }
        strings
    }, emptyArray()).await().toList()
}