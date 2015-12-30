package assignmentapp.sample.com.mysampleapp.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import assignmentapp.sample.com.mysampleapp.Utils.ConstantTags;
import assignmentapp.sample.com.mysampleapp.models.CategoryModel;
import assignmentapp.sample.com.mysampleapp.models.CouponDuniaBrandModel;

/**
 * Json-Parser.
 */
public class JSONParser implements IJSONParser {
    List<CouponDuniaBrandModel> couponDuniaBrandModels;

    @Override
    public List<CouponDuniaBrandModel> getParsedData(String data) throws JSONException {

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);
        JSONObject statusObj = jObj.getJSONObject(ConstantTags.STATUS_TAG);

        int code = statusObj.getInt(ConstantTags.STATUS_RCODE_TAG);
        String message = statusObj.getString(ConstantTags.STATUS_MESSAGE_TAG);
        // Check code, 200 represents the okay data. Otherwise represents error data.
        if (code != 200 || !message.equalsIgnoreCase("OK")) {
            return null;
        }

        couponDuniaBrandModels = new ArrayList<CouponDuniaBrandModel>();
        JSONArray dataArray = jObj.getJSONArray(ConstantTags.DATA_TAG);
        for (int i = 0; i < dataArray.length(); i++) {
            CouponDuniaBrandModel couponDuniaBrandModel = new CouponDuniaBrandModel();
            JSONObject dataObject = dataArray.getJSONObject(i);
            couponDuniaBrandModel.setFranchiseId(dataObject.getInt(ConstantTags.DATA_SUB_FRANCHISE_ID_TAG));
            couponDuniaBrandModel.setSubFranchiseID(dataObject.getInt(ConstantTags.DATA_SUB_FRANCHISE_ID_TAG));
            couponDuniaBrandModel.setOutletName(dataObject.getString(ConstantTags.DATA_OUTLET_NAME_TAG));
            couponDuniaBrandModel.setNeighbourhoodName(dataObject.getString(ConstantTags.DATA_NEIGHBOURHOOD_NAME_TAG));
            couponDuniaBrandModel.setPhoneNumber(dataObject.getString(ConstantTags.DATA_PHONE_NUMBER_TAG));
            couponDuniaBrandModel.setCityName(dataObject.getString(ConstantTags.DATA_CITY_NAME_TAG));
            couponDuniaBrandModel.setLogoUrl(dataObject.getString(ConstantTags.DATA_LOGO_URL_TAG));
            couponDuniaBrandModel.setLat(dataObject.getDouble(ConstantTags.DATA_LAT_TAG));
            couponDuniaBrandModel.setLong(dataObject.getDouble(ConstantTags.DATA_LONG_TAG));
            couponDuniaBrandModel.setAddress(dataObject.getString(ConstantTags.DATA_ADDRESS_TAG));

            JSONArray categoryArray = dataObject.getJSONArray(ConstantTags.DATA_CATEGORIES_TAG);
            List<CategoryModel> categoryModels = new ArrayList<>();
            for (int j = 0; j < categoryArray.length(); j++) {
                JSONObject categoryObj = categoryArray.getJSONObject(j);
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setOfflineCategoryID(categoryObj.getInt(ConstantTags.CATEGORY_OFFLINE_CAT_ID_TAG));
                categoryModel.setName(categoryObj.getString(ConstantTags.CATEGORY_CAT_NAME_TAG));
                categoryModels.add(categoryModel);
            }
            couponDuniaBrandModel.setCategoryModelList(categoryModels);
            couponDuniaBrandModels.add(couponDuniaBrandModel);
        }

        return couponDuniaBrandModels;
    }
}
