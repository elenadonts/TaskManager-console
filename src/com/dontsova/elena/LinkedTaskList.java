package com.dontsova.elena;


import static java.util.Objects.isNull;

public class LinkedTaskList  extends TaskList{

    private int numberOfTasks;
    private Node last;

    @Override
    public void add(Task task) {
        numberOfTasks++;
        Node lastNode = last;
        Node newNode = new Node(task, lastNode);
        if (last!= null) last.setNext(newNode);
        last = newNode;
    }
    @Override
    public boolean remove(Task task) {
        if (isNull(task)) {
            throw new NullPointerException("Task is null");
        }

        Node cursor = last;
        if (last.getTask().equals(task)) this.last = last.getLast();
        int tasksToCheck = size();
        while (tasksToCheck > 0 && !task.equals(cursor.getTask())){
            cursor = cursor.getLast();
            tasksToCheck--;
        }
        if (isNull(cursor)) return false;

        if (cursor.last!= null) cursor.getLast().setNext(cursor.getNext());
        if (cursor.next!= null) cursor.getNext().setLast(cursor.getLast());
        numberOfTasks--;
        return true;
    }

    @Override
    public int size() {
        return numberOfTasks;
    }
    @Override
    public Task getTask(int index) {
        if (index < 0 || index > size()-1) throw new IndexOutOfBoundsException("Index not found");
        int stepsBack = size()-index-1;
        Node current = last;
        while (stepsBack > 0){
            current = current.getLast();
            stepsBack--;
        }
        return current.getTask();
    }

    private static class Node {
        private Task task;
        private Node last;
        private Node next;

        private Node getNext() {
            return next;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Node(Task task, Node last) {
            this.task = task;
            this.last = last;
        }

        private Task getTask() {
            return task;
        }

        private Node getLast() {
            return last;
        }

        private void setTask(Task task) {
            this.task = task;
        }

        private void setLast(Node last) {
            this.last = last;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedTaskList that = (LinkedTaskList) o;

        if (numberOfTasks != that.numberOfTasks) return false;
        return last.equals(that.last);
    }

    @Override
    public int hashCode() {
        int result = numberOfTasks;
        result = 31 * result + last.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "numberOfTasks=" + numberOfTasks +
                ", last=" + last +
                '}';
    }
}
