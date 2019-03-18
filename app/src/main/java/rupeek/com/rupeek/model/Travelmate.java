package rupeek.com.rupeek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Travelmate {

    private String cust_name;

    private List<Locations> locations;

    public Travelmate(String cust_name, List<Locations> locations) {
        this.cust_name = cust_name;
        this.locations = locations;
    }

    public Travelmate() {
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public List<Locations> getLocations() {

        if (locations == null)
            return new ArrayList<>();
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }

}
