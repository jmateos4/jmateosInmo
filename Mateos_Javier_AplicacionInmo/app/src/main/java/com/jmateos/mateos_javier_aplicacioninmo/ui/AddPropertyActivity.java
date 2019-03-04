package com.jmateos.mateos_javier_aplicacioninmo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jmateos.mateos_javier_aplicacioninmo.R;
import com.jmateos.mateos_javier_aplicacioninmo.UtilToken;
import com.jmateos.mateos_javier_aplicacioninmo.model.PropertyDTO;
import com.jmateos.mateos_javier_aplicacioninmo.response.CategoryId;
import com.jmateos.mateos_javier_aplicacioninmo.response.PropertyResponseOne;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.generator.ServiceGenerator;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.generator.TipoAutenticacion;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.services.PropertyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPropertyActivity extends AppCompatActivity {

    private EditText addTitulo, addDescripcion, addHab, addPrice, addSize, addCod, addDireccion, addCiudad, addProvincia;
    private Button btnAddProperty;
    private List<CategoryId> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        addTitulo = findViewById(R.id.addTitle);
        addDescripcion = findViewById(R.id.addDescription);
        addHab = findViewById(R.id.addRooms);
        addPrice = findViewById(R.id.addPrice);
        addHab = findViewById(R.id.addRooms);
        addSize = findViewById(R.id.addSize);
        addCod = findViewById(R.id.addZipcode);
        addDireccion = findViewById(R.id.addAddress);
        addCiudad = findViewById(R.id.addCity);
        addProvincia = findViewById(R.id.addProvince);
        btnAddProperty = findViewById(R.id.buttonAddProperty);

        btnAddProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyDTO newProperty = new PropertyDTO(
                        addTitulo.getText().toString(),
                        addDescripcion.getText().toString(),
                       Integer.valueOf(addPrice.getText().toString()),
                        Integer.valueOf(addHab.getText().toString()),
                        Integer.valueOf(addSize.getText().toString()),
                        addCod.getText().toString(),
                        addDireccion.getText().toString(),
                        addCiudad.getText().toString(),
                        addProvincia.getText().toString(),
                        "11, 11");

                PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(AddPropertyActivity.this), TipoAutenticacion.JWT);
                Call<PropertyResponseOne> call = service.addProperty(newProperty);

                call.enqueue(new Callback<PropertyResponseOne>() {
                    @Override
                    public void onResponse(Call<PropertyResponseOne> call, Response<PropertyResponseOne> response) {
                        Toast.makeText(AddPropertyActivity.this, "Good",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<PropertyResponseOne> call, Throwable t) {
                        Toast.makeText(AddPropertyActivity.this, "bad",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
