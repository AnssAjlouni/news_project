package baghdad.com.news_project;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//هنا تعريف listview باسم ListNews
    ListView ListNews;

    /*
    هذه شاشة تسمى navigation drawer activity
يتم الضغط على كليك يمين من app ثم new ثم actitity ثم navigation drawer activity
هذه الشاشة تتميز بانها تضع لك قائمة على اليسار للتنقل بين الشاشة الاخرى
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //هنا ليتم ربط listview مع عنوانها بid
        ListNews = (ListView) findViewById(R.id.list_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //هذه للشكل الدائري الذي يحمل ايقونة رسال عند الضغط عليه يتم عمل رسالة بالاسفل سوداء تحمل الرسالة الموضوعة هنا
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        /*
        في اول باراميتر يتم وضع نوع الريكويست اما post او get ونحن استخدمنا post فنضح post
        في الباراميتر الثاني نضع عنوان الصفحة بالسيرفر الذي سيتم التعامل معها وجلب منها بيانات json
        ونحن وضعنا ملف كامل بالمشروع اسمه AppConfig.java
        هذا الملف سيكون داخله كل البيانات الرئيسية بالمشروع
       وداخله URL_NEWS_PROJECT وهذا يحمل عنوان الملف  JSON الذي سيجلب منه البيانات
         */

        /*
        ملاحظتين هامتين
        اولا يجب عليك استدعاء مكتبة volley عن طريق الدخول الى ملف Gradle Scripts ثم build.gradle ثم اضافة
        compile 'com.mcxiaoke.volley:library-aar:1.0.0'
        داخل dependencies
        ثانيا يجب عليك عمل اذن الدخول للانترنت لان البيانات تعتبر على الانترنت عن طريق الذهاب الى
        manifests
        ثم اضافة هذا الإذن
        <uses-permission android:name="android.permission.INTERNET" />
         */
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_NEWS_PROJECT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //البيانات الذي سيتم جلبها ستكون هنا في الباراميتر response
                        //سيتم طباعة البيانات الذي تم جلبها لنا عن طريق toast
                        //Toast.makeText(getBaseContext(), response + "", Toast.LENGTH_LONG).show();
                        // هنا سيتم استخدام دالة اسمها
                        // وسيتم ارسال لها بيانات json الذي تم تلقيها من السيرفر داخل الباراميتر response
                        try {
                            ReadNews(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //في حال حصل خطا باتصال مع السيرفر سيتم تنفيذ هذا المكان
                Toast.makeText(Home.this, " error : " + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //مكان ارسال القيم للويب
                //حاليا لا نريد ارسال اي قيم الى الانترنت ونريد فقط جلب ملفات json فلذلك لا نرسل شئ 
                return params;
            }
        };
        // هنا لتشغيل الطلب الذي سيتم ارساله للسيرفر
        queue.add(stringRequest);


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

    //هذه دالة ReadNews وهي لجلب بيانات json ووضعها داخل listview بطريقة مناسبة
    public void ReadNews(String responce) throws JSONException {
        final List<String[]> DataList = new LinkedList<String[]>();
        JSONArray data = new JSONArray(responce);

        for (int i = 0; i < data.length(); i++) {
            //هنا يتم جلب البيانات وتخزين رقم الخبر وعنوان الخبر وصوصرة الخبر داخل متغيرات نصية كما هو موضح
            JSONObject object = data.getJSONObject(i);
            String NewsTitle = object.getString("news_title");
            String NewsImage = object.getString("news_img");
            String NewsID = object.getString("news_id");
            //Toast.makeText(HistoryOrderCustomer.this, OrderId + "-" + OrderDate, Toast.LENGTH_SHORT).show();
            //هنا يتم وضع البيانات داخل datalist على شكل مصفوفة كما هو موضح
            DataList.add(new String[]{
                    NewsTitle,
                    NewsImage,
                    NewsID
            });
        }// end for


        ListAdapter listAdapter = new ArrayAdapter<String[]>(
                getBaseContext(),
                //هنا الجزء الاهم وهو يجب عليك ان تتصل بملف ليتم تكراره بكل مرة بlistview
                //ويحتاج ان تدخل له رقم id واسم الشاشة كما هو موضح
                R.layout.item_news,
                R.id.title,
                DataList) {
            //هذا المكان مهم جدا بكل item بlistview ماذا سيعرض
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // Must always return just a View.
                View view = super.getView(position, convertView, parent);
                //هنا نتصل بملف datalist الذي قمنا بانشائه بالاعلى ونعطيه البيانات حسب قيمة position
                String[] entry = DataList.get(position);
                //يتم تعريف الادوات الذي داخل الشاشة الذي سيتم تكريرها هنا
                //ملاحظة مهمة عند تعريف اي شئ من داخل ذلك الشاشة يجب وضع view.findViewById
                // findViewById وليس
                // لانك بهذه الحالة تقله ابحث بداخل الشاشة الذي قمت بتعريفك عليها باسم view
                //هنا تعريف النص الذي داخل الشاشة
                TextView text1 = (TextView) view.findViewById(R.id.title);
                //وهنا تعريف الصورة الذي داخل الشاشة
                ImageView image_news = (ImageView) view.findViewById(R.id.image_news);
                //هنا يتم ارسال قيمة العنوان من json الذي قمنا بعمله الى textview الذي سميناه text1
                text1.setText(entry[0]);
                //هنا يتم طرح سؤال بسيط هل النص الذي يمثل الصورة فارغ ام لا
                //في حال كان فارغ لن يضع صورة واذا كان غير فارغ يتم ادخال الصورة بداخل image_news
                if (entry[1].length() > 0) {
                    //هنا قمنا باستخدام مكتبة Picasso لعرض الصور من الانترنت
                    //رابط موقع هذه المكتبة وتحميل المكتبة http://square.github.io/picasso/
                    //هذه المكتبة فقط تعطيه عنوان صورة على موقع ويب وهو يقوم بعرضها لك داخل imageview الذي تحدده
                    //نحن قمنا بعمل عنوان للسيرفر الخاص بنا ثم دمجناه مع مجلد img ثم دمجناه مع النص الذي جائنا من السيرفر ليكون عنوان الصورة كاملة
                    Picasso.with(getBaseContext()).load(AppConfig.URL_SERVER + "img/" + entry[1]).into(image_news);
                }

                return view;
            }
        };
//هنا ليتم تنفيذ كل الذي بالاعلى
        ListNews.setAdapter(listAdapter);

    }
}
