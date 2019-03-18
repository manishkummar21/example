package rupeek.com.rupeek.model;


import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import rupeek.com.rupeek.R;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Locations implements Serializable {

    private String place;
    private String url;
    private String date;
    private int rate;
    private String description;

    public Locations() {
    }

    public Locations(String place, String url, String date, int rate, String description) {
        this.place = place;
        this.url = url;
        this.date = date;
        this.rate = rate;
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().placeholder(R.drawable.default_banner))
                .apply(new RequestOptions().error(R.drawable.default_banner))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(view);
    }
}
