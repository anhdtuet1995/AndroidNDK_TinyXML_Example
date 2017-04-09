package com.uet.anhdt.newspapertinyxml;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private final String URL_XML = "http://vnexpress.net/rss/the-thao.rss";
    private ListView listView;
    private TestAdapter adapter;
    private ArrayList<Article> articleArrayList;
    private GetNewspaperTask getNewspaperTask;
    private RelativeLayout relativeProgress;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAdapter();

        listView = (ListView) findViewById(R.id.listView);
        relativeProgress = (RelativeLayout) findViewById(R.id.relativeProgress);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra(Constant.PAGE_URL, articleArrayList.get(position).getLink());
                startActivity(intent);
            }
        });

        getNewspaperTask = new GetNewspaperTask();

        swipeRefreshLayout.setOnRefreshListener(this);

        getNewspaperTask.execute();
    }

    private void initAdapter() {
        articleArrayList = new ArrayList<>();
        adapter = new TestAdapter(this, articleArrayList);
    }

    private ArrayList<Article> getArticleArrayList(Article[] articles) {
        ArrayList<Article> articles1 = new ArrayList<>();
        for (int i = 0; i < articles.length; i++) {

                articles1.add(articles[i]);


        }
        return articles1;
    }

    public String getXmlFromUrl() {
        String stringXML = null;

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL_XML);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            stringXML = EntityUtils.toString(httpEntity);

        }catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
        return stringXML;
    }

    @Override
    public void onRefresh() {
        //if (!getNewspaperTask.isCancelled()) {
        new GetNewspaperTask().execute();
        swipeRefreshLayout.setRefreshing(false);
    }


    private class GetNewspaperTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            relativeProgress.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            String xml = getXmlFromUrl();
            Article[] articles = getArticleTest(xml);
            articleArrayList.clear();
            articleArrayList.addAll(getArticleArrayList(articles));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            relativeProgress.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    protected void onDestroy() {
        getNewspaperTask.cancel(true);
        super.onDestroy();
    }

    public native Article[] getArticleTest(String xml);

    static {
        System.loadLibrary("native-lib");
    }
}
