package baghdad.com.news_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class section extends AppCompatActivity {
    //هنا قمنا بتعرف ListView باسم list_view_section
    ListView list_view_section;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        //عنوان الشاشة
        setTitle("نوع الخبر");
        //هنا قمنا بربط listview مع listview الخاص بالشاشة
        list_view_section=(ListView)findViewById(R.id.list_view_section);
        //هنا قمنا بعمل طلب على السيرفر ولم نرسل له شئ لانه لا نريد ان نرسل شئ معين نريد ان يعرض لنا جميع الاقسام الاخبار
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_SECTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
                //هنا القيمة الراجعة من السيرفر سيتم ارسالها الى دالة readData
                try {
                    readData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "ERROR: " + error.toString(), Toast.LENGTH_LONG).show();
                //هنا سيتم تنفيذه بحال حصلت مشكلة مع السيرفر
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //هنا مكان ارسال القيم ولا نريد حاليا ارسال قيم
                return params;
            }
        };
        // هنا تنفيذ الكود السابق للارسال للسيرفر
        queue.add(stringRequest);
    }
    public void readData(String responce) throws JSONException {
        final List<String[]> DataList = new LinkedList<String[]>();
        JSONArray data = new JSONArray(responce);
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            //هنا سيتم استقبال البيانات حسب قاعدة البيانات بالاسماء التالية وتخزينها بالمتغيرات التالية
            String SectionName = object.getString("section_name");
            String SectionIcon = object.getString("section_icon");
            String SectionID = object.getString("section_id");
            //Toast.makeText(HistoryOrderCustomer.this, OrderId + "-" + OrderDate, Toast.LENGTH_SHORT).show();
            //هنا يتم تخزين داخل datalist كل البيانات
            DataList.add(new String[]{
                    SectionName,
                    SectionIcon,
                    SectionID
            });
        }// end for
        ListAdapter listAdapter = new ArrayAdapter<String[]>(
                getBaseContext(),
                //هنا قمنا بالربط مع شاشة باسم item_section
                R.layout.item_section,
                R.id.section_text,
                DataList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Must always return just a View.
                View view = super.getView(position, convertView, parent);
                String[] entry = DataList.get(position);
                //هنا الربط مع النص والصورة الذي داخل item_section
                TextView text1 = (TextView) view.findViewById(R.id.section_text);
                ImageView image_section = (ImageView) view.findViewById(R.id.section_img);
                //هنا ارسال القيمة الاولى حسب المصفوفة الى النص
                text1.setText(entry[0]);
                if (entry[1].length() > 0) {
                    //هنا ارسال القيمة التانية والذي تحمل قيمة اسم صورة بقاعدة بيانات ويتم عرضها داخل الصورة حسب الكود التالي
                    Picasso.with(getBaseContext()).load(AppConfig.URL_SERVER + "img/" + entry[1]).into(image_section);
                }
                return view;
            }
        };
        //هنا تنفيذ الكود adapter
        list_view_section.setAdapter(listAdapter);
    }
}