package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class CommonProblemModel extends CommonModel {
    String problemStr;
    String problemId;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    int status=0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProblemStr() {
        return problemStr;
    }

    public void setProblemStr(String problemStr) {
        this.problemStr = problemStr;
    }

    public CommonProblemModel(String problemStr, String problemId) {
        this.problemStr = problemStr;
        this.problemId = problemId;
    }
}
