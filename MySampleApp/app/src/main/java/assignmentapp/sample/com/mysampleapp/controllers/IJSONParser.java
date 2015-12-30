package assignmentapp.sample.com.mysampleapp.controllers;

import org.json.JSONException;

import java.util.List;

import assignmentapp.sample.com.mysampleapp.models.CouponDuniaBrandModel;

/**
 * Interface for Json-parser.
 */
public interface IJSONParser {
    /**
     * Gets parsed data.
     *
     * @param data string data to parse.
     * @return weather model.
     * @throws JSONException json-exception.
     */
    List<CouponDuniaBrandModel> getParsedData(String data) throws JSONException;
}
