package com.example.connet;

public class Scheda {
    private String user;
    private String title;
    private String description;
    private String imageUrl;
    private boolean flag;
    private String nameFile;

    public Scheda(){}

    public Scheda(String user,String title,String description,String imageUrl, String nameFile,boolean flag){
        this.user =user;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.flag = flag;
        this.nameFile = nameFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getTitle(){return title;}

    public void setTitle(String title){this.title = title;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public String getImageUrl(){return imageUrl;}

    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}


