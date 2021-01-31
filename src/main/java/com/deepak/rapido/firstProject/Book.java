package com.deepak.rapido.firstProject;

public class Book {
  private long isbn;
  private String title;

  public long getIsbn() {
    return isbn;
  }
  public void setIsbn(final long isbn) {
    this.isbn = isbn;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(final String title) {
    this.title = title;
  }
  public Book() {
    //This is the default constructor
  }
  public Book(long isbn, final String title) {
    this.isbn = isbn;
    this.title = title;
  }
}
