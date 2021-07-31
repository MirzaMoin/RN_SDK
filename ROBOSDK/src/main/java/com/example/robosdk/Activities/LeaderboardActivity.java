package com.example.robosdk.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.robosdk.API.GetAPIData;
import com.example.robosdk.API.RetrofitClientInstance;
import com.example.robosdk.Adapter.DialogListAdapter;
import com.example.robosdk.Adapter.FooterAdapter;
import com.example.robosdk.Adapter.LeaderboardAdapter;
import com.example.robosdk.Models.AppColorModel;
import com.example.robosdk.Models.HomeScreenModel;
import com.example.robosdk.Models.LBFilterModel;
import com.example.robosdk.Models.LBLeaderBoardReportModel;
import com.example.robosdk.Models.ResponseModel;
import com.example.robosdk.Models.ResponsedataModel;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgFilter,
            imgBackLeaderboard,
            winnerImage,
            winnerImageTwo,
            winnerImageThree,
            imgAwardOne,
            imgAwardTwo,
            imgAwardThree,
            imageLeaderboard,
            imageLogoLeaderboard;
    RecyclerView rvLeader, rvFooterLeaderboard;
    public static AlertDialog monthDialog;
    public static int month, year;
    public static TextView textDateBottomSheetLeader;
    LeaderboardAdapter adapter;
    LinearLayout linearNoWinnerFound;

    RelativeLayout relLoadingLeaderboard;

    TextView textSharesToQualify,
            textReferralToQualify,
            textName,
            textPoint,
            textNameTwo,
            textPointTwo,
            textNameThree,
            textPointThree;

    RelativeLayout relOne, relTwo, relThree;

    TableLayout tableLayoutLeaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.myLibTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        init();
        getData();

    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utility.getColor(Utility.response.responsedata.appColor.getPhoneNotificationBar()));
        }
        if (Utility.response.responsedata.appColor.getPhoneNotificationBarTextColor().equals("Black")) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        imgFilter = findViewById(R.id.imgCompareLeader);
        rvLeader = findViewById(R.id.rvLeaderboard);
        rvFooterLeaderboard = findViewById(R.id.rvFooterLeaderboard);
        imgBackLeaderboard = findViewById(R.id.imgBackLeaderboard);
        textSharesToQualify = findViewById(R.id.textSharesToQualify);
        textReferralToQualify = findViewById(R.id.textReferralToQualify);
        imageLeaderboard = findViewById(R.id.imageLeaderboard);
        imageLogoLeaderboard = findViewById(R.id.imageLogoLeaderboard);
        relLoadingLeaderboard = findViewById(R.id.relLoadingLeaderboard);
        tableLayoutLeaderboard = findViewById(R.id.tableLayoutLeaderboard);
        linearNoWinnerFound = findViewById(R.id.linearNoWinnerFound);

        tableLayoutLeaderboard.setBackgroundColor(Utility.getColor(Utility.response.responsedata.appColor.getHeaderBarColor()));

        winnerImage = findViewById(R.id.winnerImage);
        imgAwardOne = findViewById(R.id.imgAward);
        relOne = findViewById(R.id.relLBOne);
        textName = findViewById(R.id.textName);
        textPoint = findViewById(R.id.textPoint);

        winnerImageTwo = findViewById(R.id.winnerImageTwo);
        imgAwardTwo = findViewById(R.id.imgAwardTwo);
        relTwo = findViewById(R.id.relLBTwo);
        textNameTwo = findViewById(R.id.textNameTwo);
        textPointTwo = findViewById(R.id.textPointTwo);

        winnerImageThree = findViewById(R.id.winnerImageThree);
        imgAwardThree = findViewById(R.id.imgAwardThree);
        relThree = findViewById(R.id.relLBThree);
        textNameThree = findViewById(R.id.textNameThree);
        textPointThree = findViewById(R.id.textPointThree);

        imgFilter.setOnClickListener(this);
        imgBackLeaderboard.setOnClickListener(this);
        Utility.setFooter(LeaderboardActivity.this,rvFooterLeaderboard,"leaderboard");

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgCompareLeader) {

            ResponsedataModel responsedata = Utility.response.responsedata;
            if(responsedata.leaderBoardReport != null){
                showFilterDialog();
            }
            else
            {
                Toast.makeText(this, "No Winner Found", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.imgBackLeaderboard) {
            super.onBackPressed();
        }
    }

    void showMonthDialog() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_month_dialog, null);
        builder.setView(customLayout);
        // add a button
        // create and show the alert dialog
        monthDialog = builder.create();
        monthDialog.show();
        RecyclerView rvList = monthDialog.findViewById(R.id.rvMonthList);
        DialogListAdapter adapter = new DialogListAdapter(this, "Leaderboard", -1);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
    }

    private void showFilterDialog() {

        // create an alert builder
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.content_bottomsheet_leaderboard, null);
        dialog.setContentView(customLayout);
        // add a button
        // create and show the alert dialog
        dialog.show();
        RelativeLayout relMonth = dialog.findViewById(R.id.relMonth);
        textDateBottomSheetLeader = dialog.findViewById(R.id.textDateBottomSheetLeader);
        TextView textApply = dialog.findViewById(R.id.textApply);
        TextView textClear = dialog.findViewById(R.id.textClear);
        final EditText etFilterLeader = dialog.findViewById(R.id.etFilterLeader);


        textApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getFilterData(etFilterLeader.getText().toString().trim());
            }
        });

        textClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etFilterLeader.setText("");
            }
        });


        LBFilterModel filter = Utility.response.responsedata.filters.get(0);

        textDateBottomSheetLeader.setText(filter.getDisplay());
        month = filter.getMonth();
        year = filter.getYear();


        relMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonthDialog();
            }
        });


    }


    private void getData() {

        relLoadingLeaderboard.setVisibility(View.VISIBLE);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.loadingImages.get(0).imageUrl).into(imageLeaderboard);
        Glide.with(this).load(Utility.response.responsedata.appIntakeImages.companyLogo).into(imageLogoLeaderboard);



        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId + ", Contact ID: " + Utility.response.responsedata.contactData.contactID);
        Call<ResponseModel> call =
                service.getLeaderBoardScreenData(Utility.response.responsedata.appDetails.rewardProgramId
                );
        call.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                relLoadingLeaderboard.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getStatusCode() == 1) {
                        ResponsedataModel responsedata = Utility.response.responsedata;
                        ResponseModel responseModel = response.body();
                        responsedata.filters = responseModel.responsedata.filters;
                        responsedata.qualificationCriteria = responseModel.responsedata.qualificationCriteria;
                        responsedata.leaderBoardReport = responseModel.responsedata.leaderBoardReport;

                        Log.e("Test", "onResponse: " + responsedata.qualificationCriteria.noOfWinners);

                        setLayout();


                    } else {
                        Utility.showAlertDialog(LeaderboardActivity.this,"Oops...", "Something went wrong, contact support");
                    }
                } else {
                    Utility.showAlertDialog(LeaderboardActivity.this,"Oops...", "Something went wrong, contact support");

                    Log.e("Test Error: ", "" + response.message());


                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                relLoadingLeaderboard.setVisibility(View.GONE);
                Utility.showAlertDialog(LeaderboardActivity.this,"Oops...", "Something went wrong, contact support");

                Log.e("Test Error: ", "" + t.getMessage());


            }
        });
    }

    private void getFilterData(final String searchData) {
        Utility.showLoader(LeaderboardActivity.this);


        GetAPIData service = RetrofitClientInstance.getRetrofitInstance().create(GetAPIData.class);
        Log.e("Request", "RP ID: " + Utility.response.responsedata.appDetails.rewardProgramId + ", Contact ID: " + Utility.response.responsedata.contactData.contactID);
        Call<JsonObject> call =
                service.getLeaderBoardReport(Utility.response.responsedata.appDetails.rewardProgramId,
                        month,
                        year
                );
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Utility.dialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {

                    Log.e("Test", "Array Size: " + String.valueOf(response.body().get("responsedata").getAsJsonArray().size()));


                    if (response.body().get("statusCode").getAsInt() == 1) {
                        ResponsedataModel responsedata = Utility.response.responsedata;
                        if(responsedata.leaderBoardReport != null)
                        {
                            responsedata.leaderBoardReport.clear();
                        }

                        for (JsonElement model : response.body().get("responsedata").getAsJsonArray()) {
                            int rank = model.getAsJsonObject().get("rank").getAsInt();
                            LBLeaderBoardReportModel modelData = new LBLeaderBoardReportModel(
                                    model.getAsJsonObject().get("rank").getAsInt(),
                                    model.getAsJsonObject().get("totalPoints").getAsInt(),
                                    model.getAsJsonObject().get("contactID").getAsString(),
                                    model.getAsJsonObject().get("profilePitcure").getAsString(),
                                    model.getAsJsonObject().get("fullName").getAsString()
                            );
                            if(rank <= 3)
                            {
                                if(responsedata.leaderBoardReport != null)
                                {
                                    responsedata.leaderBoardReport.add(modelData);

                                }
                            }
                            else if (!searchData.isEmpty()) {
                                String name = model.getAsJsonObject().get("fullName").getAsString().toLowerCase();
                                if (name.contains(searchData.toLowerCase())) {
                                    if(responsedata.leaderBoardReport != null)
                                    {
                                        responsedata.leaderBoardReport.add(modelData);

                                    }
                                }
                            } else {
                                if(responsedata.leaderBoardReport != null)
                                {
                                    responsedata.leaderBoardReport.add(modelData);

                                }
                            }
                        }
                        if(adapter != null)
                        {
                        adapter.notifyDataSetChanged();

                        }


                    } else {
                        Utility.showAlertDialog(LeaderboardActivity.this,"Oops...", response.message());
                    }
                } else {

                    Utility.showAlertDialog(LeaderboardActivity.this,"Oops...", "Something went wrong");

                    Log.e("Test Error: ", "" + response.message());
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Utility.dialog.dismiss();
                Utility.showAlertDialog(LeaderboardActivity.this,"Oops...", "Something went wrong");
                Log.e("Test Error: ", "" + t.getMessage());
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setLayout() {
        ResponsedataModel responsedata = Utility.response.responsedata;
if(responsedata.leaderBoardReport != null)
{
    if (responsedata.leaderBoardReport.size() > 3) {
        adapter = new LeaderboardAdapter(this, responsedata.leaderBoardReport);
        rvLeader.setHasFixedSize(true);
        rvLeader.setLayoutManager(new LinearLayoutManager(this));
        rvLeader.setAdapter(adapter);
    }
    textSharesToQualify.setText("Required " + String.valueOf(responsedata.qualificationCriteria.getSharesToQualify() == 0 ? 2 : responsedata.qualificationCriteria.getSharesToQualify()));
    textReferralToQualify.setText("Required " + String.valueOf(responsedata.qualificationCriteria.getReferralToQualify() == 0 ? 2 :responsedata.qualificationCriteria.getReferralToQualify()));

    if (responsedata.leaderBoardReport.size() == 0) {
        relOne.setVisibility(View.GONE);
        relTwo.setVisibility(View.GONE);
        relThree.setVisibility(View.GONE);
    }
    else if (responsedata.leaderBoardReport.size() == 1) {
        textName.setText(responsedata.leaderBoardReport.get(0).getFullName());
        textPoint.setText("Point: " + String.valueOf(responsedata.leaderBoardReport.get(0).getTotalPoints()));
        relTwo.setVisibility(View.GONE);
        relThree.setVisibility(View.GONE);
        relOne.setVisibility(View.VISIBLE);

        Glide.with(this).load(responsedata.leaderBoardReport.get(0).getProfilePitcure()).into(winnerImage);

    }
    else if (responsedata.leaderBoardReport.size() == 2) {
        textNameThree.setText(responsedata.leaderBoardReport.get(0).getFullName());
        textPointThree.setText("Point: " + String.valueOf(responsedata.leaderBoardReport.get(0).getTotalPoints()));
        Glide.with(this).load(responsedata.leaderBoardReport.get(0).getProfilePitcure()).into(winnerImageThree);
        Glide.with(this).load(R.drawable.first_winner).into(imgAwardThree);

        RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams)relThree.getLayoutParams();
        relativeParams.rightMargin=200;
        relativeParams.topMargin=50;
        relThree.setLayoutParams(relativeParams);

        textNameTwo.setText(responsedata.leaderBoardReport.get(1).getFullName());
        textPointTwo.setText("Point: " + String.valueOf(responsedata.leaderBoardReport.get(1).getTotalPoints()));
        Glide.with(this).load(responsedata.leaderBoardReport.get(1).getProfilePitcure()).into(winnerImageTwo);

        RelativeLayout.LayoutParams relativeParams2 = (RelativeLayout.LayoutParams)relTwo.getLayoutParams();
        relativeParams2.leftMargin=200;
        relativeParams2.topMargin=50;
        relTwo.setLayoutParams(relativeParams2);


        relOne.setVisibility(View.GONE);
        relThree.setVisibility(View.VISIBLE);
        relTwo.setVisibility(View.VISIBLE);
    }
    else if (responsedata.leaderBoardReport.size() > 2) {
        textName.setText(responsedata.leaderBoardReport.get(0).getFullName());
        textPoint.setText("Point: " + String.valueOf(responsedata.leaderBoardReport.get(0).getTotalPoints()));
        Glide.with(this).load(responsedata.leaderBoardReport.get(0).getProfilePitcure()).into(winnerImage);
        textNameTwo.setText(responsedata.leaderBoardReport.get(1).getFullName());
        textPointTwo.setText("Point: " + String.valueOf(responsedata.leaderBoardReport.get(1).getTotalPoints()));
        Glide.with(this).load(responsedata.leaderBoardReport.get(1).getProfilePitcure()).into(winnerImageTwo);
        textNameThree.setText(responsedata.leaderBoardReport.get(2).getFullName());
        textPointThree.setText("Point: " + String.valueOf(responsedata.leaderBoardReport.get(2).getTotalPoints()));
        Glide.with(this).load(responsedata.leaderBoardReport.get(2).getProfilePitcure()).into(winnerImageThree);
    }
}
else
{
    linearNoWinnerFound.setVisibility(View.VISIBLE);
}
    }
}