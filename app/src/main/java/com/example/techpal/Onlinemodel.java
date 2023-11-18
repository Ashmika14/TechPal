package com.example.techpal;
public class Onlinemodel {
   String classname;
   String classtime;
   String date;
   String days;
   String delete;
   String docId;
   String link;
  int number;
   String teacher;
   String time;


    public Onlinemodel() {
    }

    public Onlinemodel(String classname, String classtime, String date, String days, String delete, String docId, String link, int number, String teacher, String time) {
        this.classname = classname;
        this.classtime = classtime;
        this.date = date;
        this.days = days;
        this.delete = delete;
        this.docId = docId;
        this.link = link;
        this.number = number;
        this.teacher = teacher;
        this.time = time;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClasstime() {
        return classtime;
    }

    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
}
