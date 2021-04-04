package com.plink.swfsys.piil.data.input.impl.field;

public interface Field<T> {

    FieldType getFieldType();

    //void setData(T data);

    T getData();
}
