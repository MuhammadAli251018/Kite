package online.muhammadali.kite.location.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val longitude: Double,
    val latitude: Double
)
