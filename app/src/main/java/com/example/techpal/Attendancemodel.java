package com.example.techpal;
public class Attendancemodel {
   String Name;
   String absent;
   String classname;
   String date;
   String delete;
   String docId;
   String holiday;
   String month;
   int number;
   String persent;
   String rollnumber;
   String time;

    public Attendancemodel() {
    }

    public Attendancemodel(String name, String absent, String classname, String date, String delete, String docId, String holiday, String month, int number, String persent, String rollnumber, String time) {
        Name = name;
        this.absent = absent;
        this.classname = classname;
        this.date = date;
        this.delete = delete;
        this.docId = docId;
        this.holiday = holiday;
        this.month = month;
        this.number = number;
        this.persent = persent;
        this.rollnumber = rollnumber;
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
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

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPersent() {
        return persent;
    }

    public void setPersent(String persent) {
        this.persent = persent;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
