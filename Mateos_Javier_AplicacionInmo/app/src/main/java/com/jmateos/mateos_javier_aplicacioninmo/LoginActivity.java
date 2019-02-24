package com.jmateos.mateos_javier_aplicacioninmo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jmateos.mateos_javier_aplicacioninmo.model.LoginResponse;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.generator.ServiceGenerator;
import com.jmateos.mateos_javier_aplicacioninmo.retrofit.services.LoginService;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  Button btnLogin;
  TextView textSignup;
  EditText email, password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_login);

    btnLogin = findViewById(R.id.btnSignup);
    textSignup = findViewById(R.id.textSignup);
    email = findViewById(R.id.editUser);
    password = findViewById(R.id.editPwd);

    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String username_txt = email.getText().toString();
        String password_txt = password.getText().toString();

        String credentials = Credentials.basic(username_txt, password_txt);

        LoginService service = ServiceGenerator.createService(LoginService.class);
        Call<LoginResponse> call = service.doLogin(credentials);

        call.enqueue(new Callback<LoginResponse>() {
          @Override
          public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            if (response.code() != 201) {
              // error
              Log.e("RequestError", response.message());
              Toast.makeText(LoginActivity.this, "Error de petición", Toast.LENGTH_SHORT).show();
            } else {
              // exito
              // Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_LONG).show();
                            /*
                                Pasos:
                                    1) Almacenar el token donde corresponda.
                                    2) Lanzar el siguiente Activity.
                             */
              // ServiceGenerator.jwtToken = response.body().getToken();
                            /*SharedPreferences sharedPreferences =
                                    getSharedPreferences(getString(R.string.sharedpreferences_filename),
                                            Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(getString(R.string.jwt_key), response.body().getToken());
                            editor.commit();*/
              UtilToken.setToken(LoginActivity.this, response.body().getToken());
              UtilToken.setIdUser(LoginActivity.this, response.body().getUser().getId());

              //startActivity(new Intent(LoginActivity.this, HuertoActivity.class));
              startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
              finish();
            }
          }

          @Override
          public void onFailure(Call<LoginResponse> call, Throwable t) {
            Log.e("NetworkFailure", t.getMessage());
            Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
          }
        });


      }
    });

    textSignup.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intentRegistro = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intentRegistro);
        finish();
      }
    });
  }
}
