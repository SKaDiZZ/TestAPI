package com.example.samir.testapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String POSTS_URL = "http://exclusiveapartmentssarajevo.com//wp-json/wp/v2/posts/";

    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PostsAdapter postsAdapter;

    ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Exclusive Apartments");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        postsAdapter = new PostsAdapter(MainActivity.this, posts);
        recyclerView.setAdapter(postsAdapter);

        ExclusiveAPI task = new ExclusiveAPI();
        task.execute(POSTS_URL);

    }

    public class ExclusiveAPI extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params) {

            String url = params[0];

            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder();

            Request request = builder.url(url).build();

            try {

                    Response response = client.newCall(request).execute();
                    String json = response.body().string();

                    try {

                        return new JSONArray(json);

                    } catch (JSONException e) {

                        e.printStackTrace();

                    }


            } catch (IOException e) {

                e.printStackTrace();
            }


            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            postsAdapter.notifyDataSetChanged();
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(JSONArray array) {
            super.onPostExecute(array);

            if(null == array) return;

            int length = array.length();

            for(int i = 0; i < length; i++) {

                JSONObject object = array.optJSONObject(i);

                Post post = Post.parse(object);
                posts.add(post);

            }

            onProgressUpdate();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
