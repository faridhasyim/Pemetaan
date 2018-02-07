package com.pkfest.pemetaan.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkfest.pemetaan.R;
import com.pkfest.pemetaan.helper.Utils;
import com.pkfest.pemetaan.model.Result;

import java.util.List;

/**
 * Created by rifky on 04/06/17.
 */

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ResultAdapter";
    private List<Result> dataResults;
    private Context context;

    public ResultAdapter(List<Result> dataResults, Context context) {
        this.dataResults = dataResults;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindData(dataResults.get(position));
    }

    @Override
    public int getItemCount() {
        return dataResults.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView alamat;
        private CardView cardView;
        private double longitude;
        private double latitude;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_item_list);
            alamat = (TextView) itemView.findViewById(R.id.alamat_item_list);
            cardView = (CardView) itemView.findViewById(R.id.cardview_item_list);
        }

        void bindData(final Result result) {
            this.name.setText(result.getName());
            this.alamat.setText(result.getFormattedAddress());
            this.latitude = result.getGeometry().getLocation().getLat();
            this.longitude = result.getGeometry().getLocation().getLng();
            this.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + ","
                            + longitude);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    try {
                        v.getContext().startActivity(mapIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Utils.toastLong(v.getContext(),"Smartphone anda tidak ada aplikasi maps yang terinstall!");
                    }

                }
            });
        }

    }
}
