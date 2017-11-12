package com.dontsova.elena;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    public Task(String title, int time){
        this.title = title;
        this.time = time;
        this.start = time;
        this.end = time;
    }
    public Task(String title, int start, int end, int interval){
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

    }
}