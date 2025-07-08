// ---------------------------------------------
// Assignment 2
// Questions: part 1, part2, part3
// Written by: Alexandre Godfroy
// ---------------------------------------------s


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a book with details including title, author, price, ISBN, genre, and publication year.
 * Implements Serializable to be able to convert the object to binary.
 */
public class Book implements Serializable {
    private String title;
    private String author;
    private double price;
    private String isbn;
    private String genre;
    private int year;

    /**
     * Constructs a new Book with the specified title, author, price, ISBN, genre, and year.
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @param price  the price of the book
     * @param isbn   the ISBN of the book
     * @param genre  the genre of the book
     * @param year   the publication year of the book
     */
    public Book(String title, String author, double price, String isbn, String genre, int year) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }

    /**
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the new author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the price of the book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the book.
     *
     * @param price the new price of the book
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn the new ISBN of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre the new genre of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
    /**
     * @return the year of publication of the book
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the publication year of the book.
     *
     * @param year the new publication year of the book
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns a string of the attributes of the book (title, author, price, ISBN, genre, year).
     *
     * @return formatted string with the book's details
     */
    public String toString() {
        return "Book title: " + title + "\nAuthor: " + author +
                "\nPrice: " + price + "$\nISBN: " + isbn + "\nGenre: " +
                genre + "\nYear: " + year;
    }

    /**
     * Compares this book to another object for equality. Two books are considered equal if
     * they have the same title, author, price, ISBN, genre, and year.
     *
     * @param o the object to compare this book with
     * @return true if the object is the same as this book, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(price, book.price) == 0 && Objects.equals(isbn, book.isbn) && year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }
}
