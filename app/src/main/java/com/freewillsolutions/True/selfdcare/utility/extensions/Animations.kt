package com.freewillsolutions.True.selfdcare.utility.extensions

import com.freewillsolutions.True.selfdcare.R
import com.jozzee.android.core.utility.FragmentAnimations

fun fadeIn() = FragmentAnimations(enter = R.anim.fade_in, exit = R.anim.fade_out,
        popEnter = R.anim.fade_in, popExit = R.anim.fade_out)

fun fadeOut() = FragmentAnimations(enter = R.anim.fade_out, exit = R.anim.fade_in,
        popEnter = R.anim.fade_out, popExit = R.anim.fade_in)

fun slideRightToLeft() = FragmentAnimations(enter = R.anim.slide_in_right_to_left, exit = R.anim.slide_out_left,
        popEnter = R.anim.slide_in_left_to_right, popExit = R.anim.slide_out_right)