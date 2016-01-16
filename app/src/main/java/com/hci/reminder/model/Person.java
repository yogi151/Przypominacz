package com.hci.reminder.model;

import java.io.Serializable;
import java.net.URL;

public class Person implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id w serwisie Filmweb
    private int id = 0;

    // Rola pe³niona w filmie
    private String role = null;

    // Dodatkowe informacje, np. "Niewymieniony w czo³ówce", "g³os" etc.
    private String info = null;

    // Imiê i nazwisko
    private String name = null;

    // Adres zdjêcia
    private URL photoUrl = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(URL photo) {
        this.photoUrl = photo;
    }
}