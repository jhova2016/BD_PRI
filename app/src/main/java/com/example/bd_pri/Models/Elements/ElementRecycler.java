package com.example.bd_pri.Models.Elements;

import android.widget.TextView;

public class ElementRecycler {

    String Id;
    String Name;
    String Section;
    String Employment;
    String Direction;
    String ElectorKey;
    String Phone;
    String DirectBoss;

    public ElementRecycler(String id, String name, String section, String employment, String direction, String electorKey, String phone, String directBoss) {
        Id = id;
        Name = name;
        Section = section;
        Employment = employment;
        Direction = direction;
        ElectorKey = electorKey;
        Phone = phone;
        DirectBoss = directBoss;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getEmployment() {
        return Employment;
    }

    public void setEmployment(String employment) {
        Employment = employment;
    }

    public String getElectorKey() {
        return ElectorKey;
    }

    public void setElectorKey(String electorKey) {
        ElectorKey = electorKey;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDirectBoss() {
        return DirectBoss;
    }

    public void setDirectBoss(String directBoss) {
        DirectBoss = directBoss;
    }
}
