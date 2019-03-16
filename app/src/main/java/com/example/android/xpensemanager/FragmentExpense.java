package com.example.android.xpensemanager;

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

        SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("MyPrefsFile", 0);
        final String username = settings.getString("username", null);
        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
        DataBaseHelper myDb = new DataBaseHelper(getActivity().getApplicationContext());
        lstExpense = new ArrayList<>();
        lstExpense.add(new Expense("Food",myDb.findSum("expenses","Food",username),R.drawable.ic_food));
        lstExpense.add(new Expense("Electronics",myDb.findSum("expenses","Electronics",username),R.drawable.ic_electronic_devices));
        lstExpense.add(new Expense("Transport",myDb.findSum("expenses","Transport",username),R.drawable.ic_transportation));
        lstExpense.add(new Expense("Gift",myDb.findSum("expenses","Gift",username),R.drawable.ic_gift));
        lstExpense.add(new Expense("Bills",myDb.findSum("expenses","Bills",username),R.drawable.ic_bills));
        lstExpense.add(new Expense("Home",myDb.findSum("expenses","Home",username),R.drawable.ic_home));
        lstExpense.add(new Expense("Education",myDb.findSum("expenses","Education",username),R.drawable.ic_education));
        lstExpense.add(new Expense("Child Care",myDb.findSum("expenses","ChildCare",username),R.drawable.ic_child));
        lstExpense.add(new Expense("Miscellaneous",myDb.findSum("expenses","Miscellaneous",username),R.drawable.ic_misc));


    }
}
