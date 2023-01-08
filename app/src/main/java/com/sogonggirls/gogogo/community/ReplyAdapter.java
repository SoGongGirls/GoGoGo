package com.sogonggirls.gogogo.community;

import static com.sogonggirls.gogogo.community.PostAdapter.getTimestampToDate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sogonggirls.gogogo.R;

import java.util.List;

public class ReplyAdapter extends BaseAdapter {

    private List<ReplyItem> datas;

    public ReplyAdapter(List<ReplyItem> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.community_comment_item, parent, false);
        }

        TextView reply_nick = (TextView) convertView.findViewById(R.id.reply_nick);
        TextView reply_contents = (TextView) convertView.findViewById(R.id.reply_contents);
        TextView reply_time = (TextView) convertView.findViewById(R.id.reply_time);

        ReplyItem item = datas.get(position);

        reply_nick.setText(item.getNickname());
        reply_contents.setText(item.getContents());

//        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String to = transFormat.format(item.getDate());

        reply_time.setText(getTimestampToDate(item.getDate()));

        return convertView;
    }
}
