package com.slaviboy.wordsnack.entities

sealed class ImageType(open val index: Int)

sealed class CommonImageType(override val index: Int) : ImageType(index) {
    object AppleIcon : CommonImageType(0)
    object ArrowLeft : CommonImageType(1)
    object ArrowRight : CommonImageType(2)
    object BlueButtonDown : CommonImageType(3)
    object BlueButtonUp : CommonImageType(4)
    object BlueProgressBackground : CommonImageType(5)
    object BlueProgressProgress : CommonImageType(6)
    object BonusCenter : CommonImageType(7)
    object BonusNeedle : CommonImageType(8)
    object BonusWheel : CommonImageType(9)
    object BonusWheelBg : CommonImageType(10)
    object BonusWheelWinOverlay : CommonImageType(11)
    object Bubble : CommonImageType(12)
    object Circle : CommonImageType(13)
    object CloseButtonDown : CommonImageType(14)
    object CloseButtonUp : CommonImageType(15)
    object CreditsIcon : CommonImageType(16)
    object CreditsIconL : CommonImageType(17)
    object CreditsIconM : CommonImageType(18)
    object CreditsIconS : CommonImageType(19)
    object CreditsIconXL : CommonImageType(20)
    object CreditsIconXXL : CommonImageType(21)
    object CreditsIconXXXL : CommonImageType(22)
    object CrosspromoWordpark : CommonImageType(23)
    object CurrentLettersCenter : CommonImageType(24)
    object CurrentLettersLeft : CommonImageType(25)
    object CurrentLettersRight : CommonImageType(26)
    object DialogBg : CommonImageType(27)
    object DialogBgLarge : CommonImageType(28)
    object DialogDecorBottom : CommonImageType(29)
    object DialogDecorFront : CommonImageType(30)
    object DialogDecorTop : CommonImageType(31)
    object DialogLoading : CommonImageType(32)
    object DialogRowDark : CommonImageType(33)
    object DialogRowSmall : CommonImageType(34)
    object DialogRow : CommonImageType(35)
    object DialogShadow : CommonImageType(36)
    object Fill : CommonImageType(37)
    object ExtraWordsBasket : CommonImageType(38)
    object ExtraWordsButtonDown : CommonImageType(39)
    object ExtraWordsButtonUp : CommonImageType(40)
    object ExtraWordsIconDown : CommonImageType(41)
    object ExtraWordsIconUp : CommonImageType(42)
    object FacebookButtonDown : CommonImageType(43)
    object FacebookButtonUp : CommonImageType(44)
    object FacebookIcon : CommonImageType(45)
    object FlagBA : CommonImageType(46)
    object FlagBG : CommonImageType(47)
    object FlagBR : CommonImageType(48)
    object FlagBY : CommonImageType(49)
    object FlagCA : CommonImageType(50)
    object FlagCZ : CommonImageType(51)
    object FlagDE : CommonImageType(52)
    object FlagDK : CommonImageType(53)
    object FlagEE : CommonImageType(54)
    object FlagES : CommonImageType(55)
    object FlagFI : CommonImageType(56)
    object FlagFR : CommonImageType(57)
    object FlagGR : CommonImageType(58)
    object FlagHR : CommonImageType(59)
    object FlagHU : CommonImageType(60)
    object FlagID : CommonImageType(61)
    object FlagIS : CommonImageType(62)
    object FlagIT : CommonImageType(63)
    object FlagLT : CommonImageType(64)
    object FlagLV : CommonImageType(65)
    object FlagNL : CommonImageType(66)
    object FlagNO : CommonImageType(67)
    object FlagPL : CommonImageType(68)
    object FlagPT : CommonImageType(69)
    object FlagRO : CommonImageType(70)
    object FlagRS : CommonImageType(71)
    object FlagRU : CommonImageType(72)
    object FlagSE : CommonImageType(73)
    object FlagSI : CommonImageType(74)
    object FlagSK : CommonImageType(75)
    object FlagTR : CommonImageType(76)
    object FlagUA : CommonImageType(77)
    object FlagUK : CommonImageType(78)
    object FlagUS : CommonImageType(79)
    object FooterBg : CommonImageType(80)
    object GreenButtonDown : CommonImageType(81)
    object GreenButtonUp : CommonImageType(82)
    object HintButtonDown : CommonImageType(83)
    object HintButtonUp : CommonImageType(84)
    object HintIconDown : CommonImageType(85)
    object HintIconUp : CommonImageType(86)
    object HintPriceBg : CommonImageType(87)
    object IconInvite : CommonImageType(88)
    object IconOffer : CommonImageType(89)
    object IconShare : CommonImageType(90)
    object IconVideo : CommonImageType(91)
    object Leaf : CommonImageType(92)
    object LetterBox : CommonImageType(93)
    object LetterFixed : CommonImageType(94)
    object LetterLarge : CommonImageType(95)
    object LetterShadow : CommonImageType(96)
    object RedButtonDown : CommonImageType(97)
    object RedButtonUp : CommonImageType(98)
    object RefreshIcon : CommonImageType(99)
    object RemoveAdsIcon : CommonImageType(100)
    object Rope : CommonImageType(101)
    object RoundedRect : CommonImageType(102)
    object RoundedRectSmall : CommonImageType(103)
    object SettingsButtonDown : CommonImageType(104)
    object SettingsButtonUp : CommonImageType(105)
    object ShareButtonDown : CommonImageType(106)
    object ShareButtonUp : CommonImageType(107)
    object ShopButtonDown : CommonImageType(108)
    object ShopButtonUp : CommonImageType(109)
    object ShuffleButtonDown : CommonImageType(110)
    object ShuffleButtonUp : CommonImageType(111)
    object Square : CommonImageType(112)
    object StarIcon : CommonImageType(113)
    object StatusbarBg : CommonImageType(114)
    object SwitchBgOff : CommonImageType(115)
    object SwitchBgOn : CommonImageType(116)
    object SwitchKnob : CommonImageType(117)
    object TutorialLettersBg : CommonImageType(118)
    object WordlistBg : CommonImageType(119)
}

sealed class AnimationsImageType(override val index: Int) : ImageType(index) {
    object ChapterBanner : AnimationsImageType(0)
    object ChapterGreen : AnimationsImageType(1)
    object ChapterSky : AnimationsImageType(2)
    object ChapterSun : AnimationsImageType(3)
    object ChapterTextEn : AnimationsImageType(4)
    object LevelBanner : AnimationsImageType(5)
    object LevelShine : AnimationsImageType(6)
    object LevelStar : AnimationsImageType(7)
    object LevelTextEn : AnimationsImageType(8)
    object TransitionCircle : AnimationsImageType(9)
    object TransitionStripe : AnimationsImageType(10)
    object WordBanner : AnimationsImageType(11)
    object WordEffect : AnimationsImageType(12)
    object WordTextEn : AnimationsImageType(13)
}