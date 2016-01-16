package com.hci.reminder.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import java.util.ArrayList;

public class ResponseAnalyzer {
    
    /**
     * OdpowiedŸ serwera w postaci elementu Json przeznaczona do analizy
     */
    private JsonElement reposnse;
    
    public ResponseAnalyzer(String response) {
        
        //informacja o czasie generowania + spacja
        String timeInfo = response.substring(response.lastIndexOf(" "));
        response = response.replace(timeInfo, "");
        
        JsonStreamParser parser = new JsonStreamParser(response);
        if (parser.hasNext()) {
            this.reposnse = parser.next();
        }
    }
    
    /**
     * Analizuje element Json buduj¹c na jego podstawie obiekt
     * @param elementStr Analizowany element
     * @return Obiekt String, ArrayList, ew. null
     */
    private Object getData(JsonElement element) {
        if (element.isJsonNull()) return null;
        else if (element.isJsonArray()) {
            ArrayList<Object> data = new ArrayList<Object>();
            JsonArray ja = element.getAsJsonArray();
            for (JsonElement e : ja) {
                data.add(this.getData(e));
            }
            return data;
        }
        else return element.getAsString();
    }

    /**
     * Rozpoczêcie analizy
     * @return Lista obiektów (String, ArrayList, ew. null)
     */
    public Object analyze() {
        return this.getData(this.reposnse);
    }
}