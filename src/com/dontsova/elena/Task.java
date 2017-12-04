package com.dontsova.elena;

import java.io.Serializable;
import java.util.Date;


public class Task implements Serializable, Cloneable {
    private String title;
    private Date time;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;

    public Task(String title, Date time){
        if (time.getTime() < 0) throw new IllegalArgumentException("Time cannot be negative");
        this.title = title;
        this.time = time;
        this.start = time;
        this.end = time;
    }
    public Task(String title, Date start, Date end, int interval){
        if (start.getTime() < 0 || end.getTime() < 0) throw new IllegalArgumentException("Time cannot be negative");
        if (interval < 1) throw new IllegalArgumentException("interval should me > 0");
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.time = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isActive(){
        if(this.active){
            return true;
        }
        return false;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
        this.start = time;
        this.end = time;
        this.interval = 0;
    }

    public Date getStartTime() {
        return start;
    }

    public Date getEndTime() {
        return end;
    }
    public int getRepeatInterval(){
        if(interval > 0){
            return interval;
        }
        return 0;
    }

    public void setTime(Date start, Date end, int interval){
        this.time = start;
        this.start = start;
        this.end = end;
        this.interval = interval;

    }
    public boolean isRepeated(){
        if(this.interval == 0){
            return false;
        }
        return true;
    }
    public Date nextTimeAfter(Date current){
        if (current.after(end) || current.equals(end))return null;
        if (isRepeated() && isActive()){
            Date timeBefore  = start;
            Date timeAfter = start;
            if (current.before(start)){
                return start;
            }
            if ((current.after(start) || current.equals(start)) && (current.before(end) || current.equals(end))){
                for (long i = start.getTime(); i <= end.getTime(); i += interval*1000){
                    if (current.equals(timeAfter)) return new Date(timeAfter.getTime()+interval*1000);
                    if (current.after(timeBefore) && current.before(timeAfter)) return timeAfter;
                    timeBefore = timeAfter;
                    timeAfter = new Date(timeAfter.getTime()+ interval*1000);
                }
            }
        }
        if (!isRepeated() && current.before(time) && isActive()){
            return time;
        }
        return null;
    }

    public static void main(String[] args) {
//        Date date = new Date();
//        Task a1 = new Task("A", new Date(10));
//        Task b1 = new Task("B", date);
        Task a = new Task("s", new Date(0), new Date(1000*3600*24*7), 3600);
        a.setActive(true);
        System.out.println(a.getStartTime());
        System.out.println(a.getEndTime());
        System.out.println(a.nextTimeAfter(new Date(0)));



    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!time.equals(task.time)) return false;
        if (!start.equals(task.start)) return false;
        if (!end.equals(task.end)) return false;
        if (interval != task.interval) return false;
        if (active != task.active) return false;
        return title.equals(task.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + interval;
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                '}';
    }
    @Override
    protected Task clone() throws CloneNotSupportedException {
        Task task  = (Task)super.clone();
        task.time = (Date)this.time.clone();
        task.start = (Date)this.start.clone();
        task.end = (Date)this.end.clone();
        return task;
    }
}


