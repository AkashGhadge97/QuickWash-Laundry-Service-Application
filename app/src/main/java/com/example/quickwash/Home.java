package com.example.quickwash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView nName,nEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String uName=getIntent().getStringExtra("Name");
        String uAddress=getIntent().getStringExtra("Address");
        String uEmail=getIntent().getStringExtra("Email");
        String uMobile=getIntent().getStringExtra("Mobile");

        Fragment fragment = null;
        fragment = new BookOrderFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.screen_area, fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        nName = headerView.findViewById(R.id.navName);
        nEmail=headerView.findViewById(R.id.navEmail);

        nName.setText(uName);
        nEmail.setText(uEmail);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment=null;

        String uName=getIntent().getStringExtra("Name");
        String uAddress=getIntent().getStringExtra("Address");
        String uEmail=getIntent().getStringExtra("Email");
        String uMobile=getIntent().getStringExtra("Mobile");

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home_book_order)
        {
            fragment=new BookOrderFragment();
        }
        else if (id == R.id.nav_profile)
        {
             fragment=new ProfileFragment();
             Bundle b=new Bundle();
             b.putString("Name",uName);
             b.putString("Email",uEmail);
             b.putString("Mobile",uMobile);
             b.putString("Address",uAddress);
             fragment=new ProfileFragment();
             fragment.setArguments(b);;

        }
        else if (id == R.id.nav_orders)
        {
             fragment=new YourOrdersFragment();
        }
        else if (id == R.id.nav_pricelist)
        {
             fragment=new PriceListFragment();
        }
        else if (id == R.id.nav_aboutus)
        {
             fragment=new AboutUsFragment();
        }
        else if (id == R.id.nav_help)
        {
              fragment=new HelpFragment();
        }

        if(fragment != null)
        {
            FragmentManager fragmentManager=    getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area,fragment);

            fragmentTransaction.commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
