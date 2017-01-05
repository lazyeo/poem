package cn.studyjamscn.s1.sj124.zhangshun1;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    MyApplication instance = MyApplication.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //        创建并设置字体
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/STXingkai.ttf");

        TextView chuci = (TextView)findViewById(R.id.chuCi);
        TextView hanfu = (TextView)findViewById(R.id.hanFu);
        TextView tangshi = (TextView)findViewById(R.id.tangShi);
        TextView songci = (TextView)findViewById(R.id.songCi);
        TextView yuanqu = (TextView)findViewById(R.id.yuanQu);

        chuci.setTypeface(face);
        hanfu.setTypeface(face);
        tangshi.setTypeface(face);
        songci.setTypeface(face);
        yuanqu.setTypeface(face);


    }

    public void toCard(View view) {
        Intent intent = new Intent(ListActivity.this, CardListActivity.class);

        if (view.getId() == R.id.chuciGroup) {
            instance.categoryIndex = 0;
        } else if (view.getId() == R.id.hanFuGroup) {
            instance.categoryIndex = 1;
        } else if (view.getId() == R.id.tangShiGroup) {
            instance.categoryIndex = 2;
        } else if (view.getId() == R.id.songCiGroup) {
            instance.categoryIndex = 3;
        } else if (view.getId() == R.id.yuanQuGroup) {
            instance.categoryIndex = 4;
        }

        startActivity(intent);
    }


}