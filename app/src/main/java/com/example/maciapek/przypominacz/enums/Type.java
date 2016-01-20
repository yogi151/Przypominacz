package com.example.maciapek.przypominacz.enums;

public enum Type {
    FILM(0), 
    SERIES(1),
    POPULAR(2),
    POPULARSERIES(3),
    UPCOMMING(4),
    OBSERVED(5),
    OBSERVEDSERIES(6);
    

    private int code;

    Type(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    public static Type getByCode(int code) {
    	if(code == 0 )
    		return FILM;
    	if(code == 1)
    		return SERIES;
    	return null;
    }
}
