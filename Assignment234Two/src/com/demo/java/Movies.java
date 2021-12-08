package com.demo.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Movies extends Libitem{
    private String director;

    public String toString(){
        String S;
        if (onLoan){
            S = String.format("""
                        -------------------------------------\s
                        Type: %s\s
                        Title: %s\s
                        ID: %s\s
                        Year: %s\s
                        Average rating: %.1f\s
                        Number of reviews: %s\s
                        Status: %s\s
                        Due Date: %s\s
                        Director: %s\s
                        Max number of days for borrowing: %s\s
                        """, type, title, id, year, getAvgRating(), numReviews, getBorrowing(), getDueDate(), director, getMaxBorrow());
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
                            Director: %s\s
                            Max number of days for borrowing: %s\s
                            """, type, title, id, year, getAvgRating(), numReviews, getBorrowing(), director, getMaxBorrow());
        }
        return S;
    }

    public void setDirector(String director) { this.director = director; }

    @Override
    public long getMaxBorrow() {
        return super.getMaxBorrow() + 7;
    }

    @Override
    public String getDueDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(LocalDate.now().plusDays(getMaxBorrow()));
    }
}
