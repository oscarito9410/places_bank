package com.oscar.nat_at.Presenter;

import com.oscar.nat_at.MVPCore.Presenter.BasePresenter;

/**
 * Created by oemy9 on 07/11/2017.
 */
public interface InicioPresenter extends BasePresenter {
    void initBusqueda(double lat, final double lng,int radius);
}
