package com.ama_patrol.data.models.map

interface DirectionFinderListener {
    fun onDirectionFinderStart()
    fun onDirectionFinderSuccess(route: List<Route?>?)
}