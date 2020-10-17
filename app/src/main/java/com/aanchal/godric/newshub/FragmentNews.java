package com.aanchal.godric.newshub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNews extends Fragment {

    private ArrayList<News> news;
    private RecyclerView recyclerView;
    private ApiInterface apiInterface;
    private SwipeRefreshLayout swipeRefreshLayout;
    View view;

    public FragmentNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);
        init();
        newsCall();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                newsCall();
                //Toast.makeText(getContext(), "Swipe Call", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;

    }

    public void newsCall() {
        String source = getArguments().getString("source");
        Call<NewsList> call = apiInterface.getData(source,"957706f3ffbc4d3187f5ac29159ea0b3");
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                NewsList newsList = response.body();
                news = newsList.getArticles();
                //Toast.makeText(getContext(), "Swipe Call", Toast.LENGTH_SHORT).show();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.swapAdapter(new RecyclerViewAdapter(getContext(),news),true);
                //Toast.makeText(getContext(), "title - "+news.get(0).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
            }
        });
    }

    private void init() {

        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        recyclerView = view.findViewById(R.id.fragment_recycler);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

    }

}
