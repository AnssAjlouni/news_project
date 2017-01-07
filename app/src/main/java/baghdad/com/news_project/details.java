package baghdad.com.news_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
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
import java.util.Map;

public class details extends AppCompatActivity {
    //هنا قمنا بتعريف النص للعنوان ونص لتفاصيل الخبر وصورة الخبر
    private TextView title,details;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //هنا اعطينا عنوان الشاشة لتكون تفاصيل الخبر
        setTitle("تفاصيل الخبر");
        //هنا للربط مع نص عنوان الخبر وتفاصيل الخبر وصورة الخبر
        title=(TextView)findViewById(R.id.title_news);
        details =(TextView)findViewById(R.id.details_news);
        img=(ImageView)findViewById(R.id.image_news_details);
        //هنا قمنا بارسال طلب للسيرفر ليرجع لنا json الخاصة بتفاصيل الخبر
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  TvName.setText(response);

                try {
                    //هنا لنتمكن من الوصول الى البيانات الذي رجعت لنا
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //هنا سيقوم باعطاء عنوان الخبر لنص title وسيعطي تفاصيل الخبر لنص details وسيعطي الصورة الى img
                        title.setText(""+jsonObject.getString("news_title"));
                        details.setText(""+jsonObject.getString("news_text"));
                        Picasso.with(getBaseContext()).load(AppConfig.URL_SERVER + "img/" + jsonObject.getString("news_img")).into(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "ERROR: " + error.toString(), Toast.LENGTH_LONG).show();
                //هنا بحال حصلت مشكلة بالاتصال مع السيرفر
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //هنا لنقوم باستقبال id الذي ارسلناه من الشاشة الاولى وخزناه متغير نصي باسم id
                Bundle values = getIntent().getExtras();
                String id = values.getString("id");
                //هنا نرسل id للسيرفر لمتغير اسمه id وبالسيرفر سيتعامل معه ويجلب لنا بيانات json حسب id الذي سنرسله
                params.put("id", id);
                return params;
            }
        };
        //هنا لتشغيل الكود السابق
        queue.add(stringRequest);
    }
}
