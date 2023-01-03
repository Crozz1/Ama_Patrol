package com.ama_patrol.presentation.menu.ots.maintenace

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

class MaintenanceViewModel : ViewModel() {

    private val mapRepository: MapRepository
    private val _maintenanceState = MutableStateFlow(MaintenanceState())
    private val maintenanceState: StateFlow<MaintenanceState> = _maintenanceState

    private val _eventFlow = MutableSharedFlow<MaintenanceEffect>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        mapRepository = MapRepositoryImpl(MapDataSource())
    }

    fun onAction(action: MaintenanceAction) {
        when (action) {
            is MaintenanceAction.EnteredLatitude -> {
                _maintenanceState.value = maintenanceState.value.copy(
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
                                MaintenanceEffect.DrawPolygon(action.endLocation, shape = shape)
                            )
                        }
                    }
                }
            }
            is MaintenanceAction.SavingMode -> {}
        }
    }

}