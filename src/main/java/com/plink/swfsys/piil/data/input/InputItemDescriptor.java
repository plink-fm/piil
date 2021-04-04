package com.plink.swfsys.piil.data.input;

public class InputItemDescriptor {
    private Integer start;
    private Integer end;
    private String name;
    private String type;

    public InputItemDescriptor() {
    }

    public InputItemDescriptor(Integer start, Integer end, String name, String type) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.type = type;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

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
