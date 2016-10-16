package com.otr.www.usda.ndbapi;

/**
 * Created by pingyaoooo on 2016/1/16.
 * http://api.nal.usda.gov/ndb/reports/?ndbno=04000&type=b&format=xml&api_key=DEMO_KEY
 * http://api.nal.usda.gov/ndb/reports/?ndbno=04000&type=b&format=json&api_key=DEMO_KEY
 * Parameter	Required	Default	Description
 * api_key	y	n/a	Must be a data.gov registered API key
 * ndbno	y	n/a	NDB no
 * type	n	b (basic)	Report type: [b]asic or [f]ull or [s]tats
 * format1	n	JSON	Report format: xml or json
 * BmbDSKJrP3PRjf6aOiGai8e1GE6WZGaCU7fWDRl5
 * https://api.data.gov/nrel/alt-fuel-stations/v1/nearest.json?api_key=BmbDSKJrP3PRjf6aOiGai8e1GE6WZGaCU7fWDRl5&location=Denver+CO
 */
public  class NDB_API {
    private String ndbno;
    private static final String FORMAT_JSON = "json";
    private static final String API_KEY = "BmbDSKJrP3PRjf6aOiGai8e1GE6WZGaCU7fWDRl5";
    private static final String TYPE_B = "b";
    private   String url = "http://api.nal.usda.gov/ndb/reports/?" +
            "ndbno=" + ndbno + "&type=" + TYPE_B + "&format=" + FORMAT_JSON +
            "&api_key=" + API_KEY;

    public static String SEARCH = "search";
    public static String LIST = "list";
    public static String Q = "q";
    public static String ITEM= "item";
    public static String OFFSET= "offset";
    public static String GROUP = "group";
    public static String NAME = "name";
    public static String NDBNO = "ndbno";

    public String getNdbno() {
        return ndbno;
    }

    public void setNdbno(String ndbno) {
        this.ndbno = ndbno;
        url = new String("http://api.nal.usda.gov/ndb/reports/?" +
                "ndbno=" + ndbno + "&type=" + TYPE_B + "&format=" + FORMAT_JSON +
                "&api_key=" + API_KEY);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //    Browser: http://api.nal.usda.gov/ndb/search/?format=json&q=butter&sort=n&max=25&offset=0&api_key=DEMO_KEY
    public static String getSearchUrl(String string) {
        String url = "http://api.nal.usda.gov/ndb/" +
                SEARCH+"/?format="+FORMAT_JSON+
                "&q="+string+"&sort=n&max=25&offset=0&api_key="+API_KEY;
        return url;
    }

    //    Food Reports -- obtain nutrient reports on individual foods
//    Browser: http://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=json&api_key=DEMO_KEY
    public static String getFoodReportsUrl(String ndbno) {
        String url = "http://api.nal.usda.gov/ndb/reports/?" +
                "ndbno=" + ndbno + "&type=b&format=" + FORMAT_JSON + "&api_key=" + API_KEY;
        return url;
    }
}
