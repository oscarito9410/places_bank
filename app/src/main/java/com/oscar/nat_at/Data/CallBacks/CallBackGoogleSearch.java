package com.oscar.nat_at.Data.CallBacks;

import com.oscar.nat_at.Data.Model.Result;

import java.util.List;

/**
 * Created by oemy9 on 07/11/2017.
 */

public interface CallBackGoogleSearch {
    void onSuccess(List<Result>listResults);
    void onError(Throwable t);
}
