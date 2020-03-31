package com.freewillsolutions.True.selfdcare.ui.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.freewillsolutions.True.selfdcare.R
import com.jozzee.android.core.resource.getDimen
import com.jozzee.android.core.view.warpContent

class CircleIndicator @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    /**
     * The number of indicator icons
     */
    var count: Int = 0
        set(value) {
            field = value
            updateViews()
        }

    var position: Int = 0
        set(value) {
            field = value
            updateViews()
        }

    var activeIcon: Drawable? = null
        set(value) {
            field = value
            if (count > 0) {
                updateViews()
            }
        }

    var inActiveIcon: Drawable? = null
        set(value) {
            field = value
            if (count > 0) {
                updateViews()
            }
        }

    var isFillProcess: Boolean = false

    init {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator)
            if (a.hasValue(R.styleable.CircleIndicator_activeIcon)) {
                activeIcon = a.getDrawable(R.styleable.CircleIndicator_activeIcon)
            }
            if (a.hasValue(R.styleable.CircleIndicator_inActiveIcon)) {
                inActiveIcon = a.getDrawable(R.styleable.CircleIndicator_inActiveIcon)
            }

            count = a.getInt(R.styleable.CircleIndicator_count, 0)

            if (a.hasValue(R.styleable.CircleIndicator_position)) {
                position = a.getInt(R.styleable.CircleIndicator_position, 0)
            }
            a.recycle()
        }
    }

    private fun updateViews() {
        removeAllViews()
        val dimen8Dp = getDimen(R.dimen.space_8dp)
        setPadding(0, dimen8Dp, 0, dimen8Dp)
        for (i in 0 until count) {
            val icon = ImageView(context).apply {
                id = View.generateViewId()
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER

                val icon = if (isFillProcess) {
                    if (i < position) activeIcon else inActiveIcon
                } else {
                    if (i == position) activeIcon else inActiveIcon
                }

                setImageDrawable(icon)
                layoutParams = LayoutParams(warpContent(), warpContent()).apply {
                    setMargins(dimen8Dp, 0, dimen8Dp, 0)
                    addRule(CENTER_VERTICAL)
                    if (i > 0) {
                        addRule(END_OF, this@CircleIndicator.getChildAt(i - 1).id)
                    }
                }
            }
            addView(icon)
        }
    }
}