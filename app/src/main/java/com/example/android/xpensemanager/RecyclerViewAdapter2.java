package com.example.android.xpensemanager;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.helper.StringUtil;

import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder> {

    Context mContext2;
    List<Income> mData2;
    Dialog myDialog2;
    Button b;
    EditText e;


    public RecyclerViewAdapter2(Context mContext2, List<Income> mData2) {
        this.mContext2 = mContext2;
        this.mData2 = mData2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v2;
        v2= LayoutInflater.from(mContext2).inflate(R.layout.item_income,parent, false);
        final MyViewHolder vHolder2 = new MyViewHolder(v2);

        myDialog2 = new Dialog(mContext2);
        myDialog2.setContentView(R.layout.dialog_income);
        myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        b= (Button) myDialog2.findViewById(R.id.dialog2_add);
        e= (EditText) myDialog2.findViewById(R.id.dialog2_edittext);
        final String str= String.valueOf(e.getText());
        final float amount;
        final boolean isnumeric = StringUtil.isNumeric(str);
        if(!isnumeric&&!str.equals(""))
            amount=Float.parseFloat(str);

        vHolder2.item_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_name_tv= (TextView) myDialog2.findViewById(R.id.dialog2_name_id);
                TextView dialog_amt_tv= (TextView) myDialog2.findViewById(R.id.dialog2_amount_id);
                ImageView dialog_income_image= (ImageView) myDialog2.findViewById(R.id.dialog2_img);

                dialog_name_tv.setText(mData2.get(vHolder2.getAdapterPosition()).getName());
                dialog_amt_tv.setText(mData2.get(vHolder2.getAdapterPosition()).getAmount());
                dialog_income_image.setImageResource(mData2.get(vHolder2.getAdapterPosition()).getPhoto());

                final String category= mData2.get(vHolder2.getAdapterPosition()).getName();
                Toast.makeText(mContext2,"Please enter amount received as "+String.valueOf(mData2.get(vHolder2.getAdapterPosition()).getName()),Toast.LENGTH_SHORT).show();
                myDialog2.show();
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView dialog_name_tv= (TextView) myDialog2.findViewById(R.id.dialog2_name_id);

                        if(!StringUtil.isNumeric(String.valueOf(e.getText()))){
                            Toast.makeText(mContext2,"Please enter a valid amount.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String table="Income";
                            float finalAMT=Float.parseFloat(String.valueOf(e.getText()));
                            //pass finalAMT and category to dbms
                            Toast.makeText(mContext2,"Category: "+category,Toast.LENGTH_SHORT).show();
                            //and then close dialog
                            myDialog2.dismiss();
                        }

                    }
                });

            }
        });
        return vHolder2;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        holder.tv_name.setText(mData2.get(position).getName());
        holder.tv_amount.setText(mData2.get(position).getAmount());
        holder.img.setImageResource(mData2.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData2.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_income;
        private TextView tv_name;
        private  TextView tv_amount;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_income= (LinearLayout) itemView.findViewById(R.id.income_item_id);
            tv_name= (TextView) itemView.findViewById(R.id.name_income);
            tv_amount= (TextView) itemView.findViewById(R.id.amount_income);
            img =(ImageView) itemView.findViewById(R.id.img_income);
        }
    }
}
