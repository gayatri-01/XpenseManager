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

public class FragmentExpense extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Expense> lstExpense;
    public FragmentExpense() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.expense_fragment,container,false);
        myrecyclerview= (RecyclerView) v.findViewById(R.id.expense_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),lstExpense);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstExpense = new ArrayList<>();
        lstExpense.add(new Expense("Food","0",R.drawable.ic_food));
        lstExpense.add(new Expense("Electronics","0",R.drawable.ic_electronic_devices));
        lstExpense.add(new Expense("Transport","0",R.drawable.ic_transportation));
        lstExpense.add(new Expense("Gift","0",R.drawable.ic_gift));
        lstExpense.add(new Expense("Bills","0",R.drawable.ic_bills));
        lstExpense.add(new Expense("Home","0",R.drawable.ic_home));
        lstExpense.add(new Expense("Education","0",R.drawable.ic_education));
        lstExpense.add(new Expense("Child Care","0",R.drawable.ic_child));
        lstExpense.add(new Expense("Miscellaneous","0",R.drawable.ic_misc));


    }
}
