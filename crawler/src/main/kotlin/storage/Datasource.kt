package storage

import database.Database
import database.MongoClient
import kotlinx.coroutines.await

private const val DATABASE_URL = "mongodb://localhost:27017"
private const val DATABASE_NAME = "homesweethome"

suspend fun newDatabaseConnection(): Database {
    val client = MongoClient(DATABASE_URL)

    client.connect().await()

    return client.db(DATABASE_NAME)
}
