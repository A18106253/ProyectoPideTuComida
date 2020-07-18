package proyecto.pidetucomida.actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import proyecto.pidetucomida.R;
import proyecto.pidetucomida.ui.bebidas.BebidasFragment;
import proyecto.pidetucomida.ui.ofertas.OfertasFragment;
import proyecto.pidetucomida.ui.platos.PlatosFragment;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    int idcombo=0;
    String email="";
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.



        FloatingActionButton fab = findViewById(R.id.fab);







        Bundle recibidemail=getIntent().getExtras();
        if (recibidemail !=null){
            email=recibidemail.getString("email");
            if(!email.equals("lopeztomaylla1299@gmail.com")){

                fab.setVisibility(View.INVISIBLE);

                // fab.setVisibility(View.VISIBLE);
            }

        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =new Intent(MenuActivity.this,RegistrarProductos.class);
                startActivity(intent);
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                 */
            }
        });


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_platos,R.id.nav_bebidas, R.id.nav_ofertas,R.id.nav_carrito,R.id.nav_empresa,R.id.nav_desarrolladores,R.id.nav_maps,R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        /*
        Bundle seleccioncombo=getIntent().getExtras();
        if(seleccioncombo != null) {
            idcombo =Integer.parseInt( seleccioncombo.getString("seleccion"));
        }

*/


        Toast.makeText(getApplicationContext(), "ID CATEGORIA!"+idcombo, Toast.LENGTH_SHORT).show();

        if (idcombo==1){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.nav_platos, new PlatosFragment());
            tx.commit();

        }else if(idcombo==2){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.nav_bebidas, new BebidasFragment());
            tx.commit();

        }else if(idcombo==3){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.nav_ofertas, new OfertasFragment());
            tx.commit();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}