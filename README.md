# Productcat Android SDK

---

## Table of Contents
 1. [Setup](#1-setup)
 2. [Initialization](#2-initialization)
 3. [Solution APIs](#3-solution-apis)
	  - 3.1 [Search by Image](#31-search-by-image)
	    - 3.1.1 [Selection Box](#311-selection-box)
	    - 3.1.2 [Resizing Settings](#312-resizing-settings)
	  - 3.2 [Text Search ](#32-text-search)
	  - 3.3 [Search Result Page](#33-search-result-page)
	  - 3.4 [Custom Parameters](#34-custom-parameters)
 4. [Search Results](#4-search-results)    
      - 4.1 [Image Search and Text Search Result](#41-image-search-and-text-search-result)
      - 4.2 [Search Result Page](#42-search-result-page)
 5. [Data Privacy Workflow](#5-data-privacy-workflow)

---

## 1. Setup
## 1.1 Install the SDK
You can include the dependency in your project using gradle:

```
android {
	...
    packagingOptions {
        exclude 'META-INF/DEPENDENCIES'
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE.txt'
        exclude 'META-INF/license.txt'
        exclude 'META-INF/NOTICE'
        exclude 'META-INF/NOTICE.txt'
        exclude 'META-INF/notice.txt'
        exclude 'META-INF/ASL2.0'
    }
    ...
}
dependencies {
    implementation 'com.visenze.productcat:productcat:2.1.5'
    ...
}
```

## 1.2 Set User Permissions

ProductCat Android SDK needs these user permissions to work. Add the following declarations to the AndroidManifest.xml file. Network permission allows the app to connect to network services. Write/read to external storage permissions allow the app to load and save images on the device as well as storing search request information (uid).

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.visenze.android.visenze_demo_test">

	<uses-permission android:name="android.permission.CAMERA"/>
	<uses-permission android:name="android.permission.INTERNET"/>
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>

	<application>
	...
	</application>
</manifest>
```

## 2. Initialization
`ProductCat` client must be initialized with an app key before it can be used, default endpoint (https://productcat.visenze.com). In order for it to be notified of the search results, ProductCat.ResultListener must be implemented. Call productCat.setListener to set the listener.

```java
ProductCat productCat = new ProductCat.Builder(appKey)
							// for setting a different endpoint than https://productcat.visenze.com
                        .setApiEndPoint(new URL(endpoint))
                        .setTimeout(30000)
                        .build(context);
productCat.setListener(this);

```                

## 3. Solution APIs

ProductCat database is partitioned globally based on *country* and geographical regions. It is highly recommended that you call the Product Search APIs with **country** parameter to ensure only products that ship to the country will be returned.

```
// example: set search to Singapore
searchParams.getBaseSearchParams().setCountry("SG");
```

### 3.1 Search by Image

POST /summary/products

**Search by Image** solution is to search for visually similar products in the global products database given a product image.

* Using an image from a local file path:
```java
Image image = new Image("/local/path/to/image.jpg");
ImageSearchParams searchParams = new ImageSearchParams(image);

productCat.imageSearch(searchParams);
```
* Using an image by providing the Uri of the image in photo gallery:

```java
Image image = new Image(context, uri);
ImageSearchParams searchParams = new ImageSearchParams(image);

productCat.imageSearch(searchParams);
```

* Construct the `image` from the byte array returned by the camera preview callback:

```java
@Override
public void onPictureTaken(byte[] bytes, Camera camera) {
    Image image = new Image(bytes);
    ImageSearchParams searchParams = new ImageSearchParams(image);

    productCat.imageSearch(searchParams);
}
```

* Alternatively, you can pass an image url directly to `uploadSearchParams` to start the search :

```java
String url = "http://mydomain.com/sample_image.jpg";
ImageSearchParams searchParams = new ImageSearchParams(url);

productCat.imageSearch(searchParams);
```

* If you are performing refinement on an uploaded image, you can pass the im_id returned in the search result to start the search instead of uploading the image again:

```
String imId;

@Override
public void onSearchResult(ResultList resultList) {
    imId = resultList.getImId();
	for (ProductSummary summary : resultList.getProductSummaryList()) {
		//Do something with the result
		...
	}
}

ImageSearchParams searchParams = new ImageSearchParams(url);
uploadSearchParams.setImId(imId);
productCat.imageSearch(searchParams);
```

#### 3.1.1 Selection Box
If the object you wish to search for takes up only a small portion of your image, or other irrelevant objects exists in the same image, chances are the search result could become inaccurate. Use the Box parameter to refine the search area of the image to improve accuracy. The box coordinated is set with respect to the original size of the uploading image:

```java
Image image = new Image(this, uri);
// create the box to refine the area on the searching image
// Box(x1, y1, x2, y2) where (0,0) is the top-left corner
// of the image, (x1, y1) is the top-left corner of the box,
// and (x2, y2) is the bottom-right corner of the box.
image.setBox(0, 0, 400, 400);
```

If you are using im_url or im_id for upload search. You should pass the box in this way:

```java
ImageSearchParams searchParams = new ImageSearchParams();
searchParams.setImId(imId);
searchParams.setBox(new Box(0, 0, 400, 400));

productCat.imageSearch(searchParams);
```

#### 3.1.2 Resizing Settings
When performing visual search, you may notice the increased search latency with increased image file size. This is due to the increased time in network to transfer your images to the ProductCat server, and the increased time for processing larger image files in ProductCat.  

To reduce visual search latency, by default the products summary method makes a copy of your image file, and resizes to 1024x1024 pixels if both of the original dimensions exceed 1024 pixels. This is the optimized size to lower search latency while not sacrificing search accuracy for general use cases:  

* Image from local path or photo gallery

```java
//default resize setting, set the image size to 800 x 800
Image image = new Image(imagePath, ResizeSettings.STANDARD);
```

If your image contains fine details such as textile patterns and textures, you can use an image with larger size for search to get better search result:

```java
//for images with fine details, use HIGH resize settings 1024 x 1024
Image image = new Image(imagePath, ResizeSettings.HIGH);
```

Or, provide the customized resize settings. To make efficient use the of the memory and network bandwidth of mobile device, the maximum size is set at 1024 x 1024. Any image exceeds the limit will be resized to the limit:

```java
//resize the image to 512 by 512 area using jpeg 80 quality
Image image = new Image(imagePath, new ResizeSettings(512, 512, 80));
```

* Image from camera callback

ProductCat Android SDK provides an interface to handle byte array returned from [`Camera.PictureCallback`](http://developer.android.com/reference/android/hardware/Camera.PictureCallback.html). Use `ResizeSettings.CAMERA_STANDARD` and `ResizeSettings.CAMERA_HIGH` to configure the resize settings. The image taken from the camera might not be in the desired orientaiton, a rotation parameter can be set to rotate the image to the correct orientation:

```java
@Override
public void onPictureTaken(byte[] bytes, Camera camera) {
    Image image = new Image(bytes, ResizeSettings.CAMERA_HIGH, 90);
    ImageSearchParams searchParams = new ImageSearchParams(image);

    productCat.imageSearch(searchParams);
}
```

### 3.2 Text Search

POST /summary/products

**Text Search** solution is to search similar products by given query text.

```java
TextSearchParams textParams = new TextSearchParams(queryText) ;
productCat.textSearch(textParams);
```

### 3.3 Search Result Page
POST /summary/products/srp

**Search Result Page** solution is to get a web url to display the searched product results in browser. All parameters used in /summary/products are also valid in this api.

```java
@Override
public void onPictureTaken(byte[] bytes, Camera camera) {
    Image image = new Image(bytes, ResizeSettings.CAMERA_HIGH, 90);
    ImageSearchParams searchParams = new ImageSearchParams(image);

    productCat.imageSearchResultPage(searchParams);
}
```


### 3.4 Custom Parameters

If you have additional customized parameters, please use custom to set these parameters.

```java
Image image = new Image("/local/path/to/image.jpg");
ImageSearchParams searchParams = new ImageSearchParams(image);

Map<String, String> custom = new HashMap<>();
custom.put("param", "value");
custom.put("another_param", "value");
searchParams.getBaseSearchParams().setCustom(custom);  

productCat.imageSearch(searchParams);
```

## 4. Search Results
### 4.1 Image Search and Text Seach Result
The image search and text search results are returned as a list of products with required additional information. Use `getProductSummaryList()` to get the list of products. Please Use`productCat.cancelSearch()` to cancel a search, and handle the result by implementing the `onSearchCanceled()` callback. If error occurs during the search, an error message will be returned and can be handled in `productCat.onSearchError(String error)` callback method.

```java
@Override
public void onSearchResult(ResultList resultList) {
	for (ProductSummary summary : resultList.getProductSummaryList()) {
		//Do something with each product
		...
	}
}

@Override
public void onSearchError(String error) {
    resultView.displayError(error);
}

@Override
public void onSearchCanceled() {

}
```

You can provide pagination parameters to control the paging of the image search results. by configuring the basic search parameters `BaseSearchParams`. As the result is returned in a format of a list of images page by page, use `setLimit` to set the number of results per page, `setPage` to indicate the page number:

| Name | Type | Description |
| ---- | ---- | ----------- |
| page | Integer | Optional parameter to specify the page of results. The first page of result is 1. Defaults to 1. |
| limit | Integer | Optional parameter to specify the result per page limit. Defaults to 10. |

```java
BaseSearchParams baseSearchParams = new BaseSearchParams();
baseSearchParams.setLimit(20);
baseSearchParams.setPage(1);
ImageSearchParams searchParams = new ImageSearchParams(image);
searchParams.setBaseSearchParams(baseSearchParams);
productCat.textSearch(searchParams);
```
### 4.2 Search Result Page
The Search Result Page API returns a web url to display the content of the searched result in a browser. ProductSummaryList of this API result is empty

```java
@Override
public void onSearchResult(ResultList resultList) {
      String srpUrl = resultList.getSrpUrl();

      // show url content in browser.       
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(srpUrl));
      intent.setPackage(browserPackageName);
      startActivity(intent);
}
```
## 5. Data Privacy Workflow
To analyze search performance and usage statistics (such as Click Through Rate, Active Users, Conversion Tracking, Retention Rate, etc), Productcat sdk use UID together with Google Advertising ID or IMEI to uniquely identify a user. The UID is generated when user initialize productcat sdk for the first time. Based on user's usage statistics, Visenze will push customized seach results and advertising product items according to user's preference.
As a result, users need to:
- Accept Visenze's terms and condition before use image search and search result page api
- (Optionally) Accept advertisement terms to receive special offers, promotions information. ---

Visenze sdk will show up user Terms and Condition dialog for the first time initialize with Visenze's terms and condition checked by default. Users have to accept the terms and condition before using productcat api. In case user have denied the terms. Productcat sdk will throw search error in its callback, it is up to developer's decision to show up the consent forms again or quit the service.

```java
@Override
public void onSearchResult(ResultList resultList) {
        if(ProductCat.TERMS_DENIED.equals(errorMessage)) {
            // show consent form again.
            mProductCat.showConsentForm();
        }
}
```

Please note that end-users need to:
- Accept Visenze’s Privacy Policy & Terms of Use in order to use our service
- If users have accepted the terms and condition, product recommendation popup will show. If users accept the product recommendation setting, ViSenze will return recommended product items in the search result. Otherwise, only visual similar products will be shown in the search result.

Note that Visenze may change the terms of condition any time in the future. If so, productcat sdk will automatically prop up the consent forms again for user to operate.
