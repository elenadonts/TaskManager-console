package com.dontsova.elena;

public class ArrayTaskList {
    public Task[] tasks;
    public int numberOfTasks;
    private int currentCapacity;

    public ArrayTaskList(){
        currentCapacity = 10;
        this.tasks = new Task[currentCapacity];
    }
    public void add(Task task){
        if (numberOfTasks >= currentCapacity){
            currentCapacity = currentCapacity * 2;
            Task[] withAddedTask = new Task[currentCapacity];
            System.arraycopy(tasks,0,withAddedTask,0,tasks.length);
            this.tasks = withAddedTask;
        }
        this.tasks[numberOfTasks] = task;
        this.numberOfTasks++;
    }
    public boolean remove(Task task){
        int indexOfTaskToDelete = -1;
        for(int i = 0; i < tasks.length; i++){
            if (task.equals(tasks[i])){
                indexOfTaskToDelete = i;
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
    public int size(){
        return this.numberOfTasks;
    }
    public Task getTask(int index){
        if (index < numberOfTasks) return tasks[index];
        System.out.println("Index not found");
        return null;
    }
    public ArrayTaskList incoming(int from, int to){
        ArrayTaskList incomingTasks = new ArrayTaskList();
        for(int i = 0; i < this.size(); i++){
            if(this.tasks[i].nextTimeAfter(from) != -1 &&
                    this.tasks[i].nextTimeAfter(from) <= to){
                incomingTasks.add(tasks[i]);
            }
        }
        return incomingTasks;
    }
}