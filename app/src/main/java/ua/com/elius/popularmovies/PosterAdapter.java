package ua.com.elius.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class PosterAdapter<T> extends ArrayAdapter<T> {

    private int mResource;
    private int mFieldId = 0;

    public PosterAdapter(Context context, int resource, int viewResourceId) {
        super(context, resource, viewResourceId);
        mResource = resource;
        mFieldId = viewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ImageView image;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.poster, parent, false);
        } else {
            view = convertView;
        }

        try {
            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a ImageView
                image = (ImageView) view;
            } else {
                //  Otherwise, find the TextView field within the layout
                image = (ImageView) view.findViewById(mFieldId);
            }
        } catch (ClassCastException e) {
            Log.e("PosterAdapter", "You must supply a resource ID for a ImageView");
            throw new IllegalStateException(
                    "PosterAdapter requires the resource ID to be a ImageView", e);
        }

        T item = getItem(position);
        image.setImageResource(Integer.parseInt(item.toString()));

        return view;
    }
}
