package com.example.maciapek.przypominacz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FilmyAdapter extends ArrayAdapter<String>{

    FilmyAdapter(Context context, String[] title){
        super(context,R.layout.filmy_row_layout, title);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        FilmyAdapterViewHolder holder;
        if (convertView == null){

            holder = new FilmyAdapterViewHolder();
            LayoutInflater filmInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = filmInflater.inflate(R.layout.filmy_row_layout, parent, false);
            holder.filmTitle = (TextView)convertView.findViewById(R.id.filmTitle);
            holder.filmCover = (ImageView)convertView.findViewById(R.id.filmCover);
            holder.title = getItem(position);

            convertView.setTag(holder);
        }else{
            holder = (FilmyAdapterViewHolder)convertView.getTag();
        }

        holder.title = getItem(position);
        holder.filmTitle.setText(holder.title);


        //TODO: zmienić na plakat filmu
        //holder.filmCover.setImageResource(R.drawable.avatar);
        holder.position = position;
        new ThumbnailTask(position, holder).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://1.fwcdn.pl/po/16/44/671644/7700784.3.jpg");

        return convertView;
    }

    private static class ThumbnailTask extends AsyncTask<String,Void,Bitmap> {
        private int mPosition;
        private FilmyAdapterViewHolder mHolder;

        public ThumbnailTask(int position, FilmyAdapterViewHolder holder) {
            mPosition = position;
            mHolder = holder;
        }


        @Override
        protected Bitmap doInBackground(String... params) {
            String param = params[0];
            Bitmap bitmap = getBitmapFromURL(param);
            return getResizedBitmap(bitmap, 200, 200);
        }

        public Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //connection.setDoInput(true);
                //connection.connect();
                InputStream input = new BufferedInputStream(connection.getInputStream());
                return BitmapFactory.decodeStream(input);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
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

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (mHolder.position == mPosition) {
                mHolder.filmCover.setImageBitmap(bitmap);
            }
        }
    }

    private static class FilmyAdapterViewHolder {
        public String title;
        public TextView filmTitle;
        public ImageView filmCover;
        public int position;
    }


}
