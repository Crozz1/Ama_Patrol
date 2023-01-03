package com.ama_patrol.presentation.menu
import com.ama_patrol.R
import com.ama_patrol.data.models.map.DirectionResponses
import com.google.android.gms.maps.model.LatLng

data class MonitoringNoteState(
    val id: Int = 0,
    val origin :String = "",
    val destination :String ="",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0, val enabled: Boolean = false,
    val result: Result<DirectionResponses>? = null,
    val saveMode: SaveMode = SaveMode.SAVE,
    val color: Int = R.color.red

)

sealed class MonitoringNoteAction {
    data class EnteredLatitude(
        val startLocation: LatLng, val endLocation: LatLng
    ) : MonitoringNoteAction()

    data class SavingMode(val saveMode: SaveMode): MonitoringNoteAction()
    data class EnteredId(val value: Int) : MonitoringNoteAction()
    data class EnteredOrigin(val value: String) : MonitoringNoteAction()
    data class EnteredDestination(val value: String) : MonitoringNoteAction()
    data class ChangeColor(val color: Int) : MonitoringNoteAction()
}

sealed class HomeEffect {
    data class ShowErrorMessage(val message: String) : HomeEffect()
    object SaveMonitoring : HomeEffect()
    data class DrawPolygon(val location: LatLng, val shape: String) : HomeEffect()
}

enum class SaveMode {
    SAVE, UPDATE
}




