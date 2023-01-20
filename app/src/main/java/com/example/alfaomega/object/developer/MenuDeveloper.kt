package com.example.alfaomega.`object`.developer

import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens

object MenuDeveloper {

    private val titleName = intArrayOf(
        0,
//        1,
//        2
    )

    private val subTitle = intArrayOf(
        0,
//        1,
//        2
    )

    private val menuIcon = intArrayOf(
//        R.drawable.ic_twotone_local_laundry_service_24,
        R.drawable.ic_twotone_person_24,
//        R.drawable.ic_twotone_storefront_24,
    )

    private val screensMenu = arrayOf(
        Screens.OwnerListDeveloper.route,
//        Screens.OwnerListDeveloper.route,
//        Screens.OwnerListDeveloper.route,
    )

    private val typeMenu = booleanArrayOf(
        false,
//        false,
//        false,
    )

    val listData: ArrayList<MenuDeveloperModel>
        get() {
            val list = arrayListOf<MenuDeveloperModel>()
            for (position in titleName.indices) {
                val menuItemDev = MenuDeveloperModel()
                menuItemDev.titleName = titleName[position]
                menuItemDev.subTitle = subTitle[position]
                menuItemDev.menuIcon = menuIcon[position]
                menuItemDev.screensMenu = screensMenu[position]
                menuItemDev.typeMenu = typeMenu[position]
                list.add(menuItemDev)
            }
            return list
        }
}