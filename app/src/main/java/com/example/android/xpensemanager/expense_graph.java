package com.example.android.xpensemanager;


import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;

public class expense_graph extends AppCompatActivity {
    private static String TAG = "Main Activity";
    private int[] expenses = {350,500,960,830,220,300,560,700/*,415,230*/};
    private String[] category = {"Food","Electronics","Transport","Gift","Bills","Home","Miscellaneous","Balance"/*,"Education","Child Care"*/};
    PieChart pieChart;
    Legend legend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_graph);
        Log.d(TAG, "onCreate: starting to create pie chart");
        /*relativeLayout = (RelativeLayout) findViewById(R.id.layout_id);
        relativeLayout.addView(pieChart);
        relativeLayout.setBackgroundColor(Color.WHITE);*/
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        Description description = new Description();
        pieChart.setUsePercentValues(true);
        pieChart.setRotationEnabled(true);
        /*pieChart.setExtraLeftOffset(5f);
        pieChart.setExtraRightOffset(5f);*/
        pieChart.setHoleRadius(50f);
        description.setPosition(5f,5f);
        description.setTextSize(13f);
        pieChart.setDescription(description);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setCenterText("Expenses Per Category");
        pieChart.setCenterTextSize(18f);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(13f);
        pieChart.animateXY(1500,1500);
        addDataSet();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if(e==null)
                    return;

                int ind = findIndex(e);
                if(ind!=category.length-1)
                    customDialog("Expense","Category: "+category[ind]+"\nExpense: $"+e.getY(),"Cancel","Ok");
                    //Toast.makeText(expense_graph.this,"Category: "+category[ind]+" is: "+e.getY(),Toast.LENGTH_SHORT).show();
                else
                    customDialog("Expense","Balance Left: $"+e.getY(),"Cancel","Ok");
                //Toast.makeText(expense_graph.this,"Balance Left: "+e.getY(),Toast.LENGTH_SHORT).show();
            }
            /*"Month: "+ monthName[h.getDataIndex()] +"\n"+"Expense: "+expenses[h.getDataIndex()]*/
            @Override
            public void onNothingSelected() {
                Toast.makeText(expense_graph.this,"Nothing selected!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addDataSet(){
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> expense_entry = new ArrayList<>();
        ArrayList<String> months_entry = new ArrayList<>();
        for (int i=0;i<expenses.length;i++){
            expense_entry.add(new PieEntry(expenses[i],category[i]));
        }
        for (int i=0;i<category.length;i++){
            months_entry.add(category[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(expense_entry,"Expenses");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setValueTextSize(10f);
        //pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        ArrayList<Integer> color_names = new ArrayList<>();
        color_names.add(Color.rgb(255,247,0));
        color_names.add(Color.rgb(255,131,0));
        color_names.add(Color.rgb(253,255,129));
        color_names.add(Color.rgb(255,188,12));
        color_names.add(Color.rgb(255,208,0));
        color_names.add(Color.rgb(241,196,15));
        color_names.add(Color.rgb(255,108,0));
        color_names.add(Color.rgb(78,255,0));
        pieDataSet.setColors(color_names);
        pieChart.getLegend().setEnabled(false);
        //pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
       /* legend = pieChart.getLegend();
        legend.setXEntrySpace(5f);
        legend.setXOffset(80f);
        legend.setYOffset(150f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setFormSize(15f);
        legend.setWordWrapEnabled(true);
        legend.setFormToTextSpace(3f);
        legend.setDrawInside(false);
        legend.setMaxSizePercent(0.95f);*/
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(15f);
        pieData.setValueTextColor(Color.BLACK);
        pieDataSet.setSelectionShift(7f);
    }

    public int findIndex(Entry e){
        int index = 0;
        for(int i=0;i<expenses.length;i++){
            if(expenses[i]==e.getY()){
                index = i;
                break;
            }
            else
                continue;
        }
        return index;
    }

    public void customDialog(String title,String msg,final String cancel,final String ok){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // write toast if necessary...
            }
        });
        alertDialogBuilder.show();
    }

}



