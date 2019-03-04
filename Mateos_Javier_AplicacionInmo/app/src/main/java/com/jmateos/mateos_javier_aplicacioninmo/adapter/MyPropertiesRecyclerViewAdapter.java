package com.jmateos.mateos_javier_aplicacioninmo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jmateos.mateos_javier_aplicacioninmo.R;
import com.jmateos.mateos_javier_aplicacioninmo.fragment.PropertyDetailFragment;
import com.jmateos.mateos_javier_aplicacioninmo.fragment.RemoveProperty;
import com.jmateos.mateos_javier_aplicacioninmo.listener.PropertiesInteractionListener;
import com.jmateos.mateos_javier_aplicacioninmo.response.Mine;
import com.jmateos.mateos_javier_aplicacioninmo.response.PropertyResponse;
import com.jmateos.mateos_javier_aplicacioninmo.ui.PropertyDetailActivity;

import java.util.List;

public class MyPropertiesRecyclerViewAdapter extends RecyclerView.Adapter<MyPropertiesRecyclerViewAdapter.ViewHolder> {


    private List<Mine> mValues;
    private final PropertiesInteractionListener mListener;
    private Context ctx;
    private PropertyResponse property;

    public MyPropertiesRecyclerViewAdapter(Context ctx, List<Mine> items, PropertiesInteractionListener listener) {
        this.ctx = ctx;
        mValues = items;
        mListener = listener;
    }

    public void MyPropertiesRecyclerViewAdapter(List<Mine> nuevosInmubles) {
        this.mValues = nuevosInmubles;
        notifyDataSetChanged();
    }

    @Override
    public MyPropertiesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_myproperties, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyPropertiesRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mTitulo.setText(mValues.get(position).getTitle());
        holder.mPrecio.setText(String.valueOf(mValues.get(position).getPrice())+ " €");
        holder.mHabitaciones.setText("Nº Hab: "+ String.valueOf(mValues.get(position).getRooms()));

        holder.itemView.setTag(mValues.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mine item = (Mine) v.getTag();
                Intent intent = new Intent(ctx, PropertyDetailActivity.class);
                intent.putExtra(PropertyDetailFragment.ARG_ITEM_ID, item.getId());
                ctx.startActivity(intent);
            }
        });

        holder.btnDeleteProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveProperty dialogoEliminar = RemoveProperty.newInstance();
                dialogoEliminar.show(((FragmentActivity) ctx).getSupportFragmentManager(), "dialog");
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

        public final FloatingActionButton btnDeleteProperty;


        public Mine mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitulo = view.findViewById(R.id.textTitulo);
            mPrecio = view.findViewById(R.id.textPrecio);
            //mCiudad = view.findViewById(R.id.textCiudad);
            mHabitaciones = view.findViewById(R.id.textHabitaciones);
            mPhoto = view.findViewById(R.id.imageList);
            btnDeleteProperty = view.findViewById(R.id.fabDelete);


        }
    }
}
