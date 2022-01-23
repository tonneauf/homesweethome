import search.RealEstateSeeker
import util.testAsync
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {

    @Test
    fun searchForAds() = testAsync {
        val ads = RealEstateSeeker().getAds()
        assertEquals(1, ads.size)
    }
}