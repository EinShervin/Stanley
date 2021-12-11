package ch.lepinat.shervin.helper;

public class Info {
    private int taskId;
    private long time;

    public Info(int taskId, long time) {
        this.taskId = taskId;
        this.time = time;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
