package com.ama_patrol.presentation.menu.locator

import com.ama_patrol.data.models.map.DirectionResponses
import com.ama_patrol.presentation.menu.SaveMode
import com.google.android.gms.maps.model.LatLng

data class LocatorState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val enabled: Boolean = false,
    val result: Result<DirectionResponses>? = null,
    val saveMode: SaveMode = SaveMode.SAVE
)

sealed class LocatorAction {
    data class EnteredLatitude(
        val startLocation: LatLng, val endLocation: LatLng
    ) : LocatorAction()
    data class SavingMode(val saveMode: SaveMode): LocatorAction()
}

sealed class LocatorEffect {
    data class ShowErrorMessage(val message: String) : LocatorEffect()
    object SaveNote : LocatorEffect()
    data class DrawPolygon(val location: LatLng, val shape: String) : LocatorEffect()
}