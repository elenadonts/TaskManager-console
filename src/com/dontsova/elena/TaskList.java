package com.dontsova.elena;

import java.util.Iterator;

public abstract class TaskList  {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    public TaskList incoming(int from, int to){
        TaskList incomingTasks;
        if (this instanceof ArrayTaskList){
            incomingTasks = new ArrayTaskList();
        }
        else {
            incomingTasks = new LinkedTaskList();
        }

        for(int i = 0; i < this.size(); i++){
            if(getTask(i).nextTimeAfter(from) != -1 && getTask(i).nextTimeAfter(from) <= to){
                incomingTasks.add(getTask(i));
            }
        }
        return incomingTasks;
    }

}
