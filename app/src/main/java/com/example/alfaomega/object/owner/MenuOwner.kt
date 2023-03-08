package com.example.alfaomega.`object`.owner

import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens

object MenuOwner {

    private val titleName = intArrayOf(
        0,
        1,
        2,
    )

    private val subTitle = intArrayOf(
        0,
        1,
        2,
    )

    private val menuIcon = intArrayOf(
        R.drawable.ic_twotone_print_24,
        R.drawable.twotone_qr_code_2_24,
        R.drawable.ic_twotone_person_24,
    )

    private val screensMenu = arrayOf(
        Screens.RulesOwner.route,
        Screens.QrOwner.route,
        Screens.UserOwner.route,
    )

    private val typeMenu = booleanArrayOf(
        false,
        false,
        false,
    )

    val listData: ArrayList<MenuOwnerModel>
        get() {
            val list = arrayListOf<MenuOwnerModel>()
            for (position in titleName.indices) {
                val menuItemOwner = MenuOwnerModel()
                menuItemOwner.titleName = titleName[position]
                menuItemOwner.subTitle = subTitle[position]
                menuItemOwner.menuIcon = menuIcon[position]
                menuItemOwner.screensMenu = screensMenu[position]
//                menuItemOwner.typeMenu = typeMenu[position]
                list.add(menuItemOwner)
            }
            return list
        }
}