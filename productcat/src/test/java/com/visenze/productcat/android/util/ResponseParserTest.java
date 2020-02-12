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
    public void parseSRPResult() {
        String responseString = "{\"im_id\":\"202002063654d637a048cbb5106e7c28d05dacf41d9720acf38.jpg\",\"reqid\":\"05O1IQO3OSUHB8VCQ0Q536PG\",\"status\":\"OK\",\"error\":[],\"product_types\":[{\"type\":\"bag\",\"score\":0.862,\"box\":[32,2,185,218],\"attributes\":{}}],\"srp_url\":\"https://shopping.visenze.com/search/05O1IQO3OSUHB8VCQ0Q536PG?country=SG&amp;uid=4444\",\"recognize_result\":[{\"tag_group\":\"category\",\"tags\":[{\"tag_id\":\"root|bag\",\"tag\":\"bag\",\"score\":0.9836792349815369},{\"tag_id\":\"root|bag|backpack\",\"tag\":\"backpack\",\"score\":0.9464564323425293}]}]}";
        ResultList resultList = ResponseParser.parseResult(responseString);

        assertEquals("https://shopping.visenze.com/search/05O1IQO3OSUHB8VCQ0Q536PG?country=SG&amp;uid=4444", resultList.getSrpUrl());
        assertEquals("05O1IQO3OSUHB8VCQ0Q536PG", resultList.getReqid());

        List<ProductType> productTypes = resultList.getProductTypes();
        assertEquals("bag", productTypes.get(0).getType());
        assertTrue(0.862 == productTypes.get(0).getScore());
        assertTrue(32 == productTypes.get(0).getBox().getX1());
        assertTrue(218 == productTypes.get(0).getBox().getY2());

    }

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
        assertFalse(item.isBoosted());

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
    public void testBoosted() {
        String r = "{\n" +
                "    \"im_id\": \"201906033654d637a048cbb5106e7c28d05dacf41d9720acf38.jpg\",\n" +
                "    \"reqid\": \"05LHR51JV4BT9028T7MVLJM4\",\n" +
                "    \"status\": \"OK\",\n" +
                "    \"error\": [],\n" +
                "    \"product_types\": [\n" +
                "        {\n" +
                "            \"type\": \"bag\",\n" +
                "            \"score\": 0.981,\n" +
                "            \"box\": [\n" +
                "                29,\n" +
                "                1,\n" +
                "                185,\n" +
                "                219\n" +
                "            ],\n" +
                "            \"attributes\": {}\n" +
                "        }\n" +
                "    ],\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"pgid\": \"48af0fb3ad2e58399a5f1c276603091c\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35c7dda5b000e01453b44ba6b.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"http://cf.shopee.sg/file/e5954d971e1e746cc7e6337eac3861bb\"\n" +
                "            ],\n" +
                "            \"title\": \"*sports shoes* Ready Men Women Unisex Laptop Outdoor Sports Travel Stude\",\n" +
                "            \"brand\": \"Shop: Adidas Nike COACH GUCCI CHANEL\",\n" +
                "            \"brand_id\": \"1769604923\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 58,\n" +
                "            \"max_price\": 58,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"SHOPEE-AF-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1552680525,\n" +
                "                    \"name\": \"Shopee\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/SHOPEE-AF-SG_1959945009?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=1&uid=05L4V00313CNS3VGENFOBT3J&country=SG\",\n" +
                "            \"pid\": \"SHOPEE-AF-SG_1959945009\",\n" +
                "            \"visual_score\": 0.74436575\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"39c134d247155a9d9c3582fd0a37473f\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35c4654ed02790177044bb328.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"http://cf.shopee.sg/file/fc96b9d0dc1dd5aed4a39e1f8e0aa2dc\"\n" +
                "            ],\n" +
                "            \"title\": \"Casual Bags Unisex Student Bags Backpack Fashion Bags Men And Woman Bags Adidas\",\n" +
                "            \"brand\": \"Shop: Original Sneakers Discout Store\",\n" +
                "            \"brand_id\": \"1654423710\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 18.88,\n" +
                "            \"max_price\": 18.88,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"SHOPEE-AF-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1552680525,\n" +
                "                    \"name\": \"Shopee\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/SHOPEE-AF-SG_1831569309?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=2&uid=05L4V00313CNS3VGENFOBT3J&country=SG\",\n" +
                "            \"pid\": \"SHOPEE-AF-SG_1831569309\",\n" +
                "            \"visual_score\": 0.7115052\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"243de4885e945da799a360992937445d\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35cee3ea2003a02016c7ad473.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"http://cf.shopee.sg/file/234252931a7a1701d127ec33a36625e5\"\n" +
                "            ],\n" +
                "            \"title\": \"Unisex Bagpack Inspired\",\n" +
                "            \"brand\": \"Shop: _triciatan\",\n" +
                "            \"brand_id\": \"523158126\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 38,\n" +
                "            \"max_price\": 38,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"SHOPEE-AF-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1552680525,\n" +
                "                    \"name\": \"Shopee\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/SHOPEE-AF-SG_56955919?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=3&uid=05L4V00313CNS3VGENFOBT3J&country=SG\",\n" +
                "            \"pid\": \"SHOPEE-AF-SG_56955919\",\n" +
                "            \"visual_score\": 0.70326114\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"5deb44835ee9589ab86847b32fbdf8f7\",\n" +
                "            \"main_image\": \"https://productcat.visenze.com/ad/imp/1016?reqid=05LHR51JV4BT9028T7MVLJM4&u=https%3A%2F%2Fimgtio.visenze.com%2Fweardex-insert-production%2F512x512%2F1360%2F6067%2F000017b35c90c352001400145e400e71.jpg&i=https%3A%2F%2Fap-southeast-1.pm-notifications.com%2Fdeliver%3Fbri%3Db076c935ad67bea0d0e105b29bb1df00b5c1a2bae6d9d1adb67fbdb81a%26esi%3D20064%26amtpd%3D0.8%26iid%3D5b8b6fe00f2957d51426ebd6846f80852ae30f63_200035762\",\n" +
                "            \"images\": [\n" +
                "                \"http://images.asos-media.com/inv/media/9/6/3/4/10504369/black/image1xxl.jpg\"\n" +
                "            ],\n" +
                "            \"title\": \"adidas Originals Trefoil Backpack in Black - Black\",\n" +
                "            \"desc\": \"Backpack by adidas Originals, Hands-free style, Grab handle, Padded straps and back for comfort, External pocket, Taped zip fastening, adidas logo, We ve always got time for the Trefoil. With a brand history stretching back over 60 years, adidas Originals draw inspiration from street culture and retro styles to provide fresh vintage inspired menswear. The adidas Originals range incorporates everything from the brands most iconic sneakers to new vintage inspired clothes.\",\n" +
                "            \"country\": \"AU\",\n" +
                "            \"min_price\": 40,\n" +
                "            \"max_price\": 40,\n" +
                "            \"price_unit\": \"AUD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"ASOS-AF-AU\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1810780878,\n" +
                "                    \"name\": \"ASOS\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"pid\": \"ASOS-AF-AU_7689317\",\n" +
                "            \"boosted\": \"true\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"793ae1b1e6ff5624bf908a0419e211ff\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35ce632af0036005a902b02a1.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"http://cf.shopee.sg/file/a43b31e7d00ca404d2045baac12e08a2\"\n" +
                "            ],\n" +
                "            \"title\": \"Reebok ACT FON M BACKPACK (Black) CE0926\",\n" +
                "            \"brand\": \"Reebok\",\n" +
                "            \"brand_id\": \"265951211\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 45,\n" +
                "            \"max_price\": 45,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"SHOPEE-AF-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1552680525,\n" +
                "                    \"name\": \"Shopee\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/SHOPEE-AF-SG_2188950294?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=4&uid=05L4V00313CNS3VGENFOBT3J&country=SG\",\n" +
                "            \"pid\": \"SHOPEE-AF-SG_2188950294\",\n" +
                "            \"visual_score\": 0.70252717\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"1d9245e8c0675e44b5b739dbb9a2eabe\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35ce62b1a003a00541a210215.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"http://cf.shopee.sg/file/bccfb58594892ac493a79d39d1b47f53\"\n" +
                "            ],\n" +
                "            \"title\": \"AD female bag waterproof light backpack bag computer students travel backpack ba\",\n" +
                "            \"brand\": \"Shop: muxika\",\n" +
                "            \"brand_id\": \"1157570890\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 30.8,\n" +
                "            \"max_price\": 30.8,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"SHOPEE-AF-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1552680525,\n" +
                "                    \"name\": \"Shopee\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/SHOPEE-AF-SG_2181949450?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=5&uid=05L4V00313CNS3VGENFOBT3J&country=SG\",\n" +
                "            \"pid\": \"SHOPEE-AF-SG_2181949450\",\n" +
                "            \"visual_score\": 0.7023196\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"03858e0591e75a4180f220ea297f53d2\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35c644edc0007014371519dbc.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"https://cdn.yoox.biz/45/45453149BG_14_F.JPG\"\n" +
                "            ],\n" +
                "            \"title\": \"ADIDAS ORIGINALS Backpacks & Fanny packs - Item 45453149\",\n" +
                "            \"desc\": \"maxi, techno fabric, logo, solid color, zip, external pockets, laptop compartment, fully lined, lifestyle.\",\n" +
                "            \"country\": \"AU\",\n" +
                "            \"min_price\": 48.1,\n" +
                "            \"max_price\": 48.1,\n" +
                "            \"price_unit\": \"AUD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"YOOX-AU\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1020127313,\n" +
                "                    \"name\": \"Yoox\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/YOOX-AU_794342?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=6&uid=05L4V00313CNS3VGENFOBT3J&country=AU\",\n" +
                "            \"pid\": \"YOOX-AU_794342\",\n" +
                "            \"visual_score\": 0.6953433\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"ad9d9e57e1d85aca8f440fad753cf4f9\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35c654c900007001f7eb1a2d7.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"https://res.cloudinary.com/ssenseweb/image/upload/b_white%2Cc_lpad%2Cg_center%2Ch_960%2Cw_960/c_scale%2Ch_680/f_auto%2Cdpr_1.0/v572/191751F042001_1.jpg\"\n" +
                "            ],\n" +
                "            \"title\": \"adidas Originals Black Classic Trefoil Logo Backpack\",\n" +
                "            \"desc\": \"Canvas backpack in black. Tonal rubberized piping throughout. Webbing carry handle at top. Logo flag at adjustable padded shoulder straps. Logo printed in white and zippered compartment at face. Text printed in white at back face. Zip closure at main compartment. Patch pocket and logo flag at interior. Tonal technical canvas lining. Tonal hardware. Tonal stitching. Approx. 13 length x 16 height x 3 width.\",\n" +
                "            \"country\": \"AU\",\n" +
                "            \"min_price\": 45,\n" +
                "            \"max_price\": 45,\n" +
                "            \"price_unit\": \"AUD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"SSENSE-AU\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 167346925,\n" +
                "                    \"name\": \"SSENSE\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/SSENSE-AU_50879?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=7&uid=05L4V00313CNS3VGENFOBT3J&country=AU\",\n" +
                "            \"pid\": \"SSENSE-AU_50879\",\n" +
                "            \"visual_score\": 0.6930913\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"3a91f8a6e5ed56e7a83e65f3791f74f5\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35bcd959c01e20015a206d28b.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"https://www.adidas.com.sg/dis/dw/image/v2/bcbs_prd/on/demandware.static/-/Sites-adidas-products/default/dweeeca358/zoom/DJ2170_01_standard.jpg\"\n" +
                "            ],\n" +
                "            \"title\": \"Trefoil Backpack - Black   Singapore\",\n" +
                "            \"desc\": \"This roomy backpack celebrates adidas heritage with a big Trefoil on the front pocket. It's made for today, with an inner pocket to keep your laptop close.\",\n" +
                "            \"brand\": \"Adidas\",\n" +
                "            \"brand_id\": \"1925936432\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 50,\n" +
                "            \"max_price\": 50,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"ADIDAS-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1682454850,\n" +
                "                    \"name\": \"Adidas\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/ADIDAS-SG_DJ2170?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=8&uid=05L4V00313CNS3VGENFOBT3J&country=SG\",\n" +
                "            \"pid\": \"ADIDAS-SG_DJ2170\",\n" +
                "            \"visual_score\": 0.69144493\n" +
                "        },\n" +
                "        {\n" +
                "            \"pgid\": \"6535acf864215499bf97d58609be7de0\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com/weardex-insert-production/512x512/1360/6067/000017b35c3380a402740069c5a76aec.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"http://cf.shopee.sg/file/59634d8313389a8658435a4004e586d0\"\n" +
                "            ],\n" +
                "            \"title\": \"【READY STOCK！Free Shipping 】 Beg Bag pack / School Bag student back pack\",\n" +
                "            \"brand\": \"Shop: water bottle\",\n" +
                "            \"brand_id\": \"2051792810\",\n" +
                "            \"country\": \"SG\",\n" +
                "            \"min_price\": 6.67,\n" +
                "            \"max_price\": 6.67,\n" +
                "            \"price_unit\": \"SGD\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"SHOPEE-AF-SG\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"store_id\": 1552680525,\n" +
                "                    \"name\": \"Shopee\",\n" +
                "                    \"availability\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://productcat-api.visenze.com/redirect/SHOPEE-AF-SG_1404679492?cid=1016&reqid=05LHR51JV4BT9028T7MVLJM4&pos=9&uid=05L4V00313CNS3VGENFOBT3J&country=SG\",\n" +
                "            \"pid\": \"SHOPEE-AF-SG_1404679492\",\n" +
                "            \"visual_score\": 0.68833417\n" +
                "        }\n" +
                "    ],\n" +
                "    \"sponsored_content\": [\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"content_type\": \"banner\",\n" +
                "            \"tracker_url\": \"https://ap-southeast-1.pm-notifications.com/deliver?bri=08f0241d85a0b1e598e705b39bb1df0099dbe0d1c5d1a3b44abdb81a&esi=20064&amtpd=0.8&iid=f831d54a5767cd9ba1ff6a8caeb42bab1131b5d5_200035763\",\n" +
                "            \"link\": \"https://ap-southeast-1.pm-notifications.com/ctc?bri=08f0241d85a0b1e598e705b39bb1df0099dbe0d1c5d1a3b44abdb81a\",\n" +
                "            \"placement\": \"top\",\n" +
                "            \"images\": [\n" +
                "                {\n" +
                "                    \"url\": \"https://ap-southeast-1.pm-notifications.com/deliver.image?iid=f831d54a5767cd9ba1ff6a8caeb42bab1131b5d5_200035763&bri=08f0241d85a0b1e598e705b39bb1df0099dbe0d1c5d1a3b44abdb81a&amtpd=0.8&esi=20064\",\n" +
                "                    \"w\": 468,\n" +
                "                    \"h\": 60\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 3,\n" +
                "            \"content_type\": \"featured_listing\",\n" +
                "            \"tracker_url\": \"https://ap-southeast-1.pm-notifications.com/deliver?bri=b076c935ad67bea0d0e105b29bb1df00b5c1a2bae6d9d1adb67fbdb81a&esi=20064&amtpd=0.8&iid=5b8b6fe00f2957d51426ebd6846f80852ae30f63_200035762\",\n" +
                "            \"link\": \"https://ap-southeast-1.pm-notifications.com/ctc?bri=b076c935ad67bea0d0e105b29bb1df00b5c1a2bae6d9d1adb67fbdb81a&bundle_id=1016&vz_type=3&vz_search=05LHR51JV4BT9028T7MVLJM4&aid=1810780878&vz_pid=ASOS-AF-AU_7689317&vz_url=http%3A%2F%2Fwww.asos.com%2Fau%2Fadidas-originals%2Fadidas-originals-trefoil-backpack-in-black%2Fprd%2F10504369%3F%26browseCountry%3DAU%26currencyid%3D21%26istCompanyId%3Df448b47d-6b90-4b9b-a52d-eb6058c99b1c%26istFeedId%3D87270f69-c692-481d-9ea5-f8305cfdff6d%26istItemId%3Dwmxqlliix%26istBid%3Dt\",\n" +
                "            \"placement\": \"within\",\n" +
                "            \"placement_position\": 4,\n" +
                "            \"org_search_result_position\": 14\n" +
                "        }\n" +
                "    ],\n" +
                "    \"recognize_result\": [\n" +
                "        {\n" +
                "            \"tag_group\": \"category\",\n" +
                "            \"tags\": [\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|bag\",\n" +
                "                    \"tag\": \"bag\",\n" +
                "                    \"score\": 0.9836792349815369\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|bag|backpack\",\n" +
                "                    \"tag\": \"backpack\",\n" +
                "                    \"score\": 0.9464564323425293\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ResultList resultList = ResponseParser.parseResult(r);
        List<ProductSummary> list = resultList.getProductSummaryList();
        assertTrue(list.size() == 10);
        assertTrue(list.get(3).isBoosted());
        SponsorContent featuredContent = resultList.getSponsorContents().get(1);
        assertTrue(featuredContent.getPlacementPosition() == 4);
        assertTrue(featuredContent.getOrgSearchResultPosition() == 14);


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

    @Test
    public void testParseEvalResponse() {
        String resString = "{\n" +
                "    \"im_id\": \"201906173654d637a048cbb5106e7c28d05dacf41d9720acf38.jpg\",\n" +
                "    \"reqid\": \"05LM799LEHPMK3715DUI9EE9\",\n" +
                "    \"status\": \"OK\",\n" +
                "    \"error\": [],\n" +
                "    \"product_types\": [\n" +
                "        {\n" +
                "            \"type\": \"bag\",\n" +
                "            \"score\": 0.981,\n" +
                "            \"box\": [\n" +
                "                29,\n" +
                "                1,\n" +
                "                185,\n" +
                "                219\n" +
                "            ],\n" +
                "            \"attributes\": {}\n" +
                "        }\n" +
                "    ],\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"pgid\": \"\",\n" +
                "            \"main_image\": \"https://imgtio.visenze.com.cn/weardex-insert-production/512x512/1360/7487/00001d3f5cc3f0f6003100658c7cec90.jpg\",\n" +
                "            \"images\": [\n" +
                "                \"http://g.search2.alicdn.com/img/bao/uploaded/i4/i2/1993730769/O1CN01RGmhtt1HYF49pzEbi_!!0-item_pic.jpg\"\n" +
                "            ],\n" +
                "            \"title\": \"阿迪达斯男包女包2019夏季新款三叶草书包电脑包运动双肩包D98923\",\n" +
                "            \"brand\": \"幸运叶子官方旗舰店\",\n" +
                "            \"brand_id\": \"108837944\",\n" +
                "            \"country\": \"CN\",\n" +
                "            \"min_price\": 230,\n" +
                "            \"max_price\": 239,\n" +
                "            \"price_unit\": \"CNY\",\n" +
                "            \"attrs\": {\n" +
                "                \"source\": \"XXX-CN\"\n" +
                "            },\n" +
                "            \"stores\": [\n" +
                "                {\n" +
                "                    \"name\": \"XXX-CN\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"availability\": 1,\n" +
                "            \"product_url\": \"https://detail.xxx.com/item.htm?id=585691895673\",\n" +
                "            \"pid\": \"XXX-CN_585691895673\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"recognize_result\": [\n" +
                "        {\n" +
                "            \"tag_group\": \"category\",\n" +
                "            \"tags\": [\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|bag\",\n" +
                "                    \"tag\": \"bag\",\n" +
                "                    \"score\": 0.9836792349815369\n" +
                "                },\n" +
                "                {\n" +
                "                    \"tag_id\": \"root|bag|backpack\",\n" +
                "                    \"tag\": \"backpack\",\n" +
                "                    \"score\": 0.9464564323425293\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ResultList resultList = ResponseParser.parseResult(resString);
        assertEquals("05LM799LEHPMK3715DUI9EE9", resultList.getReqid());
        assertEquals("201906173654d637a048cbb5106e7c28d05dacf41d9720acf38.jpg", resultList.getImId());
        assertNull(resultList.getErrorMessage());
        List<ProductType> productTypes = resultList.getProductTypes();
        assertEquals("bag", productTypes.get(0).getType());
        assertTrue(0.981 == productTypes.get(0).getScore());
        assertTrue(29 == productTypes.get(0).getBox().getX1());
        assertTrue(219 == productTypes.get(0).getBox().getY2());
        List<ProductSummary> summaries = resultList.getProductSummaryList();
        assertTrue(1 == summaries.size());

        ProductSummary product = summaries.get(0);
        assertEquals("", product.getPgid());
        assertEquals("XXX-CN_585691895673" , product.getPid());
        assertEquals("https://imgtio.visenze.com.cn/weardex-insert-production/512x512/1360/7487/00001d3f5cc3f0f6003100658c7cec90.jpg", product.getMainImage());
        assertEquals("http://g.search2.alicdn.com/img/bao/uploaded/i4/i2/1993730769/O1CN01RGmhtt1HYF49pzEbi_!!0-item_pic.jpg", product.getImages().get(0));
        assertEquals("阿迪达斯男包女包2019夏季新款三叶草书包电脑包运动双肩包D98923", product.getTitle());
        assertEquals("", product.getDesc());
        assertEquals("CN", product.getCountry());

        assertTrue(230 == product.getMinPrice());
        assertTrue(239 == product.getMaxPrice());
        assertEquals("CNY", product.getPriceUnit());

        assertEquals(1, product.getAvailability());
        assertEquals(1, product.getStores().size());
        Store store = product.getStores().get(0);
        assertEquals(0, store.getStoreId());
        assertEquals("XXX-CN", store.getName());
        assertEquals(1, store.getAvailability());

        assertEquals("https://detail.xxx.com/item.htm?id=585691895673",
                product.getProductUrl());

        assertEquals("108837944", product.getBrandId());
        assertEquals("幸运叶子官方旗舰店", product.getBrand());


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