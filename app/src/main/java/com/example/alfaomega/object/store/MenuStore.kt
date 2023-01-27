package com.example.alfaomega.`object`.store

import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens

object MenuStore {

    private val titleName = intArrayOf(
        0,
        1,
        2,
        3,
    )

    private val subTitle = intArrayOf(
        0,
        1,
        2,
        3,
    )

    private val menuIcon = intArrayOf(
        R.drawable.ic_twotone_print_24,
        R.drawable.ic_twotone_list_alt_24,
        R.drawable.ic_twotone_report_24,
        R.drawable.ic_twotone_person_24,
    )

    private val screensMenu = arrayOf(
        Screens.Bluetooth.route,
        Screens.TransactionList.route,
        Screens.MachineOwner.route,
        Screens.Login.route,
    )

    private val typeMenu = booleanArrayOf(
        false,
        false,
        false,
        true
    )

    val listData: ArrayList<MenuStoreModel>
        get() {
            val list = arrayListOf<MenuStoreModel>()
            for (position in titleName.indices) {
                val menuItemStore = MenuStoreModel()
                menuItemStore.titleName = titleName[position]
                menuItemStore.subTitle = subTitle[position]
                menuItemStore.menuIcon = menuIcon[position]
                menuItemStore.screensMenu = screensMenu[position]
                menuItemStore.typeMenu = typeMenu[position]
                list.add(menuItemStore)
            }
            return list
        }
}