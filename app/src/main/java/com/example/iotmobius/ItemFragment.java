package com.example.iotmobius;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemFragment newInstance(String param1, String param2) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //??????????????? -> ???????????? ?????? ??????
    private ImageButton btn_buy;
    ItemAdapter adapter;
    private ArrayList<DataItem> datas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_item, container, false);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        RecyclerView recyclerView = rootView.findViewById(R.id.item_recycler_view); // ?????????????????? ????????? ??????

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)) ; // ????????????????????? ???????????? ????????? ??????
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // ItemAnimator ??? View ?????????????????? ????????? ??? ?????? ????????? ?????? ??????

        adapter = new ItemAdapter(getContext()); // ????????? ??????
        recyclerView.setAdapter(adapter);// ????????????????????? ???????????????


        getitemData();



        btn_buy = rootView.findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MarketActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
        // Inflate the layout for this fragment ???????????? ???????????? ?????? ???????????? ~~
//        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    private void getitemData () {
        //???????????? ???????????? ????????????.

        GetMyItem get = new GetMyItem();
        datas = get.getMyItem();
        for(int i = 0; i<datas.size();i++) {
            System.out.println(datas.get(i));
            adapter.addItem(datas.get(i));
            adapter.notifyDataSetChanged();
        }
    }
}