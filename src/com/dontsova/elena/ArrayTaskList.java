package com.dontsova.elena;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTaskList extends TaskList {


    private Task[] tasks;
    private int numberOfTasks;
    private int currentCapacity;

    public ArrayTaskList(){
        currentCapacity = 10;
        this.tasks = new Task[currentCapacity];
    }
    @Override
    public void add(Task task){
        if (task.equals(null)) throw new NullPointerException("Task shouldn't be null");
        if (numberOfTasks == currentCapacity-1){
            currentCapacity = currentCapacity * 2;
            Task[] withAddedTask = new Task[currentCapacity];
            System.arraycopy(tasks,0,withAddedTask,0,tasks.length);
            this.tasks = withAddedTask;
        }
        this.tasks[numberOfTasks] = task;
        this.numberOfTasks++;
    }
    @Override
    public boolean remove(Task task){
        int indexOfTaskToDelete = -1;
        for(int i = 0; i < tasks.length; i++){
            if (task.equals(tasks[i])){
                indexOfTaskToDelete = i;
                break;
            }
        }
        if (indexOfTaskToDelete >= 0){
            this.numberOfTasks--;
            System.arraycopy(tasks, indexOfTaskToDelete+1,tasks,indexOfTaskToDelete,
                    numberOfTasks-indexOfTaskToDelete+1);
            return true;
        }
        return false;
    }
    @Override
    public int size(){
        return this.numberOfTasks;
    }
    @Override
    public Task getTask(int index){
        if (index < 0 || index > size()-1) throw new IndexOutOfBoundsException("Index not found");
        return tasks[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayTaskList that = (ArrayTaskList) o;

        if (numberOfTasks != that.numberOfTasks) return false;
        if (currentCapacity != that.currentCapacity) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(tasks);
        result = 31 * result + numberOfTasks;
        result = 31 * result + currentCapacity;
        return result;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasks=" + Arrays.toString(tasks) +
                ", numberOfTasks=" + numberOfTasks +
                ", currentCapacity=" + currentCapacity +
                '}';
    }
}