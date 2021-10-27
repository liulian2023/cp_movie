

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.Objects;

public class GfSureBetPopMeldol extends CommonModel implements Serializable {

    String name;
    String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GfSureBetPopMeldol(String name) {
        this.name = name;
    }

    public GfSureBetPopMeldol(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GfSureBetPopMeldol that = (GfSureBetPopMeldol) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }

    @Override
    public String toString() {
        return "GfSureBetPopMeldol{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public GfSureBetPopMeldol() {

    }
}
