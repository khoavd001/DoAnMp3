package com.example.doanmp3.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doanmp3.Activity.AllSingerActivity;
import com.example.doanmp3.Adapter.PlaylistAdapter;
import com.example.doanmp3.Adapter.SingerAdapter;
import com.example.doanmp3.Model.CaSi;
import com.example.doanmp3.Model.Playlist;
import com.example.doanmp3.R;
import com.example.doanmp3.Service.APIService;
import com.example.doanmp3.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SingerFragment extends Fragment {

    View view;
    TextView txt;
    RecyclerView recyclerView;
    public SingerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_singer, container, false);
        txt = view.findViewById(R.id.txt_singer);
        recyclerView = view.findViewById(R.id.rv_singer);
        GetData();


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllSingerActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<CaSi>> callback = dataService.GetRanDomCaSi();
        callback.enqueue(new Callback<List<CaSi>>() {
            @Override
            public void onResponse(Call<List<CaSi>> call, Response<List<CaSi>> response) {
                ArrayList<CaSi> caSis = (ArrayList<CaSi>) response.body();
                SingerAdapter adapter = new SingerAdapter(getActivity() ,caSis);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CaSi>> call, Throwable t) {

            }
        });
    }
}