package com.example.maciapek.przypominacz;

import android.content.Context;
import android.content.res.Resources;
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

    private  String[] coverUri;
    private String[] releaseDate;
    FilmyAdapter(Context context, String[] title, String[] coverUri, String[] releaseDate){
        super(context,R.layout.filmy_row_layout, title);
        this.coverUri = coverUri;
        this.releaseDate = releaseDate;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        FilmyAdapterViewHolder holder;
        if (convertView == null){

            holder = new FilmyAdapterViewHolder();
            LayoutInflater filmInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = filmInflater.inflate(R.layout.filmy_row_layout, parent, false);
            holder.filmTitle = (TextView)convertView.findViewById(R.id.filmTitle);
            holder.releaseDate = (TextView)convertView.findViewById(R.id.releaseDate);
            holder.filmCover = (ImageView)convertView.findViewById(R.id.filmCover);
            holder.title = getItem(position);
            holder.date = releaseDate[position];
            holder.coverUri = coverUri[position];

            convertView.setTag(holder);
        }else{
            holder = (FilmyAdapterViewHolder)convertView.getTag();
        }

        holder.title = getItem(position);
        holder.filmTitle.setText(holder.title);
        holder.date = releaseDate[position];
        holder.releaseDate.setText("Premiera: "  + holder.date);
        holder.coverUri = coverUri[position];

        holder.position = position;
        new ThumbnailTask(position, holder).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, holder.coverUri);

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
            return getBitmapFromURL(param);
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

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (mHolder.position == mPosition) {
                mHolder.filmCover.setImageBitmap(bitmap);
            }
        }
    }

    private static class FilmyAdapterViewHolder {
        public String title;
        public String date;
        public TextView releaseDate;
        public TextView filmTitle;
        public ImageView filmCover;
        public String coverUri;
        public int position;
    }


}
