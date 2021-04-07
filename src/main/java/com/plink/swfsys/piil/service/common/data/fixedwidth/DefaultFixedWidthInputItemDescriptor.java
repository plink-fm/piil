package com.plink.swfsys.piil.service.common.data.fixedwidth;

public class DefaultFixedWidthInputItemDescriptor implements FixedWidthInputItemDescriptor {

    private Integer start;
    private Integer end;
    private String name;
    private String type;

    @Override
    public Integer getStart() {
        return start;
    }

    @Override
    public void setStart(Integer start) {
        this.start = start;
    }

    @Override
    public Integer getEnd() {
        return end;
    }

    @Override
    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
