package tomerbu.edu.lec18contentproviders;

import java.util.List;

/**
 * Created by tomerbuzaglo on 24/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class Contact {
    private String name;
    private long id;
    private List<String> phones;//טבלה בנפרד
    private List<String> emails;//טבלה בנפרד

    //ctor:
    public Contact(String name, long id, List<String> phones, List<String> emails) {
        this.name = name;
        this.id = id;
        this.phones = phones;
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", phones=" + phones +
                ", emails=" + emails +
                '}';
    }
}
