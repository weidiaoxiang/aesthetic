/*
 * Licensed under Apache-2.0
 *
 * Designed and developed by Aidan Follestad (@afollestad)
 */
package com.afollestad.aesthetic.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.afollestad.aesthetic.Aesthetic
import com.afollestad.aesthetic.actions.ViewTextColorAction
import com.afollestad.aesthetic.utils.distinctToMainThread
import io.reactivex.disposables.Disposable

/** @author Aidan Follestad (afollestad) */
internal class AestheticSnackBarButton(
  context: Context,
  attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {

  private var subscription: Disposable? = null

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    subscription = Aesthetic.get()
        .snackbarActionTextColor()
        .distinctToMainThread()
        .subscribe(ViewTextColorAction(this))
  }

  override fun onDetachedFromWindow() {
    subscription?.dispose()
    super.onDetachedFromWindow()
  }
}
