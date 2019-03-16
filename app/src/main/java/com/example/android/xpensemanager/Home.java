package com.example.android.xpensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        mToggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navHeaderView = navigationView.inflateHeaderView(R.layout.header);
        TextView headerUserName = (TextView) navHeaderView.findViewById(R.id.username);
        TextView headerEmail = (TextView) navHeaderView.findViewById(R.id.email);

        FloatingActionButton f = (FloatingActionButton)findViewById(R.id.fab);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i = new Intent(Home.this,test.class);
                //startActivity(i);
            }
        });


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
                      // Intent i = new Intent(Home.this, Calendar.class);
                       //startActivity(i);
                        break;
                    //do something
                    case R.id.export:
                        //lalalla
                        break;
                    case R.id.setting:

                    case R.id.about:

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
