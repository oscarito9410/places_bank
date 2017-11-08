package com.oscar.nat_at.View.Adapter;

import android.content.Context;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.oscar.nat_at.Data.Model.Result;
import com.oscar.nat_at.R;
import com.oscar.nat_at.View.Listener.OnRecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oemy9 on 07/11/2017.
 */

public class AdapterLugar extends RecyclerView.Adapter<AdapterLugar.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Result>listResult;
    private Context context;
    private OnRecyclerItemClickListener listener;

    public AdapterLugar(Context context,List<Result>listResult) {
        this.context = context;
        this.layoutInflater=LayoutInflater.from(this.context);
        this.listResult=listResult;
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_lugar,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Result r=getItem(position);
        Picasso.with(this.context).load(r.getIcon()).into(holder.imageLugar);
        holder.tvNombre.setText(r.getName());
        holder.tvDireccion.setText(r.getVicinity());
        holder.ratingLugar.setRating(r.getRating());
        holder.tvDistancia.setText(String.format("a %1.2f km",r.getDistancia()/1000));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.onItemClickListener(r);
            }
        });
    }

    public Result getItem(int position){
        return listResult.get(position);
    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageLugarItem)
        public ImageView imageLugar;
        @BindView(R.id.tvNombreLugar)
        public TextView tvNombre;
        @BindView(R.id.tvDireccion)
        public TextView tvDireccion;
        @BindView(R.id.tvDistancia)
        public TextView tvDistancia;
        @BindView(R.id.ratingLugarItem)
        public RatingBar ratingLugar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
