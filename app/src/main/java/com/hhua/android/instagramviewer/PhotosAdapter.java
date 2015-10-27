package com.hhua.android.instagramviewer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

/**
 * Created by ahua on 10/25/15.
 */
public class PhotosAdapter extends ArrayAdapter<Photo> {
    public PhotosAdapter(Context context, List<Photo> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    // What our item looks like
    // Use the template to display each photo

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get date item for this position
        Photo photo = getItem(position);

        // Check if we using a recycled view, otherwise we need to inflate
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        // Look up the views for populating the data
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvCreatedTime = (TextView) convertView.findViewById(R.id.tvCreatedTime);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView tvLikesCount = (TextView) convertView.findViewById(R.id.tvLikeCounts);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ImageView ivProfile = (ImageView) convertView.findViewById(R.id.ivProfile);

        // Insert the model data into each view items
        tvUsername.setText(photo.username);
        tvCaption.setText(photo.caption);
        PrettyTime prettyTime = new PrettyTime();
        tvCreatedTime.setText(prettyTime.format(new Date(System.currentTimeMillis() - photo.createdTime)));
        tvLikesCount.setText(photo.likesCount + " likes");

        // Clear out the image view
        ivPhoto.setImageDrawable(null);
        ivProfile.setImageDrawable(null);

        // Insert the image view with Picasso
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(0)
                .cornerRadiusDp(50)
                .oval(false)
                .build();
        Picasso.with(getContext()).load(photo.profilePictureUrl).fit().transform(transformation).into(ivProfile);
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);

        // Return the created item as a view
        return convertView;
    }
}
