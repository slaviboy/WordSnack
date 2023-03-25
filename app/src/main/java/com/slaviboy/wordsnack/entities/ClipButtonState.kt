package com.slaviboy.wordsnack.entities

sealed class ClipButtonState(
    val up: ImageType,
    val down: ImageType,
    val iconUp: ImageType? = null,
    val iconDown: ImageType? = null
) {
    object CloseButtonState : ClipButtonState(up = CommonImageType.CloseButtonUp, down = CommonImageType.CloseButtonDown)
    object ExtraWordsButtonState : ClipButtonState(up = CommonImageType.ExtraWordsButtonUp, down = CommonImageType.ExtraWordsButtonDown)
    object GreenButtonState : ClipButtonState(up = CommonImageType.GreenButtonUp, down = CommonImageType.GreenButtonDown)
    object BlueButtonState : ClipButtonState(up = CommonImageType.BlueButtonUp, down = CommonImageType.BlueButtonDown)
    object RedButtonState : ClipButtonState(up = CommonImageType.RedButtonUp, down = CommonImageType.RedButtonDown)
    object FacebookButtonState : ClipButtonState(up = CommonImageType.FacebookButtonUp, down = CommonImageType.FacebookButtonDown)
}