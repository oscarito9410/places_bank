package com.oscar.nat_at.Data.Client;

import com.oscar.nat_at.Data.CallBacks.CallBackGoogleSearch;

/**
 * Created by oemy9 on 07/11/2017.
 */

public interface IGoogleClient {
     void busqueda(double lat, double lng, int radius, CallBackGoogleSearch callBack);
}
