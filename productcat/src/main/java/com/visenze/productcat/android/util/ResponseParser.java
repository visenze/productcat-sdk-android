package com.visenze.productcat.android.util;

import com.visenze.productcat.android.api.impl.SearchOperationsImpl;
import com.visenze.productcat.android.model.Facet;
import com.visenze.productcat.android.model.FacetItem;
import com.visenze.productcat.android.model.FacetRange;
import com.visenze.productcat.android.model.ObjectList;
import com.visenze.productcat.android.model.ResultList;
import com.visenze.productcat.android.ProductCatException;
import com.visenze.productcat.android.model.Box;
import com.visenze.productcat.android.model.ProductSummary;
import com.visenze.productcat.android.model.ProductType;
import com.visenze.productcat.android.model.SponsorContent;
import com.visenze.productcat.android.model.SponsorContentImg;
import com.visenze.productcat.android.model.Store;
import com.visenze.productcat.android.model.StoreResultList;
import com.visenze.productcat.android.model.Tag;
import com.visenze.productcat.android.model.TagGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Parses ProductCat Search API JSON responses.
 */
public class ResponseParser {

    public static final String REQID = "reqid";
    public static final String RECOGNIZE_RESULT = "recognize_result";
    public static final String RESULT = "result";
    public static final String PRODUCT_TYPES = "product_types";
    public static final String IM_ID = "im_id";
    public static final String FACETS = "facets";
    public static final String CLIENT_REQID = "client_reqid";
    public static final String COUNTRY_FILTER = "country_filter";
    public static final String KEY = "key";
    public static final String ITEMS = "items";
    public static final String RANGE = "range";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public static final String ID = "id";
    public static final String COUNT = "count";
    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String TAG_GROUP = "tag_group";
    public static final String TAGS = "tags";
    public static final String BLANK = "";
    public static final String TAG_ID = "tag_id";
    public static final String TAG = "tag";
    public static final String SCORE = "score";
    public static final String PGID = "pgid";
    public static final String PID = "pid";
    public static final String MAIN_IMAGE = "main_image";
    public static final String IMAGES = "images";
    public static final String TITLE = "title";
    public static final String DESC = "desc";
    public static final String COUNTRY = "country";
    public static final String MIN_PRICE = "min_price";
    public static final String MAX_PRICE = "max_price";
    public static final String PRICE_UNIT = "price_unit";
    public static final String ORIGINAL_PRICE_UNIT = "original_price_unit";
    public static final String ORIGINAL_MIN_PRICE = "original_min_price";
    public static final String ORIGINAL_MAX_PRICE = "original_max_price";
    public static final String AVAILABILITY = "availability";
    public static final String PRODUCT_URL = "product_url";
    public static final String PRICE = "price";
    public static final String SELLER_NAME = "seller_name";
    public static final String VISUAL_SCORE = "visual_score";
    public static final String STORES = "stores";
    public static final String STORE_ID = "store_id";
    public static final String TYPE = "type";
    public static final String BOX = "box";
    public static final String ATTRIBUTES = "attributes";
    public static final String STATUS = "status";
    public static final String OK = "OK";
    public static final String ERROR = "error";
    public static final String SPONSORED_CONTENT = "sponsored_content";
    public static final String CONTENT_TYPE = "content_type";
    public static final String TRACKER_URL = "tracker_url";
    public static final String LINK = "link";
    public static final String PLACEMENT = "placement";
    public static final String PLACEMENT_POSITION = "placement_position";
    public static final String ORG_SEARCH_RESULT_POSITION = "org_search_result_position";
    public static final String URL = "url";
    public static final String H = "h";
    public static final String W = "w";
    public static final String BOOSTED = "boosted";
    public static final String TRUE = "true";
    public static final String BRAND = "brand";
    public static final String BRAND_ID = "brand_id";
    public static final String SRP_URL = "srp_url";
    public static final String PRICE_SYMBOL = "price_symbol";
    public static final String MIN_O_PRICE = "min_o_price";
    public static final String MAX_O_PRICE = "max_o_price";
    public static final String ORIGINAL_MIN_O_PRICE = "original_min_o_price";
    public static final String ORIGINAL_MAX_O_PRICE = "original_max_o_price";
    public static final String ATTRS = "attrs";
    public static final String OPTIN = "opt_in";
    public static final String BAD_QUERY = "bad_query";

    public static final String OBJECTS = "objects";

    public static ResultList parseResult(String jsonResponse, String type) {
        if(SearchOperationsImpl.PRIVACY_STATUS.equals(type)) {
            try {
                ResultList resultList = new ResultList();
                JSONObject resultObj = new JSONObject(jsonResponse);

                if(resultObj.has(RESULT)) {
                    JSONObject result = resultObj.getJSONObject(RESULT);
                    resultList.setOptIn(result.getBoolean(OPTIN));
                }
                return resultList;
            } catch (JSONException e) {
                throw new ProductCatException("Error parsing response " + e.getMessage(), e);
            }
        } else {
            return parseResult(jsonResponse);
        }
    }

    public static List<ObjectList> parseMPSResponse(JSONObject jsonObject) {
        List<ObjectList> objectList = new ArrayList<ObjectList>();
        try {
            if (jsonObject.has(OBJECTS)) {
                JSONArray objArray = jsonObject.getJSONArray(OBJECTS);
                int size = objArray.length();
                for (int i = 0; i < size; i++) {

                    ObjectList obj = new ObjectList();
                    if (jsonObject.has(REQID)) {
                        obj.setReqid(jsonObject.getString(REQID));
                    }

                    JSONObject jsonItem = objArray.getJSONObject(i);

                    if (jsonItem.has(TYPE)) {
                        obj.setType((jsonItem.getString(TYPE)));
                    }

                    if (jsonItem.has(BOX)) {

                        JSONArray boxCoordinate = jsonItem.getJSONArray(BOX);

                        if (boxCoordinate.length() >= 4) {
                            Box box = new Box(boxCoordinate.getInt(0),
                                    boxCoordinate.getInt(1),
                                    boxCoordinate.getInt(2),
                                    boxCoordinate.getInt(3));

                            obj.setBox(box);
                        }
                    }

                    if (jsonItem.has(SCORE)) {
                        obj.setScore(jsonItem.getDouble(SCORE));
                    }

                    if (jsonItem.has(ATTRIBUTES)) {
                        JSONObject attrsArray = jsonItem.getJSONObject(ATTRIBUTES);
                        JSONArray attrsNames = attrsArray.names();
                        Map attrsMapList = new HashMap<>();
                        for (int j = 0; attrsNames != null && j < attrsNames.length(); j++) {
                            JSONArray attrsItems = (JSONArray) attrsArray.get((String)attrsNames.get(j));
                            List<String> attrs = new ArrayList<>();
                            for (int k = 0; k < attrsItems.length(); k++) {
                                attrs.add((String) attrsItems.get(k));
                            }
                            attrsMapList.put(attrsNames.get(j), attrs);
                        }
                        obj.setAttributeList(attrsMapList);
                    }

                    if (jsonItem.has(RESULT)) {
                        JSONArray resultArray = jsonItem.getJSONArray(RESULT);
                        obj.setProductSummaryList(parseProductSummaryList(resultArray));
                    }

                    objectList.add(obj);
                }

            }


            return objectList;
        } catch (JSONException e) {
            throw new ProductCatException("Error parsing response " + e.getMessage(), e);
        }

    }

    public static ResultList parseResult(String jsonResponse) {

        try {
            ResultList resultList = new ResultList();

            JSONObject resultObj = new JSONObject(jsonResponse);
            resultList.setErrorMessage(parseResponseError(resultObj));

            if (resultObj.has(REQID)) {
                resultList.setReqid(resultObj.getString(REQID));
            }

            if (resultObj.has(CLIENT_REQID)) {
                resultList.setClientReqId(resultObj.getString(CLIENT_REQID));
            }

            if (resultObj.has(RECOGNIZE_RESULT)) {
                JSONArray tagGroupArray = resultObj.getJSONArray(RECOGNIZE_RESULT) ;
                resultList.setTagGroups(parseTagGroups(tagGroupArray));
            }

            if (resultObj.has(RESULT)) {
                JSONArray resultArray = resultObj.getJSONArray(RESULT);
                resultList.setProductSummaryList(parseProductSummaryList(resultArray));
            }

            if(resultObj.has(SPONSORED_CONTENT)) {
                JSONArray contentArray = resultObj.getJSONArray(SPONSORED_CONTENT);
                resultList.setSponsorContents(parseSponsorContentList(contentArray));
            }

            if (resultObj.has(PRODUCT_TYPES)) {
                JSONArray productTypeArray = resultObj.getJSONArray(PRODUCT_TYPES);
                resultList.setProductTypes(parseProductTypeList(productTypeArray));
            }

            if (resultObj.has(IM_ID)) {
                resultList.setImId(resultObj.getString(IM_ID));
            }

            if (resultObj.has(FACETS)){
                JSONArray facetArray = resultObj.optJSONArray(FACETS);
                resultList.setFacets(parseFacets(facetArray));
            }

            if (resultObj.has(COUNTRY_FILTER)) {
                resultList.setCountryFilter(resultObj.getString(COUNTRY_FILTER));
            }

            if (resultObj.has(SRP_URL)) {
                resultList.setSrpUrl(resultObj.getString(SRP_URL));
            }

            if (resultObj.has(BAD_QUERY)) {
                resultList.setBadQuery( TRUE.equals(resultObj.getString(BAD_QUERY).toLowerCase()) );
            }

            return resultList;
        } catch (JSONException e) {
            throw new ProductCatException("Error parsing response " + e.getMessage(), e);
        }
    }

    private static List<Facet> parseFacets(JSONArray arr) {
        if( arr == null) {
            return null;
        }

        List<Facet> facets = new ArrayList<>();

        for(int i = 0, length = arr.length() ; i < length ; i++){
            JSONObject o = arr.optJSONObject(i);

            Facet facet = new Facet();

            facet.setKey(o.optString(KEY));

            // parse facet items
            JSONArray items = o.optJSONArray(ITEMS);
            if (items != null) {
                List<FacetItem> facetItems = getFacetItems(items);
                facet.setItems(facetItems);
            }

            // parse range
            JSONObject rangeObj = o.optJSONObject(RANGE);

            // set min and max
            if (rangeObj!=null) {

                FacetRange range = new FacetRange();

                Object min = rangeObj.opt(MIN);

                if (min!=null && min instanceof Number) {
                    range.setMin((Number) min);
                }

                Object max = rangeObj.opt(MAX);
                if (max!=null && max instanceof Number) {
                    range.setMax((Number) max);
                }

                facet.setRange(range);
            }

            facets.add(facet);

        }
        return facets;
    }

    private static List<FacetItem> getFacetItems(JSONArray items) {
        int itemLength = items.length();
        List<FacetItem> facetItems = new ArrayList<>();

        for (int itemIndex = 0 ; itemIndex < itemLength ; itemIndex++) {
            JSONObject itemObj = items.optJSONObject(itemIndex);
            if (itemObj == null) {
                continue;
            }

            FacetItem facetItem = new FacetItem();
            facetItem.setId(itemObj.optLong(ID));
            facetItem.setCount(itemObj.optInt(COUNT));
            facetItem.setName(itemObj.optString(NAME));
            facetItem.setValue(itemObj.optString(VALUE));
            facetItems.add(facetItem);
        }
        return facetItems;
    }

    private static List<TagGroup> parseTagGroups(JSONArray arr){
        List<TagGroup> groups = new ArrayList<TagGroup>();

        for(int i = 0, length = arr.length() ; i < length ; i++){
            JSONObject o = arr.optJSONObject(i);

            if(o!=null){
                TagGroup group = new TagGroup();
                group.tagGroup = o.optString(TAG_GROUP, BLANK);
                JSONArray tagsArr = o.optJSONArray(TAGS);
                if(group.tagGroup != null && tagsArr!=null){
                    for(int j = 0 , taglength = tagsArr.length(); j < taglength ; j++ ){
                        JSONObject tagObject = tagsArr.optJSONObject(j);
                        if(tagObject!=null){
                            Tag tag = new Tag();
                            tag.tagId = tagObject.optString(TAG_ID);
                            tag.tag = tagObject.optString(TAG);
                            tag.score = tagObject.optDouble(SCORE, 0);
                            tag.tagGroup = group.tagGroup;
                            group.tags.add(tag);

                        }
                    }

                    groups.add(group);
                }

            }

        }

        return  groups;
    }

    private static List<SponsorContent> parseSponsorContentList(JSONArray contentArray) throws ProductCatException{
        List<SponsorContent> result = new ArrayList<SponsorContent>();
        int size = contentArray.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonItem = contentArray.getJSONObject(i);
                SponsorContent sponsorContent = new SponsorContent();
                sponsorContent.setId(jsonItem.optInt(ID,0));
                sponsorContent.setTitle(jsonItem.optString(TITLE));
                sponsorContent.setDesc(jsonItem.optString(DESC));
                sponsorContent.setContentType(jsonItem.optString(CONTENT_TYPE));
                sponsorContent.setTrackerUrl(jsonItem.optString(TRACKER_URL));
                sponsorContent.setLink(jsonItem.optString(LINK));
                sponsorContent.setPlacement(jsonItem.optString(PLACEMENT));

                if(jsonItem.has(PLACEMENT_POSITION)) {
                    sponsorContent.setPlacementPosition(jsonItem.getInt(PLACEMENT_POSITION));
                }

                if(jsonItem.has(ORG_SEARCH_RESULT_POSITION)){
                    sponsorContent.setOrgSearchResultPosition(jsonItem.getInt(ORG_SEARCH_RESULT_POSITION));
                }

                // images
                if ( jsonItem.has(IMAGES) ) {
                    JSONArray imgArr = jsonItem.getJSONArray(IMAGES);
                    List<SponsorContentImg> images = new ArrayList<SponsorContentImg>();

                    for (int imgIndex = 0, length = imgArr.length() ; imgIndex < length ; imgIndex++) {
                        JSONObject imgItem = imgArr.getJSONObject(imgIndex);
                        SponsorContentImg img = new SponsorContentImg();
                        img.url = imgItem.optString(URL);

                        if(imgItem.has(H)) {
                            img.h = imgItem.getInt(H);
                        }

                        if(imgItem.has(W)) {
                            img.w = imgItem.getInt(W);
                        }

                        images.add(img);
                    }

                    sponsorContent.setImages(images);
                }


                result.add(sponsorContent);
            }
        } catch (JSONException e) {
            throw new ProductCatException("Error parsing response result " + e.getMessage(), e);
        }
        return result;
    }


    private static List<ProductSummary> parseProductSummaryList(JSONArray resultArray) throws ProductCatException {
        List<ProductSummary> resultList = new ArrayList<ProductSummary>();
        int size = resultArray.length();

        try {
            for (int i = 0; i < size; i++) {
                JSONObject summary = resultArray.getJSONObject(i);
                ProductSummary productSummary = new ProductSummary();
                productSummary.setPgid(summary.optString(PGID));
                productSummary.setPid(summary.optString(PID));
                productSummary.setMainImage(summary.optString(MAIN_IMAGE));

                String boostedString = summary.optString(BOOSTED);
                productSummary.setBoosted(TRUE.equals(boostedString));

                // images
                if ( summary.has(IMAGES) ) {
                    JSONArray imgArr = summary.getJSONArray(IMAGES);
                    for (int imgIndex = 0, length = imgArr.length() ; imgIndex < length ; imgIndex++) {
                        productSummary.getImages().add(imgArr.getString(imgIndex) );
                    }
                }

                productSummary.setTitle(summary.optString(TITLE));
                productSummary.setDesc(summary.optString(DESC));
                productSummary.setCountry(summary.optString(COUNTRY));
                productSummary.setMinPrice(summary.optDouble(MIN_PRICE, 0));
                productSummary.setMaxPrice(summary.optDouble(MAX_PRICE, 0));
                productSummary.setPriceUnit(summary.optString(PRICE_UNIT));
                productSummary.setBrand(summary.optString(BRAND));
                productSummary.setBrandId(summary.optString(BRAND_ID));
                productSummary.setPriceSymbol(summary.optString(PRICE_SYMBOL, null));

                if(summary.has(ORIGINAL_PRICE_UNIT)) {
                    productSummary.setOrgPriceUnit(summary.optString(ORIGINAL_PRICE_UNIT));
                }

                if(summary.has(ORIGINAL_MIN_PRICE)) {
                    productSummary.setOrgMinPrice(summary.optDouble(ORIGINAL_MIN_PRICE));
                }

                if(summary.has(ORIGINAL_MAX_PRICE)) {
                    productSummary.setOrgMaxPrice(summary.optDouble(ORIGINAL_MAX_PRICE));
                }

                if (summary.has(MIN_O_PRICE)) {
                    productSummary.setMinOPrice(summary.optDouble(MIN_O_PRICE));
                }

                if (summary.has(MAX_O_PRICE)) {
                    productSummary.setMaxOPrice(summary.optDouble(MAX_O_PRICE));
                }

                if (summary.has(ORIGINAL_MIN_O_PRICE)) {
                    productSummary.setOrgMinOPrice(summary.optDouble(ORIGINAL_MIN_O_PRICE));
                }

                if (summary.has(ORIGINAL_MAX_O_PRICE)) {
                    productSummary.setOrgMaxOPrice(summary.optDouble(ORIGINAL_MAX_O_PRICE));
                }

                if (summary.has(ATTRS)) {
                    productSummary.setAttrs( toMap(summary.getJSONObject(ATTRS)) );
                }

                productSummary.setAvailability(summary.optInt(AVAILABILITY, 1));
                productSummary.setProductUrl(summary.optString(PRODUCT_URL));

                productSummary.setPriceString(summary.optString(PRICE));
                productSummary.setSellerName(summary.optString(SELLER_NAME));
                productSummary.setVisualScore(summary.optDouble(VISUAL_SCORE));

                if (summary.has(STORES)) {
                    JSONArray storesJson = summary.getJSONArray(STORES);
                    for (int si = 0, length = storesJson.length() ; si < length ; si++) {
                        JSONObject storeObj = storesJson.getJSONObject(si);
                        Store store = new Store();
                        store.setStoreId(storeObj.optInt(STORE_ID, 0));
                        store.setName(storeObj.optString(NAME, BLANK));
                        store.setAvailability(storeObj.optInt(AVAILABILITY, 1));

                        productSummary.getStores().add(store);
                    }
                }

                resultList.add(productSummary);
            }
        } catch (JSONException e) {
            throw new ProductCatException("Error parsing response result " + e.getMessage(), e);
        }

        return resultList;
    }

    private static Map<String, Object> toMap(JSONObject jsonobj)  throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keys = jsonobj.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            Object value = jsonobj.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }   return map;
    }

    private static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }

        return list;
    }

    private static List<ProductType> parseProductTypeList(JSONArray resultArray) throws ProductCatException {
        List<ProductType> resultList = new ArrayList<ProductType>();
        int size = resultArray.length();

        try {
            for (int i = 0; i < size; i++) {
                JSONObject typeObj = resultArray.getJSONObject(i);
                ProductType productType = new ProductType();
                productType.setType(typeObj.getString(TYPE));
                productType.setScore(typeObj.getDouble(SCORE));
                JSONArray boxCoordinate = typeObj.getJSONArray(BOX);
                if (boxCoordinate.length() >= 4) {
                    Box box = new Box(boxCoordinate.getInt(0),
                            boxCoordinate.getInt(1),
                            boxCoordinate.getInt(2),
                            boxCoordinate.getInt(3));
                    productType.setBox(box);
                }

                if (typeObj.has(ATTRIBUTES)) {
                    JSONObject attrsArray = typeObj.getJSONObject(ATTRIBUTES);
                    JSONArray attrsNames = attrsArray.names();
                    Map attrsMapList = new HashMap<>();
                    for (int j = 0; attrsNames != null && j < attrsNames.length(); j++) {
                        JSONArray attrsItems = (JSONArray) attrsArray.get((String)attrsNames.get(j));
                        List<String> attrs = new ArrayList<>();
                        for (int k = 0; k < attrsItems.length(); k++) {
                            attrs.add((String) attrsItems.get(k));
                        }
                        attrsMapList.put(attrsNames.get(j), attrs);
                    }
                    productType.setAttributeList(attrsMapList);
                }

                resultList.add(productType);
            }
        } catch (JSONException e) {
            throw new ProductCatException("Error parsing response result " + e.getMessage(),e);
        }

        return resultList;
    }

    public static String parseResponseError(JSONObject jsonObj) {
        try {
            String status = jsonObj.getString(STATUS);
            if (status == null) {
                throw new ProductCatException("Error parsing response: status is null");
            } else {
                if (status.equals(OK)) {
                    return null;
                } else {
                    if (!jsonObj.has(ERROR) || jsonObj.getJSONArray(ERROR).length() == 0) {
                        return "Error parsing response: missing error";
                    } else {
                        return jsonObj.getJSONArray(ERROR).get(0).toString();
                    }
                }
            }
        } catch (JSONException e) {
            throw new ProductCatException("Error parsing response " + e.getMessage(), e);
        }
    }

    public static StoreResultList parseStoresResult(String jsonResponse) {
        try {
            StoreResultList storeResultList = new StoreResultList();
            JSONObject jsonObject = new JSONObject(jsonResponse);
            storeResultList.setErrorMessage(parseResponseError(jsonObject));

            if (jsonObject.has(RESULT)) {
                JSONArray resultArray = jsonObject.getJSONArray(RESULT);
                storeResultList.setStore(parseStores(resultArray));
            }

            if (jsonObject.has("limit")) {
                storeResultList.setLimit(jsonObject.getInt("limit"));
            }

            if (jsonObject.has("page")) {
                storeResultList.setLimit(jsonObject.getInt("page"));
            }

            return storeResultList;
        } catch (JSONException e) {
            throw new ProductCatException("Error parsing response " + e.getMessage(), e);
        }
    }

    private static List<Store> parseStores(JSONArray storeArray) throws JSONException {
        List<Store> stores = new ArrayList<>();
        for (int i = 0, length = storeArray.length(); i < length; i++) {
            JSONObject o = storeArray.optJSONObject(i);
            Store store = new Store();
            store.setName(o.getString(NAME));
            store.setStoreId(o.optInt(STORE_ID));
            store.setAvailability(1);
            stores.add(store);
        }
        return stores;
    }
}
