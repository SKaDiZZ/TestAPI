package com.example.samir.testapi;

import android.text.Html;
import android.text.Spanned;

import org.json.JSONException;
import org.json.JSONObject;

public class Post {

    public String title;
    public String content;
    public String thumbnail;

    public static Post parse(JSONObject object) {

        try {

            Post post = new Post();

            JSONObject titleObject = object.getJSONObject("title");
            post.title = titleObject.getString("rendered");

            JSONObject contentObject = object.getJSONObject("excerpt");
            Spanned content = Html.fromHtml(contentObject.getString("rendered"));
            post.content = content.toString();

            post.thumbnail = object.getString("featured_image_thumbnail_url");

            return post;


        } catch (JSONException e) {

            e.printStackTrace();

        }

        return null;

    }


}
