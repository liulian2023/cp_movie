package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.info;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class AbstractEntity implements Serializable {



    protected Long id;

    protected String createdUser;

    protected Date createdDate;

    protected String createdIp;

    protected String lastModifiedUser;
    protected Date lastModifiedDate;

    protected String lastModifiedIp;
    private int isDelete;

    public AbstractEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedUser() {
        return this.createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedIp() {
        return this.createdIp;
    }

    public void setCreatedIp(String createdIp) {
        this.createdIp = createdIp;
    }

    public String getLastModifiedUser() {
        return this.lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedIp() {
        return this.lastModifiedIp;
    }

    public void setLastModifiedIp(String lastModifiedIp) {
        this.lastModifiedIp = lastModifiedIp;
    }

    public int getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return isDelete == that.isDelete &&
                Objects.equals(id, that.id) &&
                Objects.equals(createdUser, that.createdUser) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdIp, that.createdIp) &&
                Objects.equals(lastModifiedUser, that.lastModifiedUser) &&
                Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
                Objects.equals(lastModifiedIp, that.lastModifiedIp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createdUser, createdDate, createdIp, lastModifiedUser, lastModifiedDate, lastModifiedIp, isDelete);
    }
}
