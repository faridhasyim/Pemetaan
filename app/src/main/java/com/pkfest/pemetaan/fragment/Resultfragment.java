package com.pkfest.pemetaan.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkfest.pemetaan.R;
import com.pkfest.pemetaan.adapter.ResultAdapter;
import com.pkfest.pemetaan.helper.RecyclerViewEmptySupport;
import com.pkfest.pemetaan.helper.Utils;
import com.pkfest.pemetaan.model.Data;
import com.pkfest.pemetaan.model.Result;
import com.pkfest.pemetaan.service.ServiceApi;
import com.pkfest.pemetaan.service.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rifky on 05/06/17.
 */

public class Resultfragment extends Fragment {
    private static final String TAG = "Resultfragment";
    private static final String QUERY = "pekalongan";
    private static final String RADIUS = "1000";
    private static final String SENSOR = "true";
    private static final String KEY = "AIzaSyA8szrI9Ue4EwyUwTgz7Nk0c39qMal0pN4";
    private RecyclerViewEmptySupport recyclerView;
    private TextView keterangan;
    private TextView textKosong;
    private ServiceApi api;
    private ResultAdapter adapter;
    private List<Result> resultList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_list,container,false);
        recyclerView = (RecyclerViewEmptySupport) fragmentView.findViewById(R.id.rv_list_data);
        keterangan = (TextView)fragmentView.findViewById(R.id.keterangan_list);
        textKosong = (TextView)fragmentView.findViewById(R.id.txt_data_kosong);
        resultList = new ArrayList<>();
        adapter = new ResultAdapter(resultList,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(textKosong);
        keterangan.setText(getArguments().getString("keterangan"));

        api = ServiceGenerator.createService(ServiceApi.class);
        ambilData(getArguments().getString("types"));

        recyclerView.setAdapter(adapter);
        return fragmentView;
    }

    private void ambilData(String types){
        final ProgressDialog dialog = Utils.getWaitDialog(getContext(),"Loading...");
        dialog.show();
        dialog.setCancelable(false);
        Call<Data> resultCall = api.getResult(QUERY,RADIUS,types,SENSOR,KEY);
        resultCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Log.e(TAG,response.body().getResults().get(0).getFormattedAddress());
                dialog.dismiss();
                resultList.addAll(response.body().getResults());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                dialog.dismiss();
                textKosong.setText(R.string.tidak_terkoneksi);
                keterangan.setVisibility(View.GONE);
                Utils.toastLong(getContext(),"Mengambil Data Response Gagal, Pastikan Terkoneksi Internet! ");
                Log.e(TAG," Mengambil Data Response Gagal "+t.getMessage());
            }
        });
    }
}
