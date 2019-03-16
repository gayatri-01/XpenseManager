package com.example.android.xpensemanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentIncome extends Fragment {
    View v2;
    private RecyclerView myrecyclerview2;
    private List<Income> lstIncome;
    public FragmentIncome() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v2= inflater.inflate(R.layout.income_fragment,container,false);
        myrecyclerview2= (RecyclerView) v2.findViewById(R.id.income_recyclerview);
        RecyclerViewAdapter2 recyclerAdapter2 = new RecyclerViewAdapter2(getContext(),lstIncome);
        myrecyclerview2.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview2.setAdapter(recyclerAdapter2);
        return v2;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstIncome = new ArrayList<>();
        lstIncome.add(new Income("Business","0",R.drawable.ic_business));
        lstIncome.add(new Income("Salary","0",R.drawable.ic_salary));
        lstIncome.add(new Income("Coupon","0",R.drawable.ic_coupon));
        lstIncome.add(new Income("Grants","0",R.drawable.ic_savings));
        lstIncome.add(new Income("Sale","0",R.drawable.ic_sale));




    }
}
