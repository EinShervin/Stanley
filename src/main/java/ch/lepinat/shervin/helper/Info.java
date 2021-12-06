package ch.lepinat.shervin.helper;

public class Info {
    private int taskId;
    private long timer;

    public Info(int taskId, long timer) {
        this.taskId = taskId;
        this.timer = timer;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }
}
