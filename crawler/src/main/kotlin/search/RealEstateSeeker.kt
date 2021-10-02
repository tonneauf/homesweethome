package search

import search.model.PropertyAdvertisement

interface RealEstateSeeker {
    suspend fun search(searchOptions: SearchOptions) : List<PropertyAdvertisement>
}

data class SearchOptions(var location: String)