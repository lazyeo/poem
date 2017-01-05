package cn.studyjamscn.s1.sj124.zhangshun1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class CardListActivity extends AppCompatActivity {

    MyApplication instance = MyApplication.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        TextView intro = (TextView) findViewById(R.id.intro);

        TextView author1 = (TextView)findViewById(R.id.firstAuthor);
        TextView author2 = (TextView)findViewById(R.id.secondAuthor);
        TextView author3 = (TextView)findViewById(R.id.thirdAuthor);
        TextView title1 = (TextView)findViewById(R.id.firstTitle);
        TextView title2 = (TextView)findViewById(R.id.secondTitle);
        TextView title3 = (TextView)findViewById(R.id.thirdTitle);

        //解析json文件为json数组格式
        try {
            instance.originalData = Utils.getDataFromAsset(this, "data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //解析json数组并从中取出对应index的块
            JSONObject test = Utils.getDataForIndex(instance.originalData, instance.categoryIndex);
            //将对应的intro内容赋值给intro的textview

            intro.setText(test.getString("intro"));
            //获取示例数据的json数组
            JSONArray contentArray = test.getJSONArray("example");
            JSONObject contentX1 = Utils.getDataForIndex(contentArray,0);
            JSONObject contentX2 = Utils.getDataForIndex(contentArray,1);
            JSONObject contentX3 = Utils.getDataForIndex(contentArray,2);

            //设置作者
            author1.setText(contentX1.getString("author"));
            author2.setText(contentX2.getString("author"));
            author3.setText(contentX3.getString("author"));
            //设置诗歌名
            title1.setText(contentX1.getString("title"));
            title2.setText(contentX2.getString("title"));
            title3.setText(contentX3.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    public void toDetail(View view) {
        Intent intent = new Intent(CardListActivity.this, DetailActivity.class);
        startActivity(intent);
        if (view.equals(view.findViewById(R.id.first))) {
            instance.contentIndex = 0;
        } else if (view.equals(view.findViewById(R.id.second))) {
            instance.contentIndex = 1;
        }else if (view.equals(view.findViewById(R.id.third))) {
            instance.contentIndex = 2;
        }
        //测试用
//        Log.e(Integer.toString(view.getId()),Integer.toString(instance.contentIndex));


    }


}
