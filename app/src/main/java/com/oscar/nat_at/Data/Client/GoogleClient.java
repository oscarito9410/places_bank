package com.oscar.nat_at.Data.Client;;
import android.text.TextUtils;
import com.oscar.nat_at.ApplicationBase;
import com.oscar.nat_at.Data.CallBacks.CallBackGoogleSearch;
import com.oscar.nat_at.Data.Model.Result;
import com.oscar.nat_at.Data.Model.SearchResult;
import com.oscar.nat_at.Data.Retrofit.GoogleRetrofitClient;
import com.oscar.nat_at.R;
import com.oscar.nat_at.Utils.Utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oemy9 on 07/11/2017.
 */

public class GoogleClient extends GoogleRetrofitClient implements  IGoogleClient {
    public void busqueda(final double lat, final double lng, int radius, final CallBackGoogleSearch callBack) {
        apiService.obtenerLugares(TextUtils.join(",", Arrays.asList(lat, lng)), radius).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                    if(response.isSuccessful()){
                        List<Result> listResults=response.body().getResults();
                        if(listResults!=null) {
                            for (Result r : listResults) {
                                r.setDistancia(Utils.obtenerDistanciaPuntos(lat, lng, r.getGeometry().getLocation().getLat(),
                                        r.getGeometry().getLocation().getLng()));
                            }
                            Collections.sort(listResults, new Comparator<Result>() {
                                @Override
                                public int compare(Result o1, Result o2) {
                                    return o1.getDistancia()>o2.getDistancia()? 0: -1;
                                }
                            });
                            callBack.onSuccess(listResults);
                        }
                    }
                    else{
                        callBack.onError(new Throwable(ApplicationBase.getContext().getString(R.string.error_lista_vacia)));
                    }
            }
            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
              callBack.onError(new Throwable(ApplicationBase.getContext().getString(R.string.error_conexion)));
            }
        });
    }
}
