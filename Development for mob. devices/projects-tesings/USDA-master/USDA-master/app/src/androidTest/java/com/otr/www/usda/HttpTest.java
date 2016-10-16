package com.otr.www.usda;

import android.test.ActivityTestCase;

import com.otr.www.usda.http.HttpAsnyTaskReport;

/**
 * Created by pingyaoooo on 2016/4/19.
 */
public class HttpTest extends ActivityTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();



    }

    public void testHttp() {
        HttpAsnyTaskReport report = new HttpAsnyTaskReport();
        report.execute("apple");
    }


}
