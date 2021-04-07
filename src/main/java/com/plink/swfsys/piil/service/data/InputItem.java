package com.plink.swfsys.piil.service.data;

import com.plink.swfsys.piil.service.common.data.field.Field;

public interface InputItem {

    void addField(String key, Field field);

    Field getField(String key);
}
