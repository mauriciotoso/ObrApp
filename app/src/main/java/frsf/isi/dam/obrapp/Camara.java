package frsf.isi.dam.obrapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.File;

public class Camara extends AppCompatActivity {

    private final String CARPETA_RAIZ="ObrApp/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misfotos";
    private String path,nombreImagen;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        imagen =findViewById(R.id.imagen);

        if(checkCameraHardware(this)){
            File fileImagen =new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
            Boolean isCreada = fileImagen.exists();

            if(!isCreada) isCreada=fileImagen.mkdirs();
            else {
                nombreImagen=(System.currentTimeMillis()/100)+".jpg";
            }

            path=Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;

            File imagen = new File(path);

            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            getIntent().putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
            startActivityForResult(i,99);
        }

    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
            MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) {
                }
            });
        }

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        imagen.setImageBitmap(bitmap);
    }
}
