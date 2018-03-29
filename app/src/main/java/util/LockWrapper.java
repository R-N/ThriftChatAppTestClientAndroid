package util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class LockWrapper implements AutoCloseable
{
    private final Lock _lock;
    public LockWrapper(Lock l) {
       this._lock = l;
    }

    public LockWrapper lock(){
        this._lock.lock();
        return this;
    }

    public LockWrapper lock(int timeout) throws InterruptedException {
        if(_lock.tryLock(timeout, TimeUnit.SECONDS)){
            return this;
        }else{
            throw new InterruptedException();
        }
    }

    public void close() {
        this._lock.unlock();
    }
}