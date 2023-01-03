package com.ama_patrol.presentation.menu.ots.aquistion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama_patrol.data.network.datasource.map.MapDataSource
import com.ama_patrol.data.network.Result
import com.ama_patrol.data.repository.map.MapRepository
import com.ama_patrol.data.repository.map.MapRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AquistionViewModel : ViewModel() {

    private val mapRepository: MapRepository
    private val _aquistionState = MutableStateFlow(AquistionState())
    private val aquistionState: StateFlow<AquistionState> = _aquistionState

    private val _eventFlow = MutableSharedFlow<AquistionEffect>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        mapRepository = MapRepositoryImpl(MapDataSource())
    }

    fun onAction(action: AquistionAction) {
        when (action) {
            is AquistionAction.EnteredLatitude -> {
               _aquistionState.value = aquistionState.value.copy(
                    latitude = action.endLocation.latitude, longitude = action.endLocation.longitude
                )

                viewModelScope.launch {
                    val start =
                        action.startLocation.latitude.toString() + "," + action.startLocation.longitude.toString() //"-6.110116,106.931418"
                    val end =
                        action.endLocation.latitude.toString() + "," + action.endLocation.longitude.toString() //"-6.160455,106.905464"
                    val result = mapRepository.getDirection(
                        origin = start, destination = end
                    )
                    result.collect {
                        if (it.status == Result.Status.SUCCESS) {
                            val shape = it.data?.routes?.get(0)?.overviewPolyline?.points.orEmpty()
                            _eventFlow.emit(
                                AquistionEffect.DrawPolygon(action.endLocation, shape = shape)
                            )
                        }
                    }
                }
            }
            is AquistionAction.SavingMode -> {}
        }
    }

}