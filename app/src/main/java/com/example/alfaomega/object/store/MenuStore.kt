package com.example.alfaomega.`object`.store

import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens

object MenuStore {

    private val titleName = arrayOf(
        "Printer",
        "Transaction",
        "Account",
    )

    private val subTitle = arrayOf(
        "Check Printer",
        "Show All Transaction Today",
        "Login Account",
    )

    private val menuIcon = intArrayOf(
        R.drawable.ic_twotone_print_24,
        R.drawable.ic_twotone_list_alt_24,
        R.drawable.ic_twotone_person_24,
    )

    private val screensMenu = arrayOf(
        Screens.Home.route,
        Screens.TransactionList.route,
        Screens.Login.route,
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
                list.add(menuItemStore)
            }
            return list
        }
}