package com.demo.java;

import java.util.ArrayList;

public class Libitem {

    protected String type;
    protected int id;
    protected String title;
    protected int year;
    protected float avgRating = 0.0F;// need method to calculate the avg
    protected int numReviews = 0;
    protected long maxBorrow;
    protected String borrowing;
    protected boolean onLoan = false;
    protected String dueDate;
    protected static ArrayList<Float> ratings = new ArrayList<>();

    public long getMaxBorrow() { return maxBorrow; }
    public String getDueDate(){ return dueDate; }

    public String getBorrowing(){//For tostring to display something other than false or true
        if(onLoan){
            return "On loan";
        }
        else return "available";
    }

    public float getAvgRating(){//Calculates the avg value of the object.
        float sum = 0.0F;

        for (Float rating : ratings) {
            sum = sum + rating;
        }

        avgRating = sum / ratings.size();

        if(Float.isNaN(avgRating)){//Exception catching, since getAvgRating is active before even getting a rating, a rating of zero returns NaN.
            avgRating = 0.0f;
        }

        if (avgRating > 10.0F){//If the avg goes over 10 then 10 will be displayed as the max.
            avgRating = 10.0F;
        }
        return avgRating;
    }

}
