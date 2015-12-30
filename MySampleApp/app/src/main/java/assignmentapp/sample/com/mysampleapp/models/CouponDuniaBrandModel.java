package assignmentapp.sample.com.mysampleapp.models;

import java.util.List;

/**
 * Model for json data.
 */
public class CouponDuniaBrandModel {
    private int franchiseId;
    private int subFranchiseID;
    private String outletName;
    private String neighbourhoodName;
    private String phoneNumber;
    private String cityName;
    private String logoUrl;
    private List<CategoryModel> categoryModelList;
    private double lat;
    private double aLong;
    private double placeDistance;
    private String address;

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setSubFranchiseID(int subFranchiseID) {
        this.subFranchiseID = subFranchiseID;
    }

    public int getSubFranchiseID() {
        return subFranchiseID;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setNeighbourhoodName(String neighbourhoodName) {
        this.neighbourhoodName = neighbourhoodName;
    }

    public String getNeighbourhoodName() {
        return neighbourhoodName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    public List<CategoryModel> getCategoryModelList() {
        return categoryModelList;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLong(double aLong) {
        this.aLong = aLong;
    }

    public double getLong() {
        return aLong;
    }


    public void setPlaceDistance(double placeDistance) {
        this.placeDistance = placeDistance;
    }

    public double getPlaceDistance() {
        return placeDistance;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
