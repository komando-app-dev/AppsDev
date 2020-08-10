package tomerbu.edu.lec6intents;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tomerbuzaglo on 13/07/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class Person implements Parcelable {
    //Serializable - לא יעיל! תהליך אוטומטי -
    //Serialization:הפיכת אובייקט לבינארי
    //De-Serialization:הפיכת בינארי חזרה לאובייקט

    //Dog implements Parcelable

    //properties:
    private String firstName;
    private String lastName;

    //constructors:
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }



    //Person p = new Person("", "")
    //p.writeToParcel(...)
    //Serialization: from Person object to a Parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //write by a specific order:
        parcel.writeString(firstName);
        parcel.writeString(lastName);

    }

    //Parcelable constructor:
    //Parcel is like a Object Bag:
    protected Person(Parcel in) {
        //1)the first object in the parcel is the firstName
        firstName = in.readString();
        //2)the 2nd object in the parcel is the lastName
        lastName = in.readString();
    }







    //ISP:
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            //helper method that takes a Parcelable:
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            //a method that takes an int and returns an empty array of Person
            return new Person[size];
        }
    };

    //getters and setters:
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //toString:
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
       //MosheDoe
        //return a Hash - returns 0 (ignored)
        return 0;
    }

    //toJson->String

    //fromJson(String json)
}
