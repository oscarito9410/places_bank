package com.oscar.nat_at.Presenter;

import com.oscar.nat_at.ApplicationBase;
import com.oscar.nat_at.Data.CallBacks.CallBackGoogleSearch;
import com.oscar.nat_at.Data.Model.Result;
import com.oscar.nat_at.MVPCore.Presenter.BasePresenterImpl;
import com.oscar.nat_at.MVPCore.View.BaseView;
import com.oscar.nat_at.MVPCore.View.BaseViewServer;

import java.util.List;

/**
 * Created by oemy9 on 07/11/2017.
 */

public class InicioPreseterImpl extends BasePresenterImpl implements InicioPresenter, CallBackGoogleSearch {

    private InicioView view;

    @Override
    public void register(BaseView view) {
        super.register(view);
        this.view=(InicioView)view;
    }

    @Override
    public void initBusqueda(double lat, double lng,int radius) {
        view.showProgress();
        ApplicationBase.getIntance().getGoogleClientAPI().busqueda(lat,lng,radius,this);
    }

    @Override
    public void onSuccess(List<Result> listResults) {
        view.setListResults(listResults);
        view.hideProgress();
    }

    @Override
    public void onError(Throwable t) {
        view.showAlertError(t.getMessage());
        view.hideProgress();
        view.showErrorTextView();
    }

    public interface  InicioView extends BaseViewServer{
        void setListResults(List<Result> listResults);
        void showErrorTextView();
    }
}
