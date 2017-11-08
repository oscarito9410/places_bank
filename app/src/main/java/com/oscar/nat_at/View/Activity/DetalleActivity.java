package com.oscar.nat_at.View.Activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.oscar.nat_at.Data.Model.Result;
import com.oscar.nat_at.Presenter.DetallePresenter;
import com.oscar.nat_at.Presenter.DetallePresenterImpl;
import com.oscar.nat_at.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalleActivity extends BaseActivity implements DetallePresenterImpl.DetalleView {

    private DetallePresenter presenter;
    private Result selectedItem;

    @BindView(R.id.imageMapa)
    ImageView imageMapa;
    @BindView(R.id.imageLugarItem)
    ImageView imageLugar;
    @BindView(R.id.tvNombreLugar)
    TextView tvNombre;
    @BindView(R.id.tvDireccion)
    TextView tvDireccion;
    @BindView(R.id.tvDistancia)
    TextView tvDistancia;
    @BindView(R.id.ratingLugarItem)
    RatingBar ratingLugar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setPresenter();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setPresenter() {
        presenter=new DetallePresenterImpl();
        presenter.register(this);
        presenter.setArguments(getIntent());
        presenter.initDetalleInfo();
    }

    @Override
    public Activity getActivityInstance() {
        return this;
    }

    @Override
    public void onInfoLoaded(Result r) {
        selectedItem=r;
        tvNombre.setText(r.getName());
        tvDireccion.setText(r.getVicinity());
        ratingLugar.setRating(r.getRating());
        tvDistancia.setText(String.format("a %1.2f km",r.getDistancia()/1000));
        toolbarLayout.setTitle(r.getName());
        Picasso.with(this).load(r.getIcon()).into(imageLugar);
        Picasso.with(this).load(r.getUrlMapa()).into(imageMapa);
    }

    @OnClick(R.id.fabSearch)
    public void searchIntent(){
            if(selectedItem!=null){
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, selectedItem.getName());
                startActivity(intent);
            }
    }
    @OnClick(R.id.fabShare)
    public void share(){
        if(selectedItem!=null){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,selectedItem.getName());
            intent.setType("text/plain");
            startActivity(intent);
        }
    }
}
