package search

import search.model.PropertyAdvertisement

interface RealEstateSupplier {
    suspend fun search(searchOptions: SearchOptions) : List<PropertyAdvertisement>
}

data class SearchOptions(var location: String) {
    var maxPrice : Int = Int.MAX_VALUE
    var minSurfaceArea : Int = 0
    var maxResult: Int = Int.MAX_VALUE
}