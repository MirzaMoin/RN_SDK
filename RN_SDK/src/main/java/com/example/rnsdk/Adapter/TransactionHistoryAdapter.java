package com.example.rnsdk.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rnsdk.Models.TransactionHistoryChildMenuModel;
import com.example.rnsdk.Models.TransactionHistoryModel;
import com.example.rnsdk.R;
import com.example.rnsdk.Utility.Utility;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder> {

    Context context;
    List<TransactionHistoryModel> responseModel;
    RelativeLayout relImagePreview;
    ImageView imgPreview,
            imgPreviewClose;

    public TransactionHistoryAdapter(Context context, List<TransactionHistoryModel> responseModel,
                                     RelativeLayout relImagePreview, ImageView imgPreview, ImageView imgPreviewClose) {
        this.context = context;
        this.responseModel = responseModel;
        this.relImagePreview = relImagePreview;
        this.imgPreview = imgPreview;
        this.imgPreviewClose = imgPreviewClose;

        for(TransactionHistoryModel data : responseModel)
        {
            data.setExpanded(false);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.content_transaction_history, parent, false);
        return new ViewHolder(listItem);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {


        holder.setIsRecyclable(false);
        if(position % 2 != 0){

            holder.linearContentTH.setBackgroundColor(Utility.getColor("#99999933"));
        }

        imgPreviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relImagePreview.setVisibility(View.GONE);
            }
        });
        Date date = null;
        TransactionHistoryModel history = this.responseModel.get(position);
        String dtStart = history.getTransactionDate();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterOut = new SimpleDateFormat("dd MMM yyyy");
        try {
            date = format.parse(dtStart);
            assert date != null;
            formatterOut.format(date);
            holder.textDate.setText("" + formatterOut.format(date));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.textBalance.setText("" + history.getBalance());
        holder.textLocation.setText("" + history.getLocationName());


//        Double point = Double.parseDouble(history.getPoints());
        if (history.getPoints().charAt(0) == '-') {
            holder.textPoint.setText("" + history.getPoints());
            holder.textPoint.setTextColor(Utility.getColor("#fc1c03ff"));
        } else {
            holder.textPoint.setText("+" + history.getPoints());
        }

        holder.textStatus.setText("" + history.getTransactionStatus());
        holder.textType.setText("" + history.getType());


        holder.linearCollEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (history.isExpanded()) {
                    history.setExpanded(false);
                    holder.linearLocation.setVisibility(View.GONE);
                    holder.linearStatus.setVisibility(View.GONE);
                    holder.linearType.setVisibility(View.GONE);
                    holder.linearTotalSpend.setVisibility(View.GONE);
                    holder.linearImagesTH.setVisibility(View.GONE);
                    holder.linearOfferNameTH.setVisibility(View.GONE);
                    holder.imageExpand.setRotation(180);

                } else {
                    history.setExpanded(true);

                    holder.linearLocation.setVisibility(View.VISIBLE);
                    holder.linearStatus.setVisibility(View.VISIBLE);
                    holder.linearType.setVisibility(View.VISIBLE);

                    for (TransactionHistoryChildMenuModel s : history.childMenus) {
                        if (s.getImages() != null) {
                            holder.textImagesTitleTH.setText(s.getName());
                            holder.linearImagesTH.setVisibility(View.VISIBLE);
                            if (s.getImages().size() == 1) {
                                holder.cardFirstTH.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(0)).into(holder.imageFirstTH);
                            } else if (s.getImages().size() == 2) {
                                holder.cardFirstTH.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(0)).into(holder.imageFirstTH);

                                holder.cardSecondTH.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(1)).into(holder.imageSecondTH);

                                Log.e(TAG, "Image First: " + s.getImages().get(0) + "Image Second: " + s.getImages().get(1));
                            } else if (s.getImages().size() == 3) {
                                holder.cardFirstTH.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(0)).into(holder.imageFirstTH);


                                holder.cardSecondTH.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(1)).into(holder.imageSecondTH);

                                holder.cardThirdTH.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(2)).into(holder.imageThirdTH);
                            }


//                            s.getImages().size()
                        } else {
                            if (s.getName().equals("Total Spent")) {
                                holder.linearTotalSpend.setVisibility(View.VISIBLE);

                                holder.textTotalSpendTitleTH.setText(s.getName() + ": ");
                                holder.textTotalSpend.setText(s.getStringValue());
                            }
                            if (s.getName().equals("Offer Name")) {
                                holder.linearOfferNameTH.setVisibility(View.VISIBLE);

                                holder.textOfferNameTitleTH.setText(s.getName() + ": ");
                                holder.textOfferNameTH.setText(s.getStringValue());
                            }
                        }

                        holder.imageFirstTH.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                relImagePreview.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(0)).into(imgPreview);
                            }
                        });
                        holder.imageSecondTH.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                relImagePreview.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(1)).into(imgPreview);
                            }
                        });
                        holder.imageThirdTH.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                relImagePreview.setVisibility(View.VISIBLE);
                                Glide.with(context).load(s.getImages().get(2)).into(imgPreview);
                            }
                        });

                    }

                    holder.imageExpand.setRotation(0);

                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return responseModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearCollEx,
                linearLocation,
                linearType,
                linearStatus,
                linearTotalSpend,
                linearImagesTH,
                linearOfferNameTH,
                linearContentTH;

        TextView textDate,
                textLocation,
                textPoint,
                textBalance,
                textType,
                textStatus,
                textTotalSpend,
                textTotalSpendTitleTH,
                textImagesTitleTH,
                textOfferNameTitleTH,
                textOfferNameTH;
        ImageView imageExpand, imageFirstTH, imageSecondTH, imageThirdTH;

        CardView cardFirstTH,cardSecondTH,cardThirdTH;


        public ViewHolder(View itemView) {
            super(itemView);

            linearCollEx = itemView.findViewById(R.id.linearCollExTH);
            linearLocation = itemView.findViewById(R.id.linearLocationTH);
            linearStatus = itemView.findViewById(R.id.linearStatusTH);
            linearType = itemView.findViewById(R.id.linearTypeTH);
            linearTotalSpend = itemView.findViewById(R.id.linearTotalSpendTH);
            linearImagesTH = itemView.findViewById(R.id.linearImagesTH);
            linearOfferNameTH = itemView.findViewById(R.id.linearOfferNameTH);
            linearContentTH = itemView.findViewById(R.id.linearContentTH);
            imageExpand = itemView.findViewById(R.id.imgExpand);


            textDate = itemView.findViewById(R.id.textDateTH);
            textLocation = itemView.findViewById(R.id.textLocationTH);
            textPoint = itemView.findViewById(R.id.textPointTH);
            textBalance = itemView.findViewById(R.id.textBalanceTH);
            textType = itemView.findViewById(R.id.textTypeTH);
            textStatus = itemView.findViewById(R.id.textTransactionStatusTH);
            textTotalSpend = itemView.findViewById(R.id.textTotalSpendTH);
            textTotalSpendTitleTH = itemView.findViewById(R.id.textTotalSpendTitleTH);
            textImagesTitleTH = itemView.findViewById(R.id.textImagesTitleTH);
            textOfferNameTitleTH = itemView.findViewById(R.id.textOfferNameTitleTH);
            textOfferNameTH = itemView.findViewById(R.id.textOfferNameTH);
            imageFirstTH = itemView.findViewById(R.id.imageFirstTH);
            imageSecondTH = itemView.findViewById(R.id.imageSecondTH);
            imageThirdTH = itemView.findViewById(R.id.imageThirdTH);
            cardFirstTH = itemView.findViewById(R.id.cardFirstTH);
            cardSecondTH = itemView.findViewById(R.id.cardSecondTH);
            cardThirdTH = itemView.findViewById(R.id.cardThirdTH);


        }
    }
}