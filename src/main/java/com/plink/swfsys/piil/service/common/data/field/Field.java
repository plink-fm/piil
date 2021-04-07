package com.plink.swfsys.piil.service.common.data.field;

public interface Field<T> {

    FieldType getFieldType();

    void setData(T data);

    T getData();
}
