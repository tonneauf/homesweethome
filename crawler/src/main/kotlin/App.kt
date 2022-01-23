import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import search.RealEstateSeeker
import search.model.PropertyAdvertisement
import storage.PropertyAdvertisementStore
import storage.closeDatabaseConnection
import storage.newDatabaseConnection

fun main() {

    @OptIn(DelicateCoroutinesApi::class)
    GlobalScope.launch {

        val adsToStore = RealEstateSeeker().getAds()

        storeAds(adsToStore)
    }
}

suspend fun storeAds(adsToStore: List<PropertyAdvertisement>) {
    val database = newDatabaseConnection()
    val propertyAdvertisementStore = PropertyAdvertisementStore(database)
    propertyAdvertisementStore.store(adsToStore)

    closeDatabaseConnection()
}
