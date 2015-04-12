/*
/
/参考サイト
/http://qiita.com/nein37/items/c9ae7c8e3489b1276c14
/
 */

package com.kawakawaplanning.pullrefreshtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.red,
                R.color.green,
                R.color.blue,
                R.color.orange);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        mListView = (ListView)findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Uri uri = Uri.parse("https://github.com/KawakawaRitsuki/PullRefresh");
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新が終了したらインジケータ非表示
                adapter.add("タップでソースへ");
                mListView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}