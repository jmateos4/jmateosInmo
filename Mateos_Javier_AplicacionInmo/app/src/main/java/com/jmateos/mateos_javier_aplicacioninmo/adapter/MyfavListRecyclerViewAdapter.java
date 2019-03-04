package com.jmateos.mateos_javier_aplicacioninmo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jmateos.mateos_javier_aplicacioninmo.R;
import com.jmateos.mateos_javier_aplicacioninmo.UtilToken;
import com.jmateos.mateos_javier_aplicacioninmo.fragment.PropertyDetailFragment;
import com.jmateos.mateos_javier_aplicacioninmo.listener.PropertiesInteractionListener;
import com.jmateos.mateos_javier_aplicacioninmo.response.FavResponse;
import com.jmateos.mateos_javier_aplicacioninmo.response.PropertyResponse;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.generator.ServiceGenerator;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.generator.TipoAutenticacion;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.services.PropertyService;
import com.jmateos.mateos_javier_aplicacioninmo.ui.PropertyDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyfavListRecyclerViewAdapter extends RecyclerView.Adapter<MyfavListRecyclerViewAdapter.ViewHolder> {

    private final List<PropertyResponse> mValues;
    private final PropertiesInteractionListener mListener;
    private boolean isFav, nonFav;
    private Context ctx;


    public MyfavListRecyclerViewAdapter(Context ctx, List<PropertyResponse> items, PropertiesInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mTitulo.setText(mValues.get(position).getTitle());
        holder.mPrecio.setText(String.valueOf(mValues.get(position).getPrice()) + " €");
        holder.mHabitaciones.setText("Nº Hab: " + String.valueOf(mValues.get(position).getRooms()));

        if(UtilToken.getToken(ctx) == null){
            holder.mFav.setVisibility(View.GONE);
        }else{
            isFav = mValues.get(position).isFav();

            if( isFav || nonFav){
                holder.mFav.setImageResource(R.drawable.ic_action_like_orange);
            }else{
                holder.mFav.setImageResource(R.drawable.ic_action_favfull_orange);
            }

            holder.mFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isFav = mValues.get(position).isFav();
                    if(isFav || nonFav){
                        PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(ctx), TipoAutenticacion.JWT);
                        Call<FavResponse> callFavourites = service.deletePropertiesFav(holder.mItem.getId());

                        callFavourites.enqueue(new Callback<FavResponse>() {
                            @Override
                            public void onResponse(Call<FavResponse> call, Response<FavResponse> response) {
                                if(response.isSuccessful()){
                                    holder.mFav.setImageResource(R.drawable.ic_action_like_orange);
                                    isFav = !isFav;
                                    Toast.makeText(ctx, "Fav Deleted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<FavResponse> call, Throwable t) {

                                Log.e("NetworkFailure", t.getMessage());
                                Toast.makeText(ctx, "Fail", Toast.LENGTH_SHORT).show();

                            }
                        });
                        mValues.get(position).setFav(false);
                    }else{
                        PropertyService service = ServiceGenerator.createService(PropertyService.class,UtilToken.getToken(ctx),TipoAutenticacion.JWT);
                        Call<FavResponse> callNoFav = service.addPropertiesFav(holder.mItem.getId());
                        callNoFav.enqueue(new Callback<FavResponse>() {
                            @Override
                            public void onResponse(Call<FavResponse> call, Response<FavResponse> response) {
                                if(response.isSuccessful()){
                                    holder.mFav.setImageResource(R.drawable.ic_action_favfull_orange);
                                    isFav = !isFav;
                                    Toast.makeText(ctx, "Fav Successful", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<FavResponse> call, Throwable t) {
                                Log.e("NetworkFailure", t.getMessage());
                                Toast.makeText(ctx, "Fail", Toast.LENGTH_SHORT).show();

                            }
                        });
                        mValues.get(position).setFav(true);
                    }
                }
            });



        }



        if (mValues.get(position).getPhotos() == null) {

        } else {
            Glide
                    .with(ctx)
                    .load(mValues.get(position).getPhotos().get(0))
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            holder.mPhoto.setBackground(resource);
                        }
                    });
        }


        holder.itemView.setTag(mValues.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyResponse item = (PropertyResponse) v.getTag();
                Intent intent = new Intent(ctx, PropertyDetailActivity.class);
                intent.putExtra(PropertyDetailFragment.ARG_ITEM_ID, item.getId());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitulo;
        public final TextView mPrecio;
        // public final TextView mCiudad;
        public final TextView mHabitaciones;
        public final ImageView mPhoto, mFav;


        public PropertyResponse mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitulo = view.findViewById(R.id.textTitulo);
            mPrecio = view.findViewById(R.id.textPrecio);
            //mCiudad = view.findViewById(R.id.textCiudad);
            mHabitaciones = view.findViewById(R.id.textHabitaciones);
            mPhoto = view.findViewById(R.id.imageList);
            mFav = view.findViewById(R.id.imageFav);


        }


    }
}
