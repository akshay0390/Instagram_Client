package com.walmartlabs.instagram_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by akulka2 on 10/9/15.
 */
public class PhotoItemAdapter extends ArrayAdapter<Photo> {

    public PhotoItemAdapter(Context context, List<Photo> objects) {

        super(context, android.R.layout.simple_list_item_1, objects);
    }

    private static class ViewHolder{
        TextView userName;
        TextView caption;
        ImageView imageView;
        RoundedImageView profileImage;
        TextView likes;
        TextView relativeTime;
        TextView totalComments;
        TextView lastComment;
        TextView secondLastComment;
        TextView lastCommenterName;
        TextView secondLastCommenterName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Photo photo = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_item,parent,false);

            viewHolder.userName = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tv_caption);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_photo);
            viewHolder.profileImage = (RoundedImageView) convertView.findViewById(R.id.iv_profile);
            viewHolder.likes =  (TextView) convertView.findViewById(R.id.tv_likes);
            viewHolder.relativeTime = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.lastComment = (TextView) convertView.findViewById(R.id.tv_lastComment);
            viewHolder.secondLastComment = (TextView) convertView.findViewById(R.id.tv_secondLastComment);
            viewHolder.lastCommenterName = (TextView) convertView.findViewById(R.id.tv_lastCommenterName);
            viewHolder.secondLastCommenterName = (TextView) convertView.findViewById(R.id.tv_secondLastCommenterName);
            viewHolder.totalComments = (TextView) convertView.findViewById(R.id.tv_totalComments);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        viewHolder.userName.setText(photo.getUserName());
        viewHolder.caption.setText(photo.getCaption());
        String heartText = getContext().getResources().getString(R.string.heart_label);
        viewHolder.likes.setText(heartText+" "+photo.getLikes()+" likes");
        viewHolder.totalComments.setText(photo.getCommentsCount()+" Comment");
        viewHolder.relativeTime.setText(photo.getTime());
        viewHolder.lastComment.setText(photo.getLastComment());
        viewHolder.secondLastComment.setText(photo.getSecondLastComment());
        viewHolder.lastCommenterName.setText(photo.getLastCommenterName()+":");
        viewHolder.secondLastCommenterName.setText(photo.getSecondLastCommenterName()+":");
        viewHolder.imageView.setImageResource(0);
        Picasso.with(getContext()).load(photo.getUrl()).into(viewHolder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        Picasso.with(getContext()).load(photo.getProfilePictureUrl()).into(viewHolder.profileImage);

        return convertView;
    }
}
