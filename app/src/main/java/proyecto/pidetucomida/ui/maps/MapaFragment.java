package proyecto.pidetucomida.ui.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import proyecto.pidetucomida.R;

public class MapaFragment extends Fragment{

    private OnMapReadyCallback callback = new OnMapReadyCallback(){
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng Lima = new LatLng(-12.0459497, -77.0307403);
            LatLng Miraflores = new LatLng(-12.117809, -77.0333792);
            LatLng Lince = new LatLng(-12.0808434, -77.0360061);
            LatLng Surco = new LatLng(-12.1045325, -76.985273);
            LatLng SantaAnita = new LatLng(-12.0491787, -76.9747201);
            googleMap.addMarker(new MarkerOptions().position(Lima).title("Lima-Peru"));
            googleMap.addMarker(new MarkerOptions().position(Miraflores).title("Miraflores"));
            googleMap.addMarker(new MarkerOptions().position(Lince).title("Lince"));
            googleMap.addMarker(new MarkerOptions().position(Surco).title("Surco"));
            googleMap.addMarker(new MarkerOptions().position(SantaAnita).title("Santa Anita"));
          //  googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lima,13));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

}