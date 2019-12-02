package com.example.spiseapp3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room.Repos.Repo;
import com.example.spiseapp3.Menu.Courserow;
import com.example.spiseapp3.Menu.Desserts;
import com.example.spiseapp3.Menu.DessertsAdapter;
import com.example.spiseapp3.Menu.DrinksAdapter;
import com.example.spiseapp3.Menu.Drinksrow;
import com.example.spiseapp3.Menu.Drow;
import com.example.spiseapp3.Menu.Lists;
import com.example.spiseapp3.Menu.MainCourseAdapter;
import com.example.spiseapp3.Menu.ViewPagerAdapter;
import com.example.spiseapp3.ui.RestaurantRequests.ExpandRequest;
import com.example.spiseapp3.ui.RestaurantRequests.RestaurantOrderRequests;
import com.facebook.stetho.Stetho;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



public class scrolling extends AppCompatActivity {

    TabLayout tabLayout;
    String uid;
    static int count=0;
    ImageView image;
    TextView t1;

    @RequiresApi(api = Build.VERSION_CODES.P)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Stetho.initializeWithDefaults(this);

        //////////////////////////////////////////////
        Intent intent=getIntent();
        uid = intent.getStringExtra("res_uid");
        String url=intent.getStringExtra("image_url");
        image=findViewById(R.id.coverPhoto);
        String rs=intent.getStringExtra("res_name");

        t1=findViewById(R.id.userName);
        t1.setText(rs);






        /////////////////////////////////////////////////
        tabLayout = findViewById(R.id.tabs);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ViewPager mViewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        toolbar.setTitle(R.string.app_name);

        //setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#00FFFFFF"));
        // collapsingToolbarLayout.setCollapsedTitleTypeface(TyperRoboto.ROBOTO_REGULAR());
        //collapsingToolbarLayout.setExpandedTitleTypeface(TyperRoboto.ROBOTO_REGULAR());
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Picasso.get().load(url).into(image);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Desserts d= com.example.spiseapp3.Menu.Desserts.newInstance(uid);
        mViewPagerAdapter.addFragment(com.example.spiseapp3.Menu.Drinks.newInstance(uid), "Drinks");
        mViewPagerAdapter.addFragment(d, "Desserts");
        mViewPagerAdapter.addFragment(com.example.spiseapp3.Menu.MainCourse.newInstance(uid), "Main Course");
        mViewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();


        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        ///////////////////////////////////////////
    }

    /////////////////////////////////////
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupTabIcons() {
        final int[] tabIcons = {
                R.drawable.ic_person_black_24dp,
                R.drawable.education,
                R.drawable.tool
        };
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabIcons[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabIcons[1]);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(tabIcons[2]);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0) {
                    Objects.requireNonNull(tab.getIcon()).setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.SRC_IN); }
                if(tab.getPosition() == 1){
                    Objects.requireNonNull(tab.getIcon()).setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.SRC_IN); }
                if(tab.getPosition() == 2){
                    Objects.requireNonNull(tab.getIcon()).setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.SRC_IN); }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Objects.requireNonNull(tab.getIcon()).setColorFilter(Color.parseColor("#0A6CEB"), PorterDuff.Mode.SRC_IN);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Click the following link");
            i.putExtra(android.content.Intent.EXTRA_TEXT, "https://abia456.github.io/resume/");
            startActivity(Intent.createChooser(i,"Share via"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void click_listener4(View view) {

        //+++++++++++++++++++++++++++++++

        if(Lists.cart_res_id!=null) {

            if (!uid.matches(Lists.cart_res_id)) {
                //empty all lists

                if(DessertsAdapter.newlist==true) {
                    Lists.desserts = DessertsAdapter.dessertsTemp;
                    if(DrinksAdapter.newlist==false) {
                        Lists.drinks = new ArrayList<Drinksrow>();
                    }

                    if(MainCourseAdapter.newlist==false){
                    Lists.maincourse= new ArrayList<Courserow>();}

                }

                if(DrinksAdapter.newlist==true) {
                    Lists.drinks = DrinksAdapter.drinksTemp;

                    if(DessertsAdapter.newlist==false) {
                        Lists.desserts = new ArrayList<Drow>();
                    }


                    if(MainCourseAdapter.newlist==false){
                        Lists.maincourse= new ArrayList<Courserow>();}

                }

                if(MainCourseAdapter.newlist==true) {
                    Lists.maincourse = MainCourseAdapter.CourseTemp;
                    if(DrinksAdapter.newlist==false) {
                        Lists.drinks = new ArrayList<Drinksrow>();
                    }
                    if(DessertsAdapter.newlist==false) {
                        Lists.desserts = new ArrayList<Drow>();
                    }

                }
                Toast.makeText(scrolling.this, "Previous CArt Emptied", Toast.LENGTH_SHORT).show();

            }
        }

        ///++++++++++++++++++++++++++++
        Lists.res_uid=uid;
        Lists.addtoCartItemList();

        //////////////////////

        Repo r=new Repo(this);

        Toast.makeText(scrolling.this, "Add to cart clicked", Toast.LENGTH_SHORT).show();
        for(int i=0;i< Lists.desserts.size();i++)
        {
            Drow d=Lists.desserts.get(i);
            r.insertTask(d.item_id,d.Name, d.Description, d.price);
        }

        for(int i=0;i< Lists.drinks.size();i++)
        {
            Drinksrow d=Lists.drinks.get(i);
            r.insertTaskDrink(d.item_id,d.DName, d.Dprice);
        }

        for(int i=0;i< Lists.maincourse.size();i++)
        {
            Courserow d=Lists.maincourse.get(i);
            r.insertTaskMainCourse(d.item_id,d.Name, d.Description, d.price);
        }
    }


    public void click_listener5(View view) {

        Intent intent = new Intent (scrolling.this,Cart.class);
        startActivity(intent);
    }

    //////////////////////////////////


}
