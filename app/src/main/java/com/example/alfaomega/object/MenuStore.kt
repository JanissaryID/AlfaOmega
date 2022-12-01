package com.example.alfaomega.`object`

import com.example.alfaomega.R

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

    val listData: ArrayList<MenuStoreModel>
        get() {
            val list = arrayListOf<MenuStoreModel>()
            for (position in titleName.indices) {
                val menuItemStore = MenuStoreModel()
                menuItemStore.titleName = titleName[position]
                menuItemStore.subTitle = subTitle[position]
                menuItemStore.menuIcon = menuIcon[position]
                list.add(menuItemStore)
            }
            return list
        }
}