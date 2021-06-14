package com.example.rnsdk.Utility;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.example.rnsdk.Activities.CashbackActivity;
import com.example.rnsdk.Activities.ChangePasswordActivity;
import com.example.rnsdk.Activities.ContactUsActivity;
import com.example.rnsdk.Activities.HomeActivity;
import com.example.rnsdk.Activities.LeaderboardActivity;
import com.example.rnsdk.Activities.LocationActivity;
import com.example.rnsdk.Activities.NotificationActivity;
import com.example.rnsdk.Activities.OfferActivity;
import com.example.rnsdk.Activities.ProfileActivity;
import com.example.rnsdk.Activities.ReferFriendActivity;
import com.example.rnsdk.Activities.RewardEntryGoalActivity;
import com.example.rnsdk.Activities.TakeSurveyActivity;
import com.example.rnsdk.Activities.TransactionHistoryActivity;
import com.example.rnsdk.Activities.TransferPointActivity;
import com.example.rnsdk.Activities.UploadReceiptActivity;
import com.example.rnsdk.Activities.WaysToEarnActivity;
import com.example.rnsdk.Models.AppColorModel;
import com.example.rnsdk.Models.ResponseModel;
import com.example.rnsdk.R;

public class Utility {

  public  static ResponseModel response = new ResponseModel();

  public static int  getColor(String color){


    String colorCode = color.substring(1,color.length());

    String alpha  = colorCode.substring(colorCode.length()-2);
    String finalColor = "#"+alpha +  colorCode.substring(0,colorCode.length()-2);

   return  Color.parseColor(finalColor);


  }

  public static void openNewActivity(Context context, String menuInternalLinkUrl, int id, String rpID)
  {
    switch (menuInternalLinkUrl)
    {
      case "notificaiton":
        context.startActivity(new Intent(context, NotificationActivity.class));
        break;
      case "refereFriend":
      case "Refer Friend":
        context.startActivity(new Intent(context, ReferFriendActivity.class));
        break;
      case "offer":
      case "Offers":
        context.startActivity(new Intent(context, OfferActivity.class));
        break;
      case "leaderboard":
      case "Leaderboard":
        context.startActivity(new Intent(context, LeaderboardActivity.class));
        break;
      case "wayToEarn":
      case "Ways To Earn":
        context.startActivity(new Intent(context, WaysToEarnActivity.class));
        break;
      case "profileScreen":
      case "Update Profile":
        context.startActivity(new Intent(context, ProfileActivity.class));
        break;
      case "uploadReceipt":
      case "Upload Receipt":
        context.startActivity(new Intent(context, UploadReceiptActivity.class));
        break;
      case "locations":
        context.startActivity(new Intent(context, LocationActivity.class));
        break;
      case "redeemCashback":
      case "Redeem Cashback":
        context.startActivity(new Intent(context, CashbackActivity.class));
        break;
      case "homeScreen":
      case "HOME":
        context.startActivity(new Intent(context, HomeActivity.class));
        break;
      case "rpg":
      case "Reward Entry Goal":
        context.startActivity(new Intent(context, RewardEntryGoalActivity.class));
        break;
      case "Transfer Points":
        context.startActivity(new Intent(context, TransferPointActivity.class));
        break;
      case "takeSurvey":
      case "Take Survey":
        context.startActivity(new Intent(context, TakeSurveyActivity.class));
        break;
      case "Transaction History":
        context.startActivity(new Intent(context, TransactionHistoryActivity.class));
        break;
      case "Change Password":
        context.startActivity(new Intent(context, ChangePasswordActivity.class));
        break;
      case "Contact Us":
        context.startActivity(new Intent(context, ContactUsActivity.class));
        break;
      case "Location":
        context.startActivity(new Intent(context, LocationActivity.class));
        break;


    }
  }

  public static int getIcon(String icon){
    switch (icon)
    {
      case "clock":
        return R.string.fa_clock;
      case "angle-double-right":
        return R.string.fa_angle_double_right_solid;
      case "address-book":
        return R.string.fa_address_book;
      case "address-card":
        return R.string.fa_address_card;
      case "arrow-down":
        return R.string.fa_arrow_down_solid;
      case "arrow-left":
        return R.string.fa_arrow_left_solid;
      case "arrow-right":
        return R.string.fa_arrow_right_solid;
      case "arrow-up":
        return R.string.fa_arrow_up_solid;
      case "arrows-alt":
        return R.string.fa_arrows_alt_solid;
      case "arrows-alt-h":
        return R.string.fa_arrows_alt_h_solid;
      case "arrows-alt-v":
        return R.string.fa_arrows_alt_v_solid;
      case "assistive-listening-systems":
        return R.string.fa_assistive_listening_systems_solid;
      case "asterisk":
        return R.string.fa_asterisk_solid;
      case "at":
        return R.string.fa_at_solid;
      case "atlas":
        return R.string.fa_atlas_solid;
      case "atom":
        return R.string.fa_atom_solid;
      case "audio-description":
        return R.string.fa_audio_description_solid;
      case "award":
        return R.string.fa_award_solid;
      case "baby":
        return R.string.fa_baby_solid;
      case "baby-carriage":
        return R.string.fa_baby_carriage_solid;
      case "backspace":
        return R.string.fa_backspace_solid;
      case "backward":
        return R.string.fa_backward_solid;
      case "bacon":
        return R.string.fa_bacon_solid;
      case "balance-scale":
        return R.string.fa_balance_scale_solid;
      case "battery-half":
        return R.string.fa_battery_half_solid;
      case "battery-quarter":
        return R.string.fa_battery_quarter_solid;
      case "battery-three-quarters":
        return R.string.fa_battery_three_quarters_solid;
      case "binoculars":
        return R.string.fa_binoculars_solid;
      case "biohazard":
        return R.string.fa_biohazard_solid;
      case "bus-alt":
        return R.string.fa_bus_alt_solid;
      case "business-time":
        return R.string.fa_business_time_solid;
      case "calculator":
        return R.string.fa_calculator_solid;
      case "calendar":
        return R.string.fa_calendar;
      case "calendar-alt":
        return R.string.fa_calendar_alt;
      case "calendar-check":
        return R.string.fa_calendar_check;
      case "calendar-day":
        return R.string.fa_calendar_day_solid;
      case "calendar-minus":
        return R.string.fa_calendar_minus;
      case "calendar-plus":
        return R.string.fa_calendar_plus;
      case "calendar-times":
        return R.string.fa_calendar_times;
      case "calendar-week":
        return R.string.fa_calendar_week_solid;
      case "camera":
        return R.string.fa_camera_solid;
      case "camera-retro":
        return R.string.fa_camera_retro_solid;
      case "campground":
        return R.string.fa_campground_solid;
      case "chevron-circle-down":
        return R.string.fa_chevron_circle_down_solid;
      case "chevron-circle-left":
        return R.string.fa_chevron_circle_left_solid;
      case "chevron-circle-right":
        return R.string.fa_chevron_circle_right_solid;
      case "chevron-circle-up":
        return R.string.fa_chevron_circle_up_solid;
      case "chevron-down":
        return R.string.fa_chevron_down_solid;
      case "chevron-left":
        return R.string.fa_chevron_left_solid;
      case "chevron-right":
        return R.string.fa_chevron_right_solid;
      case "chevron-up":
        return R.string.fa_chevron_up_solid;
      case "child":
        return R.string.fa_child_solid;
      case "church":
        return R.string.fa_church_solid;
      case "circle":
        return R.string.fa_circle;
      case "circle-notch":
        return R.string.fa_circle_notch_solid;
      case "city":
        return R.string.fa_city_solid;
      case "clinic-medical":
        return R.string.fa_clinic_medical_solid;
      case "clipboard":
        return R.string.fa_clipboard;
      case "clipboard-check":
        return R.string.fa_clipboard_check_solid;
      case "clipboard-list":
        return R.string.fa_clipboard_list_solid;
      case "clone":
        return R.string.fa_clone;
      case "closed-captioning":
        return R.string.fa_closed_captioning;
      case "cloud":
        return R.string.fa_cloud_solid;
      case "cloud-download-alt":
        return R.string.fa_cloud_download_alt_solid;
      case "cloud-meatball":
        return R.string.fa_cloud_meatball_solid;
      case "cloud-moon":
        return R.string.fa_cloud_moon_solid;
      case "cloud-moon-rain":
        return R.string.fa_cloud_moon_rain_solid;
      case "cloud-rain":
        return R.string.fa_cloud_rain_solid;
      case "cloud-showers-heavy":
        return R.string.fa_cloud_showers_heavy_solid;
      case "cloud-sun":
        return R.string.fa_cloud_sun_solid;
      case "cloud-sun-rain":
        return R.string.fa_cloud_sun_rain_solid;
      case "cloud-upload-alt":
        return R.string.fa_cloud_upload_alt_solid;
      case "cocktail":
        return R.string.fa_cocktail_solid;
      case "code":
        return R.string.fa_code_solid;
      case "code-branch":
        return R.string.fa_code_branch_solid;
      case "coffee":
        return R.string.fa_coffee_solid;
      case "cog":
        return R.string.fa_cog_solid;
      case "cogs":
        return R.string.fa_cogs_solid;
      case "cookie":
        return R.string.fa_cookie_solid;
      case "cookie-bite":
        return R.string.fa_cookie_bite_solid;
      case "copy":
        return R.string.fa_copy;
      case "copyright":
        return R.string.fa_copyright;
      case "couch":
        return R.string.fa_couch_solid;
      case "credit-card":
        return R.string.fa_credit_card;
      case "crop":
        return R.string.fa_crop_solid;
      case "crop-alt":
        return R.string.fa_crop_alt_solid;
      case "cross":
        return R.string.fa_cross_solid;
      case "desktop":
        return R.string.fa_desktop_solid;
      case "dharmachakra":
      return R.string.fa_dharmachakra_solid;
      case "diagnoses":
        return R.string.fa_diagnoses_solid;
      case "dog":
        return R.string.fa_dog_solid;
      case "fast-backward":
        return R.string.fa_fast_backward_solid;
      case "fast-forward":
        return R.string.fa_fast_forward_solid;
      case "fax":
        return R.string.fa_fax_solid;
      case "filter":
        return R.string.fa_filter_solid;
      case "fingerprint":
        return R.string.fa_fingerprint_solid;
      case "fire":
        return R.string.fa_fire_solid;
      case "fire-alt":
        return R.string.fa_fire_alt_solid;
      case "fire-extinguisher":
        return R.string.fa_fire_extinguisher_solid;
      case "glasses":
        return R.string.fa_glasses_solid;
      case "globe":
        return R.string.fa_globe_solid;
      case "grin-alt":
        return R.string.fa_grin_alt;
      case "grin":
        return R.string.fa_grin;
      case "grimace":
        return R.string.fa_grimace;
      case "greater-than-equal":
        return R.string.fa_greater_than_equal_solid;
      case "globe-africa":
        return R.string.fa_globe_africa_solid;
      case "globe-americas":
        return R.string.fa_globe_americas_solid;
      case "globe-asia":
        return R.string.fa_globe_asia_solid;
      case "globe-europe":
        return R.string.fa_globe_europe_solid;
      case "golf-ball":
        return R.string.fa_golf_ball_solid;
      case "gopuram":
        return R.string.fa_gopuram_solid;
      case "graduation-cap":
        return R.string.fa_graduation_cap_solid;
      case "greater-than":
        return R.string.fa_greater_than_solid;
      case "hdd":
        return R.string.fa_hdd;
      case "heading":
        return R.string.fa_heading_solid;
      case "headphones":
        return R.string.fa_headphones_solid;
      case "headphones-alt":
        return R.string.fa_headphones_alt_solid;
      case "kaaba" :
        return R.string.fa_kaaba_solid;
      case "key" :
        return R.string.fa_key_solid;
      case "long-arrow-alt-down" :
        return R.string.fa_long_arrow_alt_down_solid;
      case "long-arrow-alt-left" :
        return R.string.fa_long_arrow_alt_left_solid;
      case "long-arrow-alt-right" :
        return R.string.fa_long_arrow_alt_right_solid;
      case "long-arrow-alt-up" :
        return R.string.fa_long_arrow_alt_up_solid;
      case "low-vision":
        return R.string.fa_low_vision_solid;
      case "luggage-cart":
        return R.string.fa_luggage_cart_solid;
      case "magnet" :
        return R.string.fa_magnet_solid;
      case "mail-bulk" :
        return R.string.fa_mail_bulk_solid;
      case "male" :
        return R.string.fa_male_solid;
      case "map":
        return R.string.fa_map;
      case "map-marked":
        return R.string.fa_map_marked_solid;
      case "map-marked-alt":
        return R.string.fa_map_marked_alt_solid;
      case "map-marker":
        return R.string.fa_map_marker_solid;
      case "map-marker-alt" :
        return R.string.fa_map_marker_alt_solid;
      case "map-pin":
        return R.string.fa_map_pin_solid;
      case "map-signs":
        return R.string.fa_map_signs_solid;
      case "marker":
        return R.string.fa_marker_solid;
      case "mars":
        return R.string.fa_mars_solid;
      case "mars-double":
        return R.string.fa_mars_double_solid;
      case "mars-stroke" :
        return R.string.fa_mars_stroke_solid;
      case "mars-stroke-h":
        return R.string.fa_mars_stroke_h_solid;
      case "pen":
        return R.string.fa_pen_solid;
      case "pen-alt":
        return R.string.fa_pen_alt_solid;
      case "pen-fancy":
        return R.string.fa_pen_fancy_solid;
      case "plus-circle" :
        return R.string.fa_plus_circle_solid;
      case "plus-square":
        return R.string.fa_plus_square;
      case "podcast" :
        return R.string.fa_podcast_solid;
      case "poll" :
        return R.string.fa_poll_solid;
      case "receipt":
        return R.string.fa_receipt_solid;

      case "recycle" :
        return R.string.fa_recycle_solid;
      case "redo" :
        return R.string.fa_redo_solid;
      case "redo-alt" :
        return R.string.fa_redo_alt_solid;
      case "registered":
        return R.string.fa_registered;
      case "reply":
        return R.string.fa_reply_solid;
      case "reply-all" :
        return R.string.fa_reply_all_solid;
      case "stamp":
        return R.string.fa_stamp_solid;
      case "star":
        return R.string.fa_star;
      case "star-and-crescent":
        return R.string.fa_star_and_crescent_solid;
      case "star-half":
        return R.string.fa_star_half;
      case "star-half-alt":
        return R.string.fa_star_half_alt_solid;
      case "star-of-david":
        return R.string.fa_star_of_david_solid;
      case  "sticky-note":
        return R.string.fa_sticky_note;
      case "stop":
        return R.string.fa_stop_solid;
      case "stop-circle" :
        return R.string.fa_stop_circle;
      case "stopwatch":
        return R.string.fa_stopwatch_solid;
      case "table":
        return R.string.fa_table_solid;
      case "table-tennis":
        return R.string.fa_table_tennis_solid;
      case "tablet":
        return R.string.fa_table_solid;
      case  "tablet-alt":
        return R.string.fa_tablet_alt_solid;
      case "tablets":
        return R.string.fa_tablets_solid;
      case  "taxi":
        return R.string.fa_taxi_solid;
      case "teeth" :
        return R.string.fa_teeth_solid;

      case "teeth-open":
        return R.string.fa_teeth_open_solid;
      case "temperature-high":
        return R.string.fa_temperature_high_solid;
      case "temperature-low" :
        return R.string.fa_temperature_low_solid;
      case  "tenge":
        return R.string.fa_tenge_solid;
      case "terminal":
        return R.string.fa_terminal_solid;
      case "text-height":
        return R.string.fa_text_height_solid;
      case "text-width":
        return R.string.fa_text_width_solid;
      case "th":
        return R.string.fa_th_solid;
      case "th-large" :
        return R.string.fa_th_large_solid;
      case "th-list":
        return R.string.fa_th_list_solid;
      case "youtube" :
        return R.string.fa_youtube;
      case "youtube-square":
        return R.string.fa_clock;

      case "zhihu":
        return R.string.fa_zhihu;

      default:
        return R.string.fa_clock;
    }
  }

}
