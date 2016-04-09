package view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.jude.beam.expansion.BeamBaseActivity;
import com.panshen.ajiandan.ajiandan.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Config;
import config.Volley_NetRequest;
import presenter.MainActivityPresenter;
import view.fragment.PicFragment;
import view.fragment.WuliaoFragment;

public class MainActivity extends BeamBaseActivity<MainActivityPresenter>
        implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.app_bar)
    AppBarLayout appBarLayout;
    @Bind(R.id.frag_contener)
    FrameLayout framelayout;
    WuliaoFragment wuliaof;
    PicFragment picf;
    FragmentTransaction fragmenttransaction;
    FragmentManager fragmentmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initapperence();
        initragment();
    }

    public void initapperence() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        navigationView.setNavigationItemSelectedListener(this);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (verticalOffset != 0) {
//                    fab.hide();
//                } else {
//                    fab.show();
//                }
//            }
//        });
    }

    public void initragment() {
        wuliaof = new WuliaoFragment();
        picf = new PicFragment();
         fragmentmanager = getSupportFragmentManager();
        fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.replace(R.id.frag_contener, wuliaof).commit();
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                Volley_NetRequest.getInstance(MainActivity.this).addToQueue(Config.DUANZIHOST);
//            }
//        }.start();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.frag_contener, wuliaof).commit();
        } else if (id == R.id.nav_gallery) {
            fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.frag_contener, picf).commit();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }

        return false;
    }
}
