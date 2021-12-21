package com.nativedevps.support.utility.device.audio

import android.content.Context
import android.media.AudioManager

object DeviceAudio {
    fun Context.getCurrentVolume(): Int {
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val volumeLevel: Int = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolumeLevel: Int = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        return (volumeLevel.toFloat() / maxVolumeLevel * 100).toInt()
    }
}