package baghdad.com.news_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        /*
        هذه شاشة تسمى navigation drawer activity
  يتم الضغط على كليك يمين من app ثم new ثم actitity ثم navigation drawer activity
  هذه الشاشة تتميز بانها تضع لك قائمة على اليسار للتنقل بين الشاشة الاخرى
         */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //هذه للشكل الدائري الذي يحمل ايقونة رسال عند الضغط عليه يتم عمل رسالة بالاسفل سوداء تحمل الرسالة الموضوعة هنا
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        //هذه دالة عند الضغط على زر رجوع على الهاتف سيتم تنفيذه
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
        //هنا سيعمل القائمة الصغيرة الذي تحمل نص setting
        getMenuInflater().inflate(R.menu.home, menu);
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
            //هنا عند الضغط على زر setting ماذا سيعمل
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //هذه دالة تقوم بعمل القائمة الجانبية الذي تحمل عدة بيانات
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //هنا عند الضعط على اول زر من القائمة والذي تحمل الـid هذا nav_camera
        } else if (id == R.id.nav_gallery) {
            //هنا عند الضعط على اول زر من القائمة والذي تحمل الـid هذا nav_gallery
        } else if (id == R.id.nav_slideshow) {
            //هنا عند الضعط على اول زر من القائمة والذي تحمل الـid هذا nav_slideshow
        } else if (id == R.id.nav_manage) {
            //هنا عند الضعط على اول زر من القائمة والذي تحمل الـid هذا nav_manage
        } else if (id == R.id.nav_share) {
            //هنا عند الضعط على اول زر من القائمة والذي تحمل الـid هذا nav_share
        } else if (id == R.id.nav_send) {
            //هنا عند الضعط على اول زر من القائمة والذي تحمل الـid هذا nav_send
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
