package com.geermank.todoapp.Models;

public class Task {

    private long id;
    private String title;
    private String priority;
    private long expirationDate;
    private boolean finished;
    private boolean expired;

    public Task(long id, String title, String priority, long expirationDate, boolean finished, boolean expired) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.expirationDate = expirationDate;
        this.finished = finished;
        this.expired = expired;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
