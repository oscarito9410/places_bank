package com.oscar.nat_at.Presenter;

import android.content.Intent;

import com.oscar.nat_at.Data.Constants;
import com.oscar.nat_at.Data.Model.Result;
import com.oscar.nat_at.MVPCore.Presenter.BasePresenterImpl;
import com.oscar.nat_at.MVPCore.View.BaseView;
import com.oscar.nat_at.Utils.Utils;
import com.squareup.picasso.Target;

/**
 * Created by oemy9 on 08/11/2017.
 */

public class DetallePresenterImpl extends BasePresenterImpl implements DetallePresenter {

    private DetalleView view;
    private Result selectedItem;

    @Override
    public void register(BaseView view) {
        super.register(view);
        this.view=(DetalleView)view;
    }

    @Override
    public void setArguments(Intent intent) {
        if(intent.hasExtra(Constants.SELECTED_ITEM)){
            selectedItem=(Result)intent.getSerializableExtra(Constants.SELECTED_ITEM);
        }
    }

    @Override
    public void initDetalleInfo() {
        if(selectedItem!=null){
            selectedItem.setUrlMapa(Utils.getMapaUbicacion(selectedItem.getGeometry().getLocation().getLat(),
                    selectedItem.getGeometry().getLocation().getLng()));
            view.onInfoLoaded(selectedItem);
        }
    }

    public interface  DetalleView extends BaseView{
        void onInfoLoaded(Result result);
    }
}
