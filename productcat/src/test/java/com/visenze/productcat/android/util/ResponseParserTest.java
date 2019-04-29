package com.visenze.productcat.android.util;

import com.visenze.productcat.android.model.Box;
import com.visenze.productcat.android.model.ProductSummary;
import com.visenze.productcat.android.model.ProductType;
import com.visenze.productcat.android.model.ResultList;
import com.visenze.productcat.android.model.SponsorContent;
import com.visenze.productcat.android.model.SponsorContentImg;
import com.visenze.productcat.android.model.Store;
import com.visenze.productcat.android.model.TagGroup;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ResponseParserTest {

    @Test
    public void parseSponsorContentResult() {
        String responseString = "{\n" +
                "    \"im_id\": \"2019042936585ce3972035654a11430c10c09be536012146d6d.png\",\n" +
                "    \"reqid\": \"05L6GNU6ODRRP6S6UTCJQGSS\",\n" +
                "    \"status\": \"OK\",\n" +
                "    \"error\": [],\n" +
                "    \"product_types\": [\n" +
                "        {\n" +
                "            \"type\": \"product_item\",\n" +
                "            \"score\": 0.98,\n" +
                "            \"box\": [\n" +
                "                10,\n" +
                "                6,\n" +
                "                1077,\n" +
                "                803\n" +
                "            ],\n" +
                "            \"attributes\": {}\n" +
                "        }\n" +
                "    ],\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"pgid\": \"a34e278e1f585977b0c25f3c4fe254cb\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35bc601e501e200cc1d1445ad.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"https://product.image.jpg\"\n" +
                "            ],\n" +
                "            \"title\": \"CANON INTERCHANGEABLE LENS EOS M50 (15-45) BLACK\",\n" +
                "            \"desc\": \"3.0 Varie Angle LCD Monitor24.1 MegapixelsAPS-C CMOS SensorDIGIC 84K Movie ShootingBuilt In Bluetooth5 Axis Stabilisation\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 199.5,\n" +
                "            \"max_price\": 1099,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"XXX-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1302434545,\n" +
                "                    \"name\": \"XXX store\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/XXX-SG_12665?cid=1005&reqid=05L6GNU6ODRRP6S6UTCJQGSS&pos=1&uid=05L463SHBNGK2GI8KDR2DTHJ&country=SG\",\n" +
                "            \"original_min_price\": 100.5,\n" +
                "            \"original_max_price\": 1200.4,\n" +
                "            \"original_price_unit\": \"USD\",\n" +
                "            \"pid\": \"XXX-SG_12665\",\n" +
                "            \"visual_score\": 0.8873569\n" +
                "        }\n" +
                "    ],\n" +
                "    \"sponsored_content\": [\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"content_type\": \"banner\",\n" +
                "            \"tracker_url\": \"https://test.tracker/url?x=xxx\",\n" +
                "            \"link\": \"https://notf.link?url\",\n" +
                "            \"placement\": \"top\",\n" +
                "            \"images\": [\n" +
                "                {\n" +
                "                    \"url\": \"http://image.jpg\",\n" +
                "                    \"w\": 320,\n" +
                "                    \"h\": 250\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"content_type\": \"ads\",\n" +
                "            \"title\": \"Sample Ads\",\n" +
                "            \"desc\": \"Sample Ads description\",\n" +
                "            \"tracker_url\": \"http://test.tracker/ads/url\",\n" +
                "            \"link\": \"https://notf.ads.link?url\",\n" +
                "            \"placement\": \"within\",\n" +
                "            \"placement_position\": 4,\n" +
                "            \"images\": [\n" +
                "                {\n" +
                "                    \"url\": \"https://test.ad/test.jpg\",\n" +
                "                    \"w\": 400,\n" +
                "                    \"h\": 400\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"recognize_result\": [\n" +
                "        {\n" +
                "            \"tag_group\": \"category\",\n" +
                "            \"tags\": [\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics\",\n" +
                "                    \"tag\": \"electronics\",\n" +
                "                    \"score\": 0.9986275434494019\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics|camera\",\n" +
                "                    \"tag\": \"camera\",\n" +
                "                    \"score\": 0.935146152973175\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics|lense\",\n" +
                "                    \"tag\": \"lense\",\n" +
                "                    \"score\": 0.10024268925189972\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics|camera|DSLR_camera\",\n" +
                "                    \"tag\": \"DSLR camera\",\n" +
                "                    \"score\": 0.7064892053604126\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics|camera|compact_camera\",\n" +
                "                    \"tag\": \"compact camera\",\n" +
                "                    \"score\": 0.4895308017730713\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ResultList resultList = ResponseParser.parseResult(responseString);
        ProductSummary item = assertCommon(resultList);

        assertTrue(100.5 == item.getOrgMinPrice());
        assertTrue(1200.4 == item.getOrgMaxPrice());
        assertEquals("USD", item.getOrgPriceUnit());

        List<SponsorContent> contents = resultList.getSponsorContents();
        assertEquals(2, contents.size());

        SponsorContent bannerContent = contents.get(0);
        SponsorContent adContent = contents.get(1);

        assertEquals(2, bannerContent.getId());
        assertEquals("banner", bannerContent.getContentType());
        assertEquals("https://test.tracker/url?x=xxx" , bannerContent.getTrackerUrl());
        assertEquals("https://notf.link?url", bannerContent.getLink());
        assertEquals("top", bannerContent.getPlacement());
        assertEquals(1, bannerContent.getImages().size());
        SponsorContentImg bannerImg = bannerContent.getImages().get(0);
        assertEquals("http://image.jpg", bannerImg.url);
        assertTrue(320 == bannerImg.w);
        assertTrue(250 == bannerImg.h);
        assertNull(bannerContent.getPlacementPosition());

        assertEquals(1, adContent.getId());
        assertEquals("ads", adContent.getContentType());
        assertEquals("http://test.tracker/ads/url" , adContent.getTrackerUrl());
        assertEquals("https://notf.ads.link?url", adContent.getLink());
        assertEquals("within", adContent.getPlacement());
        assertEquals(1, adContent.getImages().size());
        SponsorContentImg adImg = adContent.getImages().get(0);
        assertEquals("https://test.ad/test.jpg", adImg.url);
        assertTrue(400 == adImg.w);
        assertTrue(400 == adImg.h);
        assertTrue(4 == adContent.getPlacementPosition());







    }

    @Test
    public void testParseNormalResult(){
        String responseString = "{\n" +
                "    \"im_id\": \"2019042936585ce3972035654a11430c10c09be536012146d6d.png\",\n" +
                "    \"reqid\": \"05L6GNU6ODRRP6S6UTCJQGSS\",\n" +
                "    \"status\": \"OK\",\n" +
                "    \"error\": [],\n" +
                "    \"product_types\": [\n" +
                "        {\n" +
                "            \"type\": \"product_item\",\n" +
                "            \"score\": 0.98,\n" +
                "            \"box\": [\n" +
                "                10,\n" +
                "                6,\n" +
                "                1077,\n" +
                "                803\n" +
                "            ],\n" +
                "            \"attributes\": {}\n" +
                "        }\n" +
                "    ],\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"pgid\": \"a34e278e1f585977b0c25f3c4fe254cb\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35bc601e501e200cc1d1445ad.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"https://product.image.jpg\"\n" +
                "            ],\n" +
                "            \"title\": \"CANON INTERCHANGEABLE LENS EOS M50 (15-45) BLACK\",\n" +
                "            \"desc\": \"3.0 Varie Angle LCD Monitor24.1 MegapixelsAPS-C CMOS SensorDIGIC 84K Movie ShootingBuilt In Bluetooth5 Axis Stabilisation\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 199.5,\n" +
                "            \"max_price\": 1099,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"XXX-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1302434545,\n" +
                "                    \"name\": \"XXX store\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/XXX-SG_12665?cid=1005&reqid=05L6GNU6ODRRP6S6UTCJQGSS&pos=1&uid=05L463SHBNGK2GI8KDR2DTHJ&country=SG\",\n" +
                "            \"pid\": \"XXX-SG_12665\",\n" +
                "            \"visual_score\": 0.8873569\n" +
                "        }\n" +
                "    ],\n" +
                "    \"recognize_result\": [\n" +
                "        {\n" +
                "            \"tag_group\": \"category\",\n" +
                "            \"tags\": [\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics\",\n" +
                "                    \"tag\": \"electronics\",\n" +
                "                    \"score\": 0.9986275434494019\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics|camera\",\n" +
                "                    \"tag\": \"camera\",\n" +
                "                    \"score\": 0.935146152973175\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics|lense\",\n" +
                "                    \"tag\": \"lense\",\n" +
                "                    \"score\": 0.10024268925189972\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics|camera|DSLR_camera\",\n" +
                "                    \"tag\": \"DSLR camera\",\n" +
                "                    \"score\": 0.7064892053604126\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|electronics|camera|compact_camera\",\n" +
                "                    \"tag\": \"compact camera\",\n" +
                "                    \"score\": 0.4895308017730713\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ResultList resultList = ResponseParser.parseResult(responseString);
        ProductSummary item = assertCommon(resultList);

        assertNull(item.getOrgMinPrice());
        assertNull(item.getOrgMaxPrice());
        assertNull(item.getOrgPriceUnit());
    }

    private ProductSummary assertCommon(ResultList resultList) {
        assertEquals("05L6GNU6ODRRP6S6UTCJQGSS" , resultList.getReqid());
        assertEquals("2019042936585ce3972035654a11430c10c09be536012146d6d.png" , resultList.getImId());
        assertNull(resultList.getClientReqId());
        assertNull(resultList.getErrorMessage());

        List<ProductType> productTypes = resultList.getProductTypes();
        assertEquals(1, productTypes.size());
        ProductType productType = productTypes.get(0);
        assertEquals("product_item", productType.getType());
        assertTrue(0.98 == productType.getScore());
        Box box = productType.getBox();

        assertTrue(10  == box.getX1());
        assertTrue(6  == box.getY1());
        assertTrue(1077  == box.getX2());
        assertTrue(803  == box.getY2());

        List<ProductSummary> summaries = resultList.getProductSummaryList();
        assertEquals(1, summaries.size());
        ProductSummary item = summaries.get(0);
        assertEquals("a34e278e1f585977b0c25f3c4fe254cb", item.getPgid());
        assertEquals("XXX-SG_12665" , item.getPid());
        assertEquals("https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35bc601e501e200cc1d1445ad.jpg", item.getMainImage());
        assertEquals("https://product.image.jpg", item.getImages().get(0));
        assertEquals("CANON INTERCHANGEABLE LENS EOS M50 (15-45) BLACK", item.getTitle());
        assertEquals("3.0 Varie Angle LCD Monitor24.1 MegapixelsAPS-C CMOS SensorDIGIC 84K Movie ShootingBuilt In Bluetooth5 Axis Stabilisation", item.getDesc());
        assertEquals("SG", item.getCountry());

        assertTrue(199.5 == item.getMinPrice());
        assertTrue(1099 == item.getMaxPrice());
        assertEquals("SGD", item.getPriceUnit());

        assertEquals(1, item.getAvailability());
        assertEquals(1, item.getStores().size());
        Store store = item.getStores().get(0);
        assertEquals(1302434545, store.getStoreId());
        assertEquals("XXX store", store.getName());
        assertEquals(1, store.getAvailability());

        assertEquals("https://productcat-api.visenze.com/redirect/XXX-SG_12665?cid=1005&reqid=05L6GNU6ODRRP6S6UTCJQGSS&pos=1&uid=05L463SHBNGK2GI8KDR2DTHJ&country=SG",
                item.getProductUrl());
        assertTrue(0.8873569 == item.getVisualScore());

        List<TagGroup> tagGroups = resultList.getTagGroups();
        assertEquals(1, tagGroups.size());
        TagGroup categoryTagGroup = tagGroups.get(0);
        assertEquals("category", categoryTagGroup.tagGroup);
        assertEquals(5, categoryTagGroup.tags.size());
        assertEquals("root|electronics",categoryTagGroup.tags.get(0).tagId);
        assertTrue(0.9986275434494019 == categoryTagGroup.tags.get(0).score);
        assertEquals("root|electronics|camera|compact_camera",categoryTagGroup.tags.get(4).tagId);
        assertTrue(0.4895308017730713 == categoryTagGroup.tags.get(4).score);
        return item;
    }
}