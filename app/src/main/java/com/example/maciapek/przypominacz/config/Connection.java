package com.example.maciapek.przypominacz.config;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.example.maciapek.przypominacz.api.ResponseAnalyzer;
import com.example.maciapek.przypominacz.model.Person;

public class Connection {

	private String methods;

    private String methodName;

    private String signature;

    private final String VERSION;

    private final String APPID;

    public Connection() {
        VERSION = "1.0";
        APPID = "android";
        methods = null;
        methodName = null;
        signature = null;
    }

    /**
     * Ustawienie metody zdalnej, kt�ra b�dzie wywo�ana
     * @param method Dane metody postaci: nazwa_metody [parametry]
     * @return Powodzenie / niepowodzenie
     */
    public boolean setMethod(String method) {

        if (this.setSignature(method)) {
            this.methods = method + "\\n";

            //zapis nazwy metody
            String[] methodParts = method.split("\\s");
            methodName = methodParts[0];
            return true;
        }
        return false;
    }

    /**
     * Przygotowanie sygnatury
     * @param method Dane metody zdalnej postaci: nazwa_metody [parametry]
     * @return Powodzenie / niepowodzenie
     */
    private boolean setSignature(final String method) {

        final String sig = method + "\\n" + this.APPID + Config.KEY;

       // this.signature = this.VERSION + "," + DigestUtils.md5Hex(sig);
        String s = new String(Hex.encodeHex(DigestUtils.md5(sig)));
        this.signature = this.VERSION + "," + s;
        
        return true;
    }

    /**
     * Przygotowanie parametr�w dla zapytania
     * @return Parametry dla url
     */
    private String prepareParams() {

        if (this.methods == null || this.signature == null) return null;

        String qs;
        try {
            qs = "methods=" + URLEncoder.encode(this.methods, "UTF-8");
            qs += "&signature=" + URLEncoder.encode(this.signature, "UTF-8");
            qs += "&version=" + URLEncoder.encode(this.VERSION, "UTF-8");
            qs += "&appId=" + URLEncoder.encode(this.APPID, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
        return qs;
    }

    /**
     * Ustawienie wszystkich element�w otrzymanych w odpowiedzi i zwr�cenie tej, o kt�r� jest zapytanie
     * @return Dane, kt�re zwracane s� dla wywo�anej metody zdalnej
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Object prepareResponse() {

        String response = getResponse();
        if (response == null) return null;

        ResponseAnalyzer ra = new ResponseAnalyzer(response);
        Object res = null;

        ArrayList<String> strList;
        ArrayList<ArrayList> list;

        if(methodName.equals("getFilmDescription")) {
        	strList = (ArrayList)ra.analyze();
        	res = strList.get(0);
        }
        
        if(methodName.equals("getFilmReview")) {
        	strList = (ArrayList)ra.analyze();
        	res = strList.get(3);
        }
        
        if(methodName.equals("getFilmPersons")) {
        		list = (ArrayList)ra.analyze();
                ArrayList<Person> personArrayList = new ArrayList();
                for (ArrayList personData : list) {
                    Person person = new Person();
                    try {
                        int id = Integer.parseInt(personData.get(0).toString());
                        person.setId(id);
                        String role = personData.get(1) != null ? personData.get(1).toString() : "";
                        person.setRole(role);
                        String info = personData.get(2) != null ? personData.get(2).toString() : "";
                        person.setInfo(info);
                        person.setName(personData.get(3).toString());
                        URL photoUrl = null;
                        photoUrl = new URL(personData.get(4) != null ? Config.URL_PERSON + personData.get(4).toString() : "");
                        person.setPhotoUrl(photoUrl);
                        personArrayList.add(person);
                    }
                    catch (MalformedURLException e) {
                    }
                }
                res = personArrayList;
        	}
        
        if(methodName.equals("getFilmInfoFull")) {
        	res = (ArrayList)ra.analyze();
        }
        
        if(methodName.equals("getPopularFilms")) {
        	res = (ArrayList)ra.analyze();
        }
        
        if(methodName.equals("getUpcommingFilms")) {
        	res = (ArrayList)ra.analyze();
        }

        return res;
    }

   /* private class GetJsonAsync extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Do stuff before the operation
        }

        @Override
        protected String doInBackground(String... params){
            return getResp();
        }

        @Override
        protected void onPostExecute(String result) {
            // Do stuff after the operation
        }
    }*/
    /**
     * Uzyskanie odpowiedzi z serwera
     * @return Odpowiedz serwera
     */


    public String getResponse(){
        String resp = "";

        String params = this.prepareParams();
        System.out.println(params);
        if (params == null) return null;

        try {
            URL url = new URL(Config.API_SERVER + params);
            URLConnection con = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            /**
             * Pierwsza linia jest komunikatem o stanie zapytania, mo�liwe opcje:
             * "ok" - pozosta�e linie s� wynikiem
             * "err" - pozosta�a linia zawiera opis b��du
             */
            String stateStr = in.readLine();
            boolean state = stateStr.equals("ok");

            String line;
            while ((line = in.readLine()) != null) {
                resp += line;
            }
            in.close();

            if (!state) {
                return null;
            }
            else if (resp.equals("exc NullPointerException")) {
                return null;
            }
        }
        catch (MalformedURLException e) {
            return null;
        }
        catch (IOException e) {
            return null;
        }
        return resp;
    }
    }




