package storage

import database.Collection
import database.Database
import kotlinx.coroutines.await
import search.model.PropertyAdvertisement

class PropertyAdvertisementStore(database: Database) {

    companion object {
        private const val COLLECTION_NAME = "ads"
    }

    private val collection: Collection

    init {
        collection = database.collection(COLLECTION_NAME)
    }

    suspend fun findAll(): Array<PropertyAdvertisement> =
        collection.find().toArray().await() as Array<PropertyAdvertisement>

    suspend fun store(ads: List<PropertyAdvertisement>) {
        for (ad in ads) {
            ad._id = collection.insertOne(ad).await().insertedId
        }
    }
}