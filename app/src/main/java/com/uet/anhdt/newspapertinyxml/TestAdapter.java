package com.uet.anhdt.newspapertinyxml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anhdt on 4/9/2017.
 */

public class TestAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Article> articleArrayList;

    public TestAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articleArrayList = articles;
    }

    @Override
    public int getCount() {
        return articleArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.link = (TextView) convertView.findViewById(R.id.link);
            viewHolder.pubDate = (TextView) convertView.findViewById(R.id.pubDate);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Article article = articleArrayList.get(position);
        viewHolder.title.setText(article.getTitle());
        viewHolder.pubDate.setText(article.getPubDate());
        viewHolder.link.setText(article.getLink());
        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView link;
        TextView pubDate;
    }
}
