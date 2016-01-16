package com.example.maciapek.przypominacz;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FilmView extends Fragment {

        private Boolean b = true;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootview = inflater.inflate(R.layout.film_layout, container, false);
            Bundle bundle = getArguments();

            TextView title = (TextView)rootview.findViewById(R.id.engTitle);
            title.setText(bundle.getCharSequence("title"));


            final ImageView icon = (ImageView)rootview.findViewById(R.id.addOrRemove);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: dodanie/usunięcie z obserwowanych i zmiana ikony
                    //TODO: do ifa sprawdzenie warunku czy w obserwowanych
                    if (b) {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.added, Toast.LENGTH_SHORT).show();
                        icon.setImageResource(R.drawable.minus);
                        b = false;
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.removed, Toast.LENGTH_SHORT).show();
                        icon.setImageResource(R.drawable.plus);
                        b = true;
                    }
                }
            });

        return rootview;

        }

    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream input = new BufferedInputStream(connection.getInputStream());
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return getResizedBitmap(bitmap, 200, 200);

        } catch (IOException e) {
            e.printStackTrace();
            return BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.reel);
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }
}
