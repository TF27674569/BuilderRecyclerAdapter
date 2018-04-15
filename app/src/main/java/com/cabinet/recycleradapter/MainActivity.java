package com.cabinet.recycleradapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.recycler.base.Binder;
import org.recycler.EAdapter;
import org.recycler.ViewHolder;
import org.recycler.base.ImageLoader;
import org.recycler.base.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EAdapter.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, ImageView imageView, String url) {
                // 此处是你使用的库文件
            }
        });


        List<String> strings = new ArrayList<>();

//       EAdapter.Builder.load(strings)
//                .item(R.layout.activity_main)
//                .bind()
//                .onItemClick()
//                .onItemLongClick()
//                .item(View)
//                .manager()
//                .support()
//                .into();

    }
}
