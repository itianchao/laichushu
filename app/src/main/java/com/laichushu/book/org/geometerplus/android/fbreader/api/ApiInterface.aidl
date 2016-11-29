/*
 * This code is in the public domain.
 */

package com.laichushu.book.org.geometerplus.android.org.geometerplus.android.fbreader.api;

import com.laichushu.book.org.geometerplus.android.org.geometerplus.android.fbreader.api.ApiObject;

interface ApiInterface {
	ApiObject request(int method, in ApiObject[] parameters);
	List<ApiObject> requestList(int method, in ApiObject[] parameters);
	Map requestMap(int method, in ApiObject[] parameters);
}
