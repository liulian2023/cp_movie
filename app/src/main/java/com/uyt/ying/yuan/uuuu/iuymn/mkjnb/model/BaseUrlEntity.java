

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class BaseUrlEntity {

    /**
     * appRequestDomains : [{"createdDate":1593825315000,"createdUser":"kugua","domain":"http://148.66.6.18:8183/web/ws","id":5,"isDelete":0,"lastModifiedDate":1593825315000,"lastModifiedUser":"kugua","listsort":1,"status":1},{"createdDate":1593825330000,"createdUser":"kugua","domain":"http://148.66.6.19:8183/web/ws","id":6,"isDelete":0,"lastModifiedDate":1593825330000,"lastModifiedUser":"kugua","listsort":2,"status":1},{"createdDate":1593825341000,"createdUser":"kugua","domain":"http://148.66.6.20:8183/web/ws","id":7,"isDelete":0,"lastModifiedDate":1593825341000,"lastModifiedUser":"kugua","listsort":3,"status":1},{"createdDate":1593825351000,"createdUser":"kugua","domain":"http://148.66.6.21:8183/web/ws","id":8,"isDelete":0,"lastModifiedDate":1593825351000,"lastModifiedUser":"kugua","listsort":4,"status":1},{"createdDate":1593825362000,"createdUser":"kugua","domain":"http://148.66.6.22:8183/web/ws","id":9,"isDelete":0,"lastModifiedDate":1593825362000,"lastModifiedUser":"kugua","listsort":5,"status":1}]
     * message : success
     * status : success
     */

    private String message;
    private String status;
    private List<AppRequestDomainsBean> appRequestDomains;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AppRequestDomainsBean> getAppRequestDomains() {
        return appRequestDomains;
    }

    public void setAppRequestDomains(List<AppRequestDomainsBean> appRequestDomains) {
        this.appRequestDomains = appRequestDomains;
    }

    public static class AppRequestDomainsBean {
        /**
         * createdDate : 1593825315000
         * createdUser : kugua
         * domain : http://148.66.6.18:8183/web/ws
         * id : 5
         * isDelete : 0
         * lastModifiedDate : 1593825315000
         * lastModifiedUser : kugua
         * listsort : 1
         * status : 1
         */

        private long createdDate;
        private String createdUser;
        private String domain;
        private long id;
        private int isDelete;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private int listsort;
        private int status;
        private long startTime;
        private long endTime;
        private boolean isSuccess=true;

        public boolean isSuccess() {
            return isSuccess;
        }

        public void setSuccess(boolean success) {
            isSuccess = success;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        private boolean isCheck=false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public int getListsort() {
            return listsort;
        }

        public void setListsort(int listsort) {
            this.listsort = listsort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
