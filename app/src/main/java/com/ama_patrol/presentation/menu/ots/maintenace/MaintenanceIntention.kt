package com.ama_patrol.presentation.menu.ots.maintenace

import com.ama_patrol.data.models.map.DirectionResponses
import com.ama_patrol.presentation.menu.SaveMode
import com.google.android.gms.maps.model.LatLng

data class MaintenanceState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val enabled: Boolean = false,
    val result: Result<DirectionResponses>? = null,
    val saveMode: SaveMode = SaveMode.SAVE
)

sealed class MaintenanceAction {
    data class EnteredLatitude(
        val startLocation: LatLng, val endLocation: LatLng
    ) : MaintenanceAction()
    data class SavingMode(val saveMode: SaveMode): MaintenanceAction()
}

sealed class MaintenanceEffect {
    data class ShowErrorMessage(val message: String) : MaintenanceEffect()
    object SaveNote : MaintenanceEffect()
    data class DrawPolygon(val location: LatLng, val shape: String) : MaintenanceEffect()
}