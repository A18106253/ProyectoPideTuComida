package proyecto.pidetucomida.actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

    String idcombo="";
    String email="";
    String correo = "";
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

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            correo = extras.getString("email");
            if (!correo.equals("lopeztomaylla1299@gmail.com")) {
                fab.setVisibility(View.INVISIBLE);
            }
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =new Intent(MenuActivity.this,RegistrarProductos.class);
                intent.putExtra("email", correo);
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



      //  Toast.makeText(getApplicationContext(), "ID CATEGORIA!"+idcombo, Toast.LENGTH_SHORT).show();

        Intent idcombito = getIntent();
        String verificaridcombo = idcombito.getStringExtra("seleccion");

        //boolean mifragement=false;
        if (verificaridcombo!= null && idcombito.equals("1")){
            Toast.makeText(getApplicationContext(), "ID CATEGORIA!"+idcombito, Toast.LENGTH_SHORT).show();
            addFragment(new PlatosFragment(),false,"PlatosFragment");
            /*
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.nav_platos, new PlatosFragment());
            */

           // mifragement=true;
            //tx.commit();
           // drawer.closeDrawer(GravityCompat.START);
            //return ;

        }else if(verificaridcombo!= null && idcombo.equals("2")){
            Toast.makeText(getApplicationContext(), "ID CATEGORIA!"+idcombito, Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.nav_bebidas);
            //addFragment(new BebidasFragment(),false,"BebidasFragment");
           // FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            //tx.replace(R.id.nav_bebidas, new BebidasFragment());
           // mifragement=true;

            //tx.commit();
           // drawer.closeDrawer(GravityCompat.START);

        }else if(verificaridcombo!= null && idcombo.equals("3")){
            Toast.makeText(getApplicationContext(), "ID CATEGORIA!"+idcombito, Toast.LENGTH_SHORT).show();
            addFragment(new OfertasFragment(),true,"OfertasFragment");
           // FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            //tx.add(R.id.nav_ofertas, new OfertasFragment());
           // mifragement=true;

          //  tx.commit();
           // drawer.closeDrawer(GravityCompat.START);

        }




    }

    public void addFragment(Fragment fragment, boolean addToBackStack,String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.nav_host_fragment, fragment,tag);
        ft.commitAllowingStateLoss();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false);
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