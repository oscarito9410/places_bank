package com.oscar.nat_at.Utils;
import android.location.Location;
import com.oscar.nat_at.Data.Constants;

/**
 * Created by oemy9 on 07/11/2017.
 */

public class Utils {
    public static float obtenerDistanciaPuntos(double lat, double lng, double latDos, double lngDos){
        Location loc = new Location("");
        loc.setLatitude(lat);
        loc.setLongitude(lng);

        Location locDos = new Location("");
        locDos.setLatitude(latDos);
        locDos.setLongitude(lngDos);
        return loc.distanceTo(locDos);
    }
    public static String getMapaUbicacion(double lat, double lng) {
        return String.format("https://maps.googleapis.com/maps/api/staticmap?center=%s,%s&zoom=13&size=400x400&" +
                "&markers=color:red|%s,%s&key=%s", lat, lng, lat, lng, Constants.API_KEY_STATIC_MAPS);
    }
}
