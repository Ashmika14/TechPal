package com.example.techpal;

import java.io.Serializable;

public class Workmodel implements Serializable {
   String classname;
   String date;
   String delete;
   String detail;

   String docId;

   int number;

   String part;
   String sdate;
   String secssion;
   String subject;
   String teacher;
   String time;
   String uri;
   String work;

    public Workmodel() {
    }

    public Workmodel(String classname, String date, String delete, String detail, String docId, int number, String part, String sdate, String secssion, String subject, String teacher, String time, String uri, String work) {
        this.classname = classname;
        this.date = date;
        this.delete = delete;
        this.detail = detail;
        this.docId = docId;
        this.number = number;
        this.part = part;
        this.sdate = sdate;
        this.secssion = secssion;
        this.subject = subject;
        this.teacher = teacher;
        this.time = time;
        this.uri = uri;
        this.work = work;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getSecssion() {
        return secssion;
    }

    public void setSecssion(String secssion) {
        this.secssion = secssion;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
