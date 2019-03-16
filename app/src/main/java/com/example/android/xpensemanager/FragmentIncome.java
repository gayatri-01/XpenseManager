package com.example.android.xpensemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("MyPrefsFile", 0);
        final String username = settings.getString("username", null);
        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
        DataBaseHelper myDb = new DataBaseHelper(getActivity().getApplicationContext());

        lstIncome = new ArrayList<>();
        lstIncome.add(new Income("Business",myDb.findSum("incomes","Business",username),R.drawable.ic_business));
        lstIncome.add(new Income("Salary",myDb.findSum("incomes","Salary",username),R.drawable.ic_salary));
        lstIncome.add(new Income("Coupon",myDb.findSum("incomes","Coupon",username),R.drawable.ic_coupon));
        lstIncome.add(new Income("Grants",myDb.findSum("incomes","Grants",username),R.drawable.ic_savings));
        lstIncome.add(new Income("Sale",myDb.findSum("incomes","Sale",username),R.drawable.ic_sale));




    }
}
