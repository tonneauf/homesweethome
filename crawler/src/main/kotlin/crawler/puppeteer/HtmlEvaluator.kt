package crawler.puppeteer

import kotlinx.coroutines.await
import org.w3c.dom.Element

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