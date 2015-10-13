package com.walmartlabs.instagram_client;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class InstagramFeed extends AppCompatActivity {

    private static final String CLIENT_ID_VALUE = "92ff8a8947c8462781476c08b2036d1d";
    private static final String CLIENT_ID_PARAM = "client_id";
    private static final String POPULAR_PHOTOS_API = "https://api.instagram.com/v1/media/popular?";

    private ArrayList<Photo> photos;
    private PhotoItemAdapter photoItemAdapter;
    private ListView photoListView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_feed);
        photos = new ArrayList<>();
        photoItemAdapter = new PhotoItemAdapter(this,photos);
        photoListView = (ListView) findViewById(R.id.lv_Photos);
        photoListView.setAdapter(photoItemAdapter);


        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchPopularPhotos();
            }
        });


        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        fetchPopularPhotos();

        photoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Photo clickedPhoto = (Photo) photoListView.getItemAtPosition(position);
                Intent intent = new Intent(InstagramFeed.this, InstagramPhoto.class);
                intent.putExtra("photo", clickedPhoto);
                startActivity(intent);

            }
        });
    }

    private void fetchPopularPhotos(){

        AsyncHttpClient httpClient = new AsyncHttpClient();
        String url = POPULAR_PHOTOS_API+CLIENT_ID_PARAM+"="+CLIENT_ID_VALUE;

        httpClient.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //super.onSuccess(statusCode, headers, response);
                photos.clear();
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject data = dataArray.getJSONObject(i);
                        if (data.getString("type").equals("image")) {

                            Photo photo = new Photo();

                            String userName = data.getJSONObject("user").getString("username");
                            String id = data.getString("id");
                            String caption = data.getJSONObject("caption").getString("text");
                            String url = data.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                            String profilePictureUrl = data.getJSONObject("user").getString("profile_picture");
                            int likes = data.getJSONObject("likes").getInt("count");
                            long createTime = Long.parseLong(data.getString("created_time"));
                            String relativeTime = DateUtils.getRelativeTimeSpanString(createTime*1000, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
                            JSONArray commentsArray = data.getJSONObject("comments").getJSONArray("data");
                            int totalComments = data.getJSONObject("comments").getInt("count");
                            if(commentsArray.length()>1){
                                String lastComment = commentsArray.getJSONObject(commentsArray.length()-1).getString("text");
                                String lastCommenterName = commentsArray.getJSONObject(commentsArray.length()-1).getJSONObject("from").getString("username");
                                String secondLastComment = commentsArray.getJSONObject(commentsArray.length() - 2).getString("text");
                                String secondLastCommenterName = commentsArray.getJSONObject(commentsArray.length()-2).getJSONObject("from").getString("username");
                                photo.setLastComment(lastComment);
                                photo.setLastCommenterName(lastCommenterName);
                                photo.setSecondLastCommenterName(secondLastCommenterName);
                                photo.setSecondLastComment(secondLastComment);
                            }
                            photo.setId(id);
                            photo.setCommentsCount(totalComments);
                            photo.setUserName(userName);
                            photo.setCaption(caption);
                            photo.setUrl(url);
                            photo.setProfilePictureUrl(profilePictureUrl);
                            photo.setLikes(likes);
                            photo.setTime(relativeTime);
                            photos.add(photo);
                        }
                    }
                    refreshLayout.setRefreshing(false);
                } catch (JSONException e) {
                    refreshLayout.setRefreshing(false);
                    e.printStackTrace();
                }

                photoItemAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
