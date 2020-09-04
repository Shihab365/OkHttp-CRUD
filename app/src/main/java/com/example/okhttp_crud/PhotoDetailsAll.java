package com.example.okhttp_crud;

public class PhotoDetailsAll {
    String Name, Description, Photo;

    public PhotoDetailsAll(String Name, String Description, String Photo) {
        this.setName(Name);
        this.setDescription(Description);
        this.setPhoto(Photo);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
