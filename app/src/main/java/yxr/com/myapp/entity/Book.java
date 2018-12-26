package yxr.com.myapp.entity;


import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {
    private String name;
    private String author;
    private int pages;
    private double price;

     @Override
    public String toString() {
        return name+" "+author+" "+pages+" "+price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
