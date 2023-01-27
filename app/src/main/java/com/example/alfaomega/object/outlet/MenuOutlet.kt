package com.example.alfaomega.`object`.outlet

import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens

object MenuOutlet {

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
        R.drawable.ic_twotone_menu_book_24,
        R.drawable.ic_twotone_list_alt_24,
        R.drawable.ic_twotone_report_24,
    )

    private val screensMenu = arrayOf(
        Screens.MenuOwner.route,
        Screens.TransactionOwner.route,
        Screens.MachineOwner.route,
    )

    private val typeMenu = booleanArrayOf(
        false,
        false,
        false,
    )

    val listData: ArrayList<MenuOutletModel>
        get() {
            val list = arrayListOf<MenuOutletModel>()
            for (position in titleName.indices) {
                val menuItemOwner = MenuOutletModel()
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