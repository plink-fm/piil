package com.plink.swfsys.piil.service.common.data;

import com.plink.swfsys.piil.service.common.data.field.Field;
import com.plink.swfsys.piil.service.data.InputItem;

import java.util.HashMap;
import java.util.Map;

public class DefaultInputItem implements InputItem {

    private Map<String, Field> fields = new HashMap<>();

    public DefaultInputItem() {
    }

    public void addField(String key, Field field) {
        fields.put(key, field);
    }

    public Field getField(String key) {
        return fields.get(key);
    }

}
