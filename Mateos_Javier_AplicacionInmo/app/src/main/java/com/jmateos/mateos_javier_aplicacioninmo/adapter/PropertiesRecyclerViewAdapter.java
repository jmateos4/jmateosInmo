package com.jmateos.mateos_javier_aplicacioninmo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jmateos.mateos_javier_aplicacioninmo.PropertyDetailActivity;
import com.jmateos.mateos_javier_aplicacioninmo.R;
import com.jmateos.mateos_javier_aplicacioninmo.fragment.PropertyDetailFragment;
import com.jmateos.mateos_javier_aplicacioninmo.listener.PropertiesInteractionListener;
import com.jmateos.mateos_javier_aplicacioninmo.response.PropertyResponse;

import java.util.List;


public class PropertiesRecyclerViewAdapter extends RecyclerView.Adapter<PropertiesRecyclerViewAdapter.ViewHolder> {

    private final List<PropertyResponse> mValues;
    private final PropertiesInteractionListener mListener;
    private Context ctx;

    public PropertiesRecyclerViewAdapter(Context ctx, List<PropertyResponse> items, PropertiesInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_properties, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitulo.setText(mValues.get(position).getTitle());
        holder.mPrecio.setText(String.valueOf(mValues.get(position).getPrice())+ " €");
        holder.mHabitaciones.setText("Nº Hab: "+ String.valueOf(mValues.get(position).getRooms()));

        if(mValues.get(position).getPhotos() == null) {

        } else {
            Glide
                    .with(ctx)
                    .load(mValues.get(position).getPhotos().get(0))
                    .into(new SimpleTarget<Drawable>(){
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
        public final ImageView mPhoto;


        public PropertyResponse mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitulo = view.findViewById(R.id.textTitulo);
            mPrecio = view.findViewById(R.id.textPrecio);
            //mCiudad = view.findViewById(R.id.textCiudad);
            mHabitaciones = view.findViewById(R.id.textHabitaciones);
            mPhoto = view.findViewById(R.id.imageList);





        }

    }
}
