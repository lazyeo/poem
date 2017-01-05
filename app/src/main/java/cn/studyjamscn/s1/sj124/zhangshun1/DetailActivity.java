package cn.studyjamscn.s1.sj124.zhangshun1;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    MyApplication instance = MyApplication.getInstance();

    private HorizontalScrollView sv;
    private TextViewVertical tv;
    String content;
    String author;
    float textSize = 80;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //根据index设置string内容


        try {
            instance.originalData = Utils.getDataFromAsset(this, "data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject test = Utils.getDataForIndex(instance.originalData, instance.categoryIndex);
            JSONArray contentArray = test.getJSONArray("example");
            JSONObject contentX = Utils.getDataForIndex(contentArray,instance.contentIndex);
            content = contentX.getString("content");
            author = contentX.getString("author");
//            Log.e("测试内容",content);
//            Log.e("测试作者",author);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        tv = (TextViewVertical) findViewById(R.id.theContent);
        sv = (HorizontalScrollView) findViewById(R.id.VerOutline);


        //设置接口事件接收
        Handler handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case TextViewVertical.LAYOUT_CHANGED:
                        sv.scrollBy(tv.getTextWidth(), 0);//滚动到最右边
                        break;
                }
            }
        };
        tv.setHandler(handler);//将Handler绑定到TextViewVertical

//        创建并设置字体
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/STXingkai.ttf");
        tv.setTypeface(face);

        //设置左下角签名字体
        TextView sign = (TextView)findViewById(R.id.sign);
        sign.setTypeface(face);
        sign.setText(author);




        //设置诗歌文字内容
        tv.setText(content);
        tv.setTextSize(textSize);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;

    }
//字体大小设置按钮
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.fontBig:
//
//                if (textSize <= 100){
//                    textSize = textSize + 5;
//                    tv.setTextSize(textSize);
//                }else {
//                    Toast.makeText(this, "字号已至最大", Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            case R.id.fontSmall:
//                if (textSize >=50){
//                    textSize = textSize - 5;
//                    tv.setTextSize(textSize);
//                }else {
//                    Toast.makeText(this, "字号已至最小", Toast.LENGTH_SHORT).show();
//                }
//                return true;
////            case R.id.action_share:
////                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
////                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
