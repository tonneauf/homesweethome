package storage

import database.Database
import database.MongoClient
import kotlinx.coroutines.await

private const val DATABASE_URL = "mongodb://localhost:27017"
private const val DATABASE_NAME = "homesweethome"

private val mongoClient = MongoClient(DATABASE_URL)

suspend fun newDatabaseConnection(): Database {
    mongoClient.connect().await()
    return mongoClient.db(DATABASE_NAME)
}

suspend fun closeDatabaseConnection() {
    mongoClient.close().await()
}
