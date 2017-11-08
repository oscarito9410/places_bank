package com.oscar.nat_at.View.Activity;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oscar.nat_at.Data.Constants;
import com.oscar.nat_at.Data.Model.Result;
import com.oscar.nat_at.Presenter.InicioPresenter;
import com.oscar.nat_at.Presenter.InicioPreseterImpl;
import com.oscar.nat_at.Presenter.InicioPreseterImpl.InicioView;
import com.oscar.nat_at.R;
import com.oscar.nat_at.View.Adapter.AdapterLugar;
import com.oscar.nat_at.View.Listener.OnRecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InicioActivity extends BaseActivity implements InicioView, SwipeRefreshLayout.OnRefreshListener, OnRecyclerItemClickListener {

    private InicioPresenter presenter;

    @BindView(R.id.recyclerResultado)
    RecyclerView recyclerResultado;
    @BindView(R.id.progressCarga)
    ProgressBar progressCarga;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter=new InicioPreseterImpl();
        presenter.register(this);
        presenter.initBusqueda(Constants.DEFAULT_LAT,Constants.DEFAULT_LNG,Constants.DEFAULT_RADIUS);
    }

    @Override
    public void setListResults(List<Result> listResults) {
        if(listResults!=null){
            recyclerResultado.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            AdapterLugar adpt=new AdapterLugar(this,listResults);
            recyclerResultado.setLayoutManager(new LinearLayoutManager(this));
            recyclerResultado.setAdapter(adpt);
            adpt.setOnItemClickListener(this);
        }
    }

    @Override
    public void showErrorTextView() {
        tvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        swipeRefresh.setOnRefreshListener(this);
    }
    @Override
    public void showProgress() {
        tvEmpty.setVisibility(View.GONE);
        recyclerResultado.setVisibility(View.GONE);
        progressCarga.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        recyclerResultado.setVisibility(View.VISIBLE);
        progressCarga.setVisibility(View.GONE);
    }

    @Override
    public Activity getActivityInstance() {
        return this;
    }

    @Override
    public void showAlertError(@StringRes int mensaje) {
        showErrorMensaje(mensaje);
    }

    @Override
    public void showAlertError(String mensaje) {
        showErrorMensaje(mensaje);
    }

    @Override
    public void onRefresh() {
        presenter.initBusqueda(Constants.DEFAULT_LAT,Constants.DEFAULT_LNG,Constants.DEFAULT_RADIUS);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClickListener(Object selectedItem) {
            Result item=(Result)selectedItem;
            Intent intent=new Intent(this,DetalleActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable(Constants.SELECTED_ITEM, item);
            intent.putExtras(bundle);
            startActivity(intent);
    }
}
