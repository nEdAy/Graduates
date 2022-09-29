package cn.neday.graduates.view.sheets

import com.dylanc.longan.topActivity
import com.maxkeppeler.sheets.core.ButtonStyle
import com.maxkeppeler.sheets.core.Image
import com.maxkeppeler.sheets.core.SheetStyle
import com.maxkeppeler.sheets.core.TopStyle
import com.maxkeppeler.sheets.info.InfoSheet

object Info {
    fun showDialog(
        @androidx.annotation.StringRes titleRes: Int,
        @androidx.annotation.StringRes contentRes: Int? = null,
        @androidx.annotation.DrawableRes drawableRes: Int? = null,
        content: String? = null,
        cancelable: Boolean = true,
        onNegativeBlock: (() -> Unit)? = null,
        onPositiveBlock: (() -> Unit)? = null
    ) {
        InfoSheet().show(topActivity) {
            style(SheetStyle.DIALOG)
            title(titleRes)
            topStyle(TopStyle.BELOW_COVER)
            contentRes?.let { content(it) }
            content?.let { content(it) }
            drawableRes?.let { withCoverImage(Image(it)) }
            cancelableOutside(cancelable)
            negativeButtonStyle(ButtonStyle.OUTLINED)
            positiveButtonStyle(ButtonStyle.NORMAL)
            displayNegativeButton(onNegativeBlock != null || onPositiveBlock != null)
            displayPositiveButton(onPositiveBlock != null)
            onNegative {
                onNegativeBlock?.invoke()
            }
            onPositive {
                onPositiveBlock?.invoke()
            }
        }
    }
}