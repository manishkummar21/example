package rupeek.com.rupeek.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationEntity {

    @Id(autoincrement = true)
    private Long id;

    @Index
    private Long customerid;
    private String place;
    private String url;
    private String date;
    private int rate;
    private String description;

    @Generated(hash = 142185321)
    public LocationEntity(Long id, Long customerid, String place, String url,
            String date, int rate, String description) {
        this.id = id;
        this.customerid = customerid;
        this.place = place;
        this.url = url;
        this.date = date;
        this.rate = rate;
        this.description = description;
    }

    @Generated(hash = 1723987110)
    public LocationEntity() {
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
