package com.demo.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Journals extends Libitem{
    private int volume;
    private int number;

    public int getVolume() {
        return volume;
    }

    public int getNumber() {
        return number;
    }

    public void setVolume(int volume) { this.volume = volume; }

    public void setNumber(int number) {
        this.number = number;
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
                            Volume: %s\s
                            Number: %s\s
                            Max number of days for borrowing: %s\s
                            """,type,title,id,year,getAvgRating(),numReviews,getBorrowing(),getDueDate(),getVolume(),getNumber(),getMaxBorrow());
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
                            Volume: %s\s
                            Number: %s\s
                            Max number of days for borrowing: %s\s
                            """,type,title,id,year,getAvgRating(),numReviews,getBorrowing(),getVolume(),getNumber(),getMaxBorrow());
        }
        return S;
    }

    @Override
    public long getMaxBorrow() {
        return super.getMaxBorrow() + 14;
    }

    @Override
    public String getDueDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(LocalDate.now().plusDays(getMaxBorrow()));
    }
}
