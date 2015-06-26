package ua.com.elius.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PosterAdapter<T> extends ArrayAdapter<T> {

    private final String LOG_TAG = PosterAdapter.class.getSimpleName();

    private int mResource;
    private int mFieldId = 0;
    private Movies mMovies;

    public PosterAdapter(Context context, int resource) {
        super(context, resource);
        mResource = resource;
    }

    public PosterAdapter(Context context, int resource, int viewResourceId) {
        super(context, resource, viewResourceId);
        mResource = resource;
        mFieldId = viewResourceId;
    }

    public void setMovies(Movies movies) {
        this.mMovies = movies;
    }

    public Movie getMovie(int position) {
        return mMovies.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ImageView image;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(mResource, parent, false);
            try {
                ImageHolder holder = new ImageHolder();
                if (mFieldId == 0) {
                    //  If no custom field is assigned, assume the whole resource is a ImageView
                    holder.image = (ImageView) view;
                } else {
                    //  Otherwise, find the TextView field within the layout
                    holder.image = (ImageView) view.findViewById(mFieldId);
                }
                view.setTag(holder);
            } catch (ClassCastException e) {
                Log.e("PosterAdapter", "You must supply a resource ID for an ImageView");
                throw new IllegalStateException(
                        "PosterAdapter requires the resource ID to be an ImageView", e);
            }
        } else {
            view = convertView;
        }

        image = ((ImageHolder)view.getTag()).image;
        T item = getItem(position);
        if (item instanceof Integer) {
            image.setImageResource((Integer) item);
        } else {
            Glide.with(getContext())
                    .load(item.toString())
                    .into(image);
        }

        return view;
    }

    static class ImageHolder {
        ImageView image;
    }

}
