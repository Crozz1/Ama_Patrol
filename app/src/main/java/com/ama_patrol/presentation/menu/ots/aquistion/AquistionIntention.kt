package com.ama_patrol.presentation.menu.ots.aquistion

import com.ama_patrol.data.models.map.DirectionResponses
import com.ama_patrol.presentation.menu.SaveMode
import com.google.android.gms.maps.model.LatLng

data class AquistionState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val enabled: Boolean = false,
    val result: Result<DirectionResponses>? = null,
    val saveMode: SaveMode = SaveMode.SAVE
)

sealed class AquistionAction {
    data class EnteredLatitude(
        val startLocation: LatLng, val endLocation: LatLng
    ) : AquistionAction()
    data class SavingMode(val saveMode: SaveMode): AquistionAction()
}

sealed class AquistionEffect {
    data class ShowErrorMessage(val message: String) : AquistionEffect()
    object SaveNote : AquistionEffect()
    data class DrawPolygon(val location: LatLng, val shape: String) : AquistionEffect()
}