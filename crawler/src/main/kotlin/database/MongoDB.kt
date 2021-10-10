@file:JsModule("mongodb")
package database

import kotlin.js.Promise

external class MongoClient(url : String) {
    fun connect() : Promise<MongoClient>
    fun db(dbName : String) : Database
}

external class Database {
    fun collection(collectionName : String) : Collection
}

external class Collection {
    fun find() : FindCursor
    fun insertOne(document : Any) : Promise<InsertManyResult>
}

external class InsertManyResult {
    val insertedId : ObjectId
}

external class FindCursor {
    fun toArray() : Promise<Array<Any>>
}

external class ObjectId
