package storage

import database.Collection
import database.Database
import database.ObjectId
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
        val insertedIds = collection.insertMany(ads.toTypedArray()).await().insertedIds
        for (i in ads.indices) {
            ads[i]._id = insertedIds["$i"] as ObjectId
        }
    }
}