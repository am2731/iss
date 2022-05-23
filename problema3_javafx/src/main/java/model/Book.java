package model;

public class Book extends Entity<Integer> {

    public String title;
    public String author;
    public String status;
    public int idd;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.status = Status.AVAILABLE.name();
    }

    public Book(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getIdd() {
        return idd;
    }

    public void setIdd(int id) {
        this.idd = id;
    }
}
