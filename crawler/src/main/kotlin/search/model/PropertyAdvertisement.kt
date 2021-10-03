package search.model

data class PropertyAdvertisement(
    val title: String,
    val url: String,
    val surfaceArea: Int,
    val roomNumber: Int,
    val price: Int,
    val photos: List<String?>,
    val description: String,
)

