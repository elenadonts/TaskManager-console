package com.dontsova.elena;


import java.util.ArrayList;
import java.util.LinkedList;

public class Task  {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    public Task(String title, int time){
        if (time < 0) throw new IllegalArgumentException("Time cannot be negative");
        this.title = title;
        this.time = time;
        this.start = time;
        this.end = time;
    }
    public Task(String title, int start, int end, int interval){
        if (start < 0 || end < 0) throw new IllegalArgumentException("Time cannot be negative");
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        this.start = time;
        this.end = time;
        this.interval = 0;
    }

    public int getStartTime() {
        return start;
    }

    public int getEndTime() {
        return end;
    }
    public int getRepeatInterval(){
        if(interval > 0){
            return interval;
        }
        return 0;
    }

    public void setTime(int start, int end, int interval){
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
    public  int nextTimeAfter(int current){
        if (isRepeated() && isActive()){
            int timeBefore  = start;
            int timeAfter = start;
            if (current < start){
                return start;
            }
            if (current >= start && current <= end){
                for(int i = start; i <= end; i += interval){
                    if (current == timeAfter) return timeAfter+interval;
                    if (current > timeBefore && current < timeAfter) return timeAfter;
                    timeBefore = timeAfter;
                    timeAfter += interval;
                }
            }
        }
        if (!isRepeated() && current < time && isActive()){
            return time;
        }
        return -1;
    }

    public static void main(String[] args) {

        Task a1 = new Task("A", 10);
        Task b1 = new Task("B", 20);
//
//        LinkedTaskList a = new LinkedTaskList();
//        LinkedTaskList b = new LinkedTaskList();
//        System.out.println(a.equals(b));
//        a1.setActive(true);
//        b1.setActive(true);
//
//        for (int i = 0; i < 10; i++){
//            if (i < 5){
//                a.add(a1);
//                b.add(b1);
//            }
//
//        }
//
//        Task a2 = new Task("A", 10);
//        Task b2 = new Task("A", 10);
//        System.out.println(a.size());
//        System.out.println(b.size());
//        while (a.size() > 0){
//            a.remove(a.getTask(a.size() - 1));
//        }
//        while (b.size() > 0) {
//            b.remove(b.getTask(b.size() - 1));
//        }
//
//        for (int i = 0; i < 10; i++){
//            a.add(a2);
//            b.add(b2);
//        }

        ArrayTaskList a = new ArrayTaskList();
        a.add(a1);
        a.add(b1);
        a.add(a1);

        a.remove(b1);
        a.remove(a1);
        a.remove(a1);
  
        System.out.println(a.size());


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (time != task.time) return false;
        if (start != task.start) return false;
        if (end != task.end) return false;
        if (interval != task.interval) return false;
        if (active != task.active) return false;
        return title.equals(task.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + time;
        result = 31 * result + start;
        result = 31 * result + end;
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
}


