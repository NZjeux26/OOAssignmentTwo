package com.demo.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book extends Libitem{

    private String author;
    private int numPages;

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String toString(){
        String S;
        if (onLoan){
            S = String.format(
                    """
                            -------------------------------------\s
                            Type: %s\s
                            Title: %s\s
                            ID: %s\s
                            Year: %s\s
                            Average rating: %.1f\s
                            Number of reviews: %s\s
                            Status: %s\s
                            Due date: %s\s
                            Author: %s\s
                            Number of pages: %s\s
                            Max number of days for borrowing: %s\s
                            """,type,title,id,year,getAvgRating(),numReviews,getBorrowing(),getDueDate(),getAuthor(),getNumPages(),getMaxBorrow());
        }
        else{
            S = String.format(
                    """
                            -------------------------------------\s
                            Type: %s\s
                            Title: %s\s
                            ID: %s\s
                            Year: %s\s
                            Average rating: %.1f\s
                            Number of reviews: %s\s
                            Status: %s\s
                            Author: %s\s
                            Number of pages: %s\s
                            Max number of days for borrowing: %s\s
                            """,type,title,id,year,getAvgRating(),numReviews,getBorrowing(),getAuthor(),getNumPages(),getMaxBorrow());
        }
        return S;
    }

    @Override
    public long getMaxBorrow() {
        return super.getMaxBorrow() + 28;
    }

    @Override
    public String getDueDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(LocalDate.now().plusDays(getMaxBorrow()));
    }

}
