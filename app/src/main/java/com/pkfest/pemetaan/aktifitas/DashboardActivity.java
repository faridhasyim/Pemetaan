package com.pkfest.pemetaan.aktifitas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pkfest.pemetaan.R;
import com.pkfest.pemetaan.dialog.DialogBantuan;
import com.pkfest.pemetaan.dialog.DialogTentang;
import com.pkfest.pemetaan.fragment.Resultfragment;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadFragment(new Resultfragment(),"school","Data Sekolah di Pekalongan");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tentang) {
            tampilDialogTentang();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            loadFragment(new Resultfragment(),"school","Data Sekolah di Pekalongan");
        } else if (id == R.id.nav_bantuan) {
            tampilDialogBantuan();
        } else if (id == R.id.nav_share){
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Ada aplikasi pemetaan keren loh, ayo buruan download di www.stmik-wp.ac.id";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "\n\nvia "+getString(R.string.app_name)+" App");
            startActivity(Intent.createChooser(sharingIntent, "Bagikan via :"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void tampilDialogBantuan(){
        DialogBantuan dialogBantuan = new DialogBantuan();
        dialogBantuan.show(getFragmentManager(),"Bantuan");
    }
    private void tampilDialogTentang(){
        DialogTentang dialogTentang = new DialogTentang();
        dialogTentang.show(getFragmentManager(),"Tentang");
    }

    private void loadFragment(Fragment fr, String types, String keterangan){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        Bundle dataBundle = new Bundle();
        dataBundle.putString("types",types);
        dataBundle.putString("keterangan",keterangan);
        fr.setArguments(dataBundle);
        fragmentTransaction.replace(R.id.fragment_dashboard, fr);
        fragmentTransaction.commit();
    }
}
