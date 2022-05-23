package model;

public class BooksSubscribers {
    int id;
    int idBook;
    int idSubscriber;

    public BooksSubscribers() {

    }
    public BooksSubscribers(int idBook, int idSubscriber) {
        this.idBook = idBook;
        this.idSubscriber = idSubscriber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdSubscriber() {
        return idSubscriber;
    }

    public void setIdSubscriber(int idSubscriber) {
        this.idSubscriber = idSubscriber;
    }
}
