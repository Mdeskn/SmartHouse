package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import smarthouse.concurrency.ResourceLock;

class ResourceLockTest {
    @Test
    void testLockAndUnlock() {
        ResourceLock lock = new ResourceLock();

        lock.lock();
        assertTrue(lock.isLocked(), "Lock should be locked");

        lock.unlock();
        assertFalse(lock.isLocked(), "Lock should be unlocked");
    }
}
