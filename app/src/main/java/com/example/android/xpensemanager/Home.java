package com.example.android.xpensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;
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



public class Home extends AppCompatActivity {



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(Home.this,GoogleSignIn.class));
                }
            }
        };
        mAuth = FirebaseAuth.getInstance();
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        mToggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navHeaderView = navigationView.inflateHeaderView(R.layout.header);
        TextView headerUserName = (TextView) navHeaderView.findViewById(R.id.username);
        TextView headerEmail = (TextView) navHeaderView.findViewById(R.id.email);

      /*  FloatingActionButton f = (FloatingActionButton)findViewById(R.id.fab);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i = new Intent(Home.this,test.class);
                //startActivity(i);
            }
        });*/


        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        String username = settings.getString("username", null);
        String email = settings.getString("email", null);
        String imageUriString = settings.getString("imageURI", null);
        Uri imageUri = Uri.parse(imageUriString);
        if(imageUri!=null)
        {
            CircleImageView image=navHeaderView.findViewById(R.id.profile_image);

            Glide.with(Home.this).load(imageUri).into(image);
        }


        headerUserName.setText(username);
        headerEmail.setText(email);
        navigationView.setItemBackgroundResource(R.drawable.selector);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.categories:
                       Intent intent = new Intent(Home.this, Categories.class);
                        startActivity(intent);
                        break;
                    case R.id.stats:
                      Intent i = new Intent(Home.this, Calendar.class);
                      startActivity(i);
                        break;
                    //do something
                    case R.id.export:
                        Intent m = new Intent(Home.this,expense_graph.class);
                        startActivity(m);
                        break;
                    case R.id.setting:

                    case R.id.about:

                    case R.id.logout:
                        mAuth = FirebaseAuth.getInstance();
                                mAuth.signOut();

                        Auth.GoogleSignInApi.signOut(mGoogleApiClient);

                      break;




                    default:
                        break;
                }
                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
