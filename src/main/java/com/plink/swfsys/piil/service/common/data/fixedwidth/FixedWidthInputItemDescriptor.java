package com.plink.swfsys.piil.service.common.data.fixedwidth;

import com.plink.swfsys.piil.service.data.InputItemDescriptor;

public interface FixedWidthInputItemDescriptor extends InputItemDescriptor {

    Integer getStart();

    void setStart(Integer start);

    Integer getEnd();

    void setEnd(Integer end);

}
