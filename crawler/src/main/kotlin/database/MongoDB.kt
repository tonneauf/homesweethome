@file:JsModule("mongodb")
package database

import kotlin.js.Json
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
    fun insertOne(document : Any) : Promise<InsertOneResult>
    fun insertMany(documents : Array<Any>) : Promise<InsertManyResult>
}

external class InsertOneResult {
    val insertedId : ObjectId
}

external class InsertManyResult {
    val insertedIds : Json
}

external class FindCursor {
    fun toArray() : Promise<Array<Any>>
}

external class ObjectId
