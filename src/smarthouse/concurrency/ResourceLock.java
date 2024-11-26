package smarthouse.concurrency;

public class ResourceLock {
    private boolean locked;

    public boolean isLocked() {
        return locked;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }
}

