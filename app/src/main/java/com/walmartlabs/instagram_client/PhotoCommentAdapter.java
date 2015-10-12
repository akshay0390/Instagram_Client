package com.walmartlabs.instagram_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by akulka2 on 10/11/15.
 */
public class PhotoCommentAdapter extends ArrayAdapter<PhotoComment> {

    private TextView commenterUserName;
    private TextView commentText;

    public PhotoCommentAdapter(Context context, List<PhotoComment> objects) {
        super(context, android.R.layout.simple_list_item_1 , objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhotoComment photoComment =  getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comments_list,parent,false);
        }

        commenterUserName = (TextView) convertView.findViewById(R.id.tv_commenterUserName);
        commentText = (TextView) convertView.findViewById(R.id.tv_commenterText);

        commenterUserName.setText(photoComment.getCommenterUserName());
        commentText.setText(photoComment.getCommentText());

        return convertView;
    }
}
