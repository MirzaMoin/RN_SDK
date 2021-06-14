package com.example.rnsdk;

import com.example.rnsdk.Models.MenuPermissionModel;
import com.example.rnsdk.Utility.Utility;

public class Constants {

    public static int[][] getDrawableIcons(){

        MenuPermissionModel menu = Utility.response.responsedata.menuPermission;

         int iconList[][] = {{ R.drawable.ic_baseline_menu_24},
                { R.drawable.ic_baseline_sell_24,
                        R.drawable.ic_baseline_star_rate_24,
                        R.drawable.ic_baseline_card_giftcard_24,
                        R.drawable.ic_baseline_compare_arrows_24,
                },
                {
                        R.drawable.ic_baseline_paid_24,
                        R.drawable.ic_baseline_create_24,
                        R.drawable.ic_baseline_group_add_24,
                        R.drawable.ic_baseline_upload_24,
                },
                {
                        R.drawable.ic_baseline_history_24,
                        R.drawable.ic_baseline_card_giftcard_24,
                },
                {
                        R.drawable.ic_baseline_person_24,
                        R.drawable.ic_baseline_lock_24,
                },
                {
                        R.drawable.ic_baseline_local_phone_24,
                        R.drawable.ic_baseline_location_on_24,
                        R.drawable.ic_menu_manage
                },
                {
                        R.drawable.ic_baseline_menu_24,
                }
        };

         return iconList;

    }




}
