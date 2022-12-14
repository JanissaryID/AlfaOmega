package com.example.alfaomega.`object`.owner

import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens

object MenuOwner {

    private val titleName = intArrayOf(
        0,
        1,
        2,
        3,
        4
    )

    private val subTitle = intArrayOf(
        0,
        1,
        2,
        3,
        4
    )

    private val menuIcon = intArrayOf(
        R.drawable.ic_twotone_print_24,
        R.drawable.ic_twotone_menu_book_24,
        R.drawable.ic_twotone_list_alt_24,
        R.drawable.ic_twotone_person_24,
        R.drawable.ic_twotone_report_24,
    )

    private val screensMenu = arrayOf(
        Screens.RulesOwner.route,
        Screens.MenuOwner.route,
        Screens.TransactionOwner.route,
        Screens.UserOwner.route,
        Screens.ReportMachine.route,
    )

    private val typeMenu = booleanArrayOf(
        false,
        false,
        false,
        false,
        false
//        true
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