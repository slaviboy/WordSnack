package com.slaviboy.wordsnack.entities

sealed class ClipButtonState(
    val up: ImageType,
    val down: ImageType,
    val iconUp: ImageType? = null,
    val iconDown: ImageType? = null
) {
    object ShareButtonState : ClipButtonState(
        up = CommonImageType.ShareButtonUp, down = CommonImageType.ShareButtonDown
    )

    object ShopButtonState : ClipButtonState(
        up = CommonImageType.ShopButtonUp, down = CommonImageType.ShopButtonDown
    )

    object SettingsButtonState : ClipButtonState(
        up = CommonImageType.SettingsButtonUp, down = CommonImageType.SettingsButtonDown
    )

    object ShuffleButtonState : ClipButtonState(
        up = CommonImageType.ShuffleButtonUp, down = CommonImageType.ShuffleButtonDown
    )

    object HintButtonState : ClipButtonState(
        up = CommonImageType.HintButtonUp, down = CommonImageType.HintButtonDown,
        iconUp = CommonImageType.HintIconUp, iconDown = CommonImageType.HintIconDown
    )

    object CloseButtonState : ClipButtonState(
        up = CommonImageType.CloseButtonUp, down = CommonImageType.CloseButtonDown
    )

    object ExtraWordsButtonState : ClipButtonState(
        up = CommonImageType.ExtraWordsButtonUp, down = CommonImageType.ExtraWordsButtonDown
    )

    object GreenButtonState : ClipButtonState(
        up = CommonImageType.GreenButtonUp, down = CommonImageType.GreenButtonDown
    )

    object BlueButtonState : ClipButtonState(
        up = CommonImageType.BlueButtonUp, down = CommonImageType.BlueButtonDown
    )

    object RedButtonState : ClipButtonState(
        up = CommonImageType.RedButtonUp, down = CommonImageType.RedButtonDown
    )

    object FacebookButtonState : ClipButtonState(
        up = CommonImageType.FacebookButtonUp, down = CommonImageType.FacebookButtonDown
    )
}