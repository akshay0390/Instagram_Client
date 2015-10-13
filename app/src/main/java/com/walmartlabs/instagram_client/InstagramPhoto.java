package com.walmartlabs.instagram_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class InstagramPhoto extends AppCompatActivity {

    private ListView pictureComments;
    private ArrayList<PhotoComment> photoComments;
    private PhotoCommentAdapter commentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_photo);

        pictureComments = (ListView) findViewById(R.id.lv_userComments);

        photoComments = new ArrayList<>();
        commentAdapter = new PhotoCommentAdapter(this,photoComments);
        pictureComments.setAdapter(commentAdapter);
        Photo photo = (Photo) getIntent().getSerializableExtra("photo");
        fetchPhotoComments(photo.getId());
    }

    private void fetchPhotoComments(String id){

        String url = "https://api.instagram.com/v1/media/"+id+"/comments?client_id=92ff8a8947c8462781476c08b2036d1d";
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(url,null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try{
                    JSONArray commentsList = response.getJSONArray("data");
                    for(int i=0;i<commentsList.length();i++){
                        JSONObject data = (JSONObject)commentsList.get(i);
                        String commenterUserName = data.getJSONObject("from").getString("username");
                        String commentText = data.getString("text");

                        PhotoComment photoComment = new PhotoComment();
                        photoComment.setCommenterUserName(commenterUserName);
                        photoComment.setCommentText(commentText);

                        photoComments.add(photoComment);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                commentAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram_photo, menu);
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
