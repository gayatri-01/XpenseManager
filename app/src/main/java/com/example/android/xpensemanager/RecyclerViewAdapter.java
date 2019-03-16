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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

   Context mContext;
   List<Expense> mData;
   Dialog myDialog;
   Button b;
   EditText e;


    public RecyclerViewAdapter(Context mContext, List<Expense> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v;
       v= LayoutInflater.from(mContext).inflate(R.layout.item_expense,parent, false);
       final MyViewHolder vHolder = new MyViewHolder(v);

       myDialog = new Dialog(mContext);
       myDialog.setContentView(R.layout.dialog_expense);
       myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b= (Button) myDialog.findViewById(R.id.dialog_add);



        e= (EditText) myDialog.findViewById(R.id.dialog_edittext);
        final String str= String.valueOf(e.getText());
        final float amount;
        final boolean isnumeric = StringUtil.isNumeric(str);
        if(!isnumeric&&!str.equals(""))
        amount=Float.parseFloat(str);



       vHolder.item_expense.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               TextView dialog_name_tv= (TextView) myDialog.findViewById(R.id.dialog_name_id);
               TextView dialog_amt_tv= (TextView) myDialog.findViewById(R.id.dialog_amount_id);
               ImageView dialog_expense_image= (ImageView) myDialog.findViewById(R.id.dialog_img);

               dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
               dialog_amt_tv.setText(mData.get(vHolder.getAdapterPosition()).getAmount());
               dialog_expense_image.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());
               final String category= mData.get(vHolder.getAdapterPosition()).getName();
               Toast.makeText(mContext,"Please enter amount spent on "+String.valueOf(mData.get(vHolder.getAdapterPosition()).getName()),Toast.LENGTH_SHORT).show();
               myDialog.show();
               b.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       TextView dialog_name_tv= (TextView) myDialog.findViewById(R.id.dialog_name_id);

                       if(!StringUtil.isNumeric(String.valueOf(e.getText()))){
                           Toast.makeText(mContext,"Please enter a valid amount.",Toast.LENGTH_SHORT).show();
                       }
                       else{
                           String table="Expense";
                           float finalAMT=Float.parseFloat(String.valueOf(e.getText()));
                           //pass finalAMT and category to dbms
                           Toast.makeText(mContext,"Category: "+category,Toast.LENGTH_SHORT).show();
                           //and then close dialog
                           myDialog.dismiss();
                       }

                   }
               });


           }
       });

        return vHolder;
    }




    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_amount.setText(mData.get(position).getAmount());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_expense;
        private TextView tv_name;
        private  TextView tv_amount;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_expense= (LinearLayout) itemView.findViewById(R.id.expense_item_id);
            tv_name= (TextView) itemView.findViewById(R.id.name_expense);
            tv_amount= (TextView) itemView.findViewById(R.id.amount_expense);
            img =(ImageView) itemView.findViewById(R.id.img_expense);
        }
    }
}
