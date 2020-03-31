package com.freewillsolutions.True.selfdcare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.feature.maps.MapsMockup
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen
import com.freewillsolutions.True.selfdcare.utility.extensions.fadeIn
import com.freewillsolutions.True.selfdcare.utility.extensions.initialToolbar
import com.jozzee.android.core.fragment.addFragment
import com.jozzee.android.core.fragment.onBackPressed
import com.jozzee.android.core.resource.getColor
import com.jozzee.android.core.resource.getDrawable
import com.jozzee.android.core.view.showToast
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.screen_covid_quarantine.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class CovidQuarantineScreen : BaseScreen() {
    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.screen_covid_quarantine, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialToolbar(toolbar,"มีความเสี่ยงติดเชื้อ โควิด-19",getDrawable(R.drawable.ic_asset_back_white))
        toolbar.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        subscribeUi()
    }

    private fun subscribeUi() {
        setupSaveButton()
        setupCalendarCallback()
    }

    private fun setupSaveButton() {
        btnAcceptGetLocation.setOnCheckedChangeListener { buttonView, isChecked ->
            when (isChecked) {
                true -> {
                    btnSafeLocation.background = getDrawable(R.drawable.btn_blue_gradient)
                    setupButtonSave(true)
                }
                false -> {
                    btnSafeLocation.background = getDrawable(R.drawable.btn_gray_gradient)
                    setupButtonSave(false)
                }
            }
        }
    }

    private fun setupButtonSave(flag: Boolean) {

        btnSafeLocation.setOnClickListener {
            when (flag) {
                true -> {
                    addFragment(MapsMockup(), fadeIn())
                }
                false -> {
                    showToast("กรุณายอมรับการแชร์ตำแหน่ง")
                }
            }
        }
    }
    private fun setupCalendarCallback(){
        calendarQuarantine.tileSize = -5
        calendarQuarantine.setDateSelected(CalendarDay.today(),true)
        calendarQuarantine.selectionColor = getColor(R.color.blue)
        calendarQuarantine.selectionMode = MaterialCalendarView.SELECTION_MODE_RANGE
    }
}