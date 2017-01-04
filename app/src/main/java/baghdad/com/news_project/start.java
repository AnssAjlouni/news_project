package baghdad.com.news_project;
//كل شئ يبدا بimport هو استخدعاء لمكتبة معينة داخلا ملشروع
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        /*
        دالة onCreate هي الدالة الذي سيبدا التنفيذ بها عند فتح الشاشة الذي هي فيه

        setContentView(R.layout.activity_start);
        هذه للربط مع شاشة التصميم layout
         */
        setTitle("عنوان التطبيق");
       //دالة set title لوضع عنوان لشاشة التطبيق

        //هنا استخدام عداد يبدا من ثانية الى 3 ثواني حيث كل 1000 تمثل ثانية واحدة
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                //هنا يتم كتابة ماذا سيحص اثناء العد
                //نحن لان ريد ان نقوم بشئ معين اثناء العد
            }

            public void onFinish() {

                //هنا يتم كتابة الكود للشئ الذي سيقوم بعمله بعد عد ال3 ثواني
                //هنا سيذهب الى شاشة اسمها Home
                Intent i = new Intent(getBaseContext(), Home.class);
                startActivity(i);
                //دالة finish لانهاء الشاشة
                //قمنا بكتابة fininsh حتى بعد فتح الشاشة home يتم اغلاق الشاشة finish
                //حتى يتم ازالتها من الذاكرة
                // بحيث انه عندما نضغط على رجوع من شاشة home ما يرجع لهذه الشاشة ويطلع من التطبيق مباشرة
                finish();
            }
            //start()
            //حتى يقوم ببداية التنفيذ كود العداد
        }.start();
    }
}
