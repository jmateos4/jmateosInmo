package com.jmateos.mateos_javier_aplicacioninmo.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jmateos.mateos_javier_aplicacioninmo.R;
import com.jmateos.mateos_javier_aplicacioninmo.response.PropertyResponse;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.generator.ServiceGenerator;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.generator.TipoAutenticacion;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.services.PropertyService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PropertyDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private PropertyResponse mItem;

    private TextView deDescripcion, dePrecio;




    public PropertyDetailFragment() {
        //
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.content_property_detail, container, false);

        // Show the dummy content as text in a TextView.

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.


            final String idProperty= getArguments().getString(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            final CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            PropertyService service = ServiceGenerator.createService(PropertyService.class);

            Call<PropertyResponse> call = service.oneProperty(idProperty);
            call.enqueue(new Callback<PropertyResponse>() {
                @Override
                public void onResponse(Call<PropertyResponse> call, Response<PropertyResponse> response) {
                    if (response.isSuccessful()) {
                    mItem = new PropertyResponse(idProperty, response.body().getTitle(), response.body().getDescription(), response.body().getPrice(), response.body().getRooms(), response.body().getSize(), response.body().getCategoryId(), response.body().getAddress(), response.body().getZipcode(), response.body().getCity(), response.body().getProvince(), response.body().getLoc(), response.body().getCreatedAt(), response.body().getUpdatedAt());
                        appBarLayout.setTitle(mItem.getTitle());

                        //Glide
                                //.with(getContext())
                                //.load(mItem.getFoto())
                                /*.into(new SimpleTarget<Drawable>(){

                                    @Override
                                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                        appBarLayout.setBackground(resource);
                                    }
                                });*/
                        ((TextView) rootView.findViewById(R.id.deDescripcion)).setText("Descripcion: " + mItem.getDescription());
                        ((TextView) rootView.findViewById(R.id.dePrecio)).setText("Precio: " + String.valueOf(mItem.getPrice()));
                        ((TextView) rootView.findViewById(R.id.deHab)).setText("Nº Habitaciones: " + String.valueOf(mItem.getRooms()));
                        ((TextView) rootView.findViewById(R.id.deDireccion)).setText("Direccion: " + mItem.getAddress());
                        ((TextView) rootView.findViewById(R.id.deCiudad)).setText("Ciudad: " + mItem.getCity());
                    } else {
                        // Toast
                    }


                }

                @Override
                public void onFailure(Call<PropertyResponse> call, Throwable t) {
                    // Toast
                    Log.i("onFailure", "error en retrofit");
                }
            });


        }


        return rootView;
    }
}