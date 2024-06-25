package com.smart.screens.signaling

import kotlinx.serialization.Serializable

@Serializable
data class SignalingData(val signalingWorkingState: String, val signalingState: Boolean)

@Serializable
data class SignalingStateData(val signalingState: Boolean)
