package com.plink.swfsys.piil.data.input.impl;

import com.plink.swfsys.piil.data.input.impl.field.Field;

import java.util.HashMap;
import java.util.Map;

public class InputItem {

    private Map<String, Field> fields = new HashMap<>();

    public InputItem() {

    }

    public void addField(String key, Field field) {
        fields.put(key, field);
    }

    public Field getField(String key) {
        return fields.get(key);
    }

    // TODO: this needs to come from properties file through InputItemSpecification
    public String getTaxRate() {
        return "0.07775";
    }
}
