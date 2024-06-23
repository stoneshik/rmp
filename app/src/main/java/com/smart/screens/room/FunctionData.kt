package com.smart.screens.room

import kotlinx.serialization.Serializable

@Serializable
data class FunctionHumidityData(
    val idRoom: Int,
    val enableFunctions: Array<String>,
    val humidity: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as FunctionHumidityData
        if (idRoom != other.idRoom) return false
        if (!enableFunctions.contentEquals(other.enableFunctions)) return false
        if (humidity != other.humidity) return false
        return true
    }

    override fun hashCode(): Int {
        var result = idRoom
        result = 31 * result + enableFunctions.contentHashCode()
        result = 31 * result + humidity.hashCode()
        return result
    }
}

@Serializable
data class FunctionLightsData(
    val idRoom: Int,
    val enableFunctions: Array<String>,
    val lights: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as FunctionLightsData
        if (idRoom != other.idRoom) return false
        if (!enableFunctions.contentEquals(other.enableFunctions)) return false
        if (lights != other.lights) return false
        return true
    }

    override fun hashCode(): Int {
        var result = idRoom
        result = 31 * result + enableFunctions.contentHashCode()
        result = 31 * result + lights.hashCode()
        return result
    }
}

@Serializable
data class FunctionTemperatureData(
    val idRoom: Int,
    val enableFunctions: Array<String>,
    val temperature: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as FunctionTemperatureData
        if (idRoom != other.idRoom) return false
        if (!enableFunctions.contentEquals(other.enableFunctions)) return false
        if (temperature != other.temperature) return false
        return true
    }

    override fun hashCode(): Int {
        var result = idRoom
        result = 31 * result + enableFunctions.contentHashCode()
        result = 31 * result + temperature.hashCode()
        return result
    }
}