package baghdad.com.news_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class news_type extends AppCompatActivity {
    private ListView news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_type);
        //هنا تم ارسال من الشاشة الاولى اسم section حتى نعرضه بالعنوان كما هو التالي
        Bundle values = getIntent().getExtras();
        String s = values.getString("section_name");
        setTitle(s);
        news=(ListView)findViewById(R.id.list_view_news_type);
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_NEWS_TYPE,
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
                Toast.makeText(getBaseContext(), " error : " + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //مكان ارسال القيم للويب
                //سيتم ارسال قيمة رقم section_id حتى نقدر نجيب البيانات من السيرفر بناء عليها
                //وقمنا بجلب رقم الsection من الشاشة الاولى وارسلناه الى هذه الشاشة
                Bundle values = getIntent().getExtras();
                String s = values.getString("section_id");
                params.put("section_id", s);
                return params;
            }
        };
        // هنا لتشغيل الطلب الذي سيتم ارساله للسيرفر
        queue.add(stringRequest);
    }
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
                final String[] entry = DataList.get(position);
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
                //في هذا المكان قمنا بتعريف الزر شاهد الخبر كامل حتى نقوم باستخدامه
                Button details_btn =(Button)view.findViewById(R.id.details_btn);
                //هنا حدث النقر على الزر
                details_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //هنا سيذهب الى شاشة اسمها details وسياخذ معه متغير باسم id وسيحمل قيمة id الخاص بالخبر بقاعدة البيانات
                        Intent i = new Intent(getBaseContext(),details.class);
                        i.putExtra("id",entry[2]);
                        startActivity(i);
                    }
                });
                return view;
            }
        };
//هنا ليتم تنفيذ كل الذي بالاعلى
        news.setAdapter(listAdapter);

    }
}
