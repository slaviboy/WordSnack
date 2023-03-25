package com.slaviboy.wordsnack.entities

sealed class ClipButtonState(
    val up: ImageType,
    val down: ImageType,
    val iconUp: ImageType? = null,
    val iconDown: ImageType? = null
) {
    object GreenButtonState : ClipButtonState(up = CommonImageType.GreenButtonUp, down = CommonImageType.GreenButtonDown)
}