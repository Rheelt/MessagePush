package push.remoting;

import push.remoting.exception.LifeCycleException;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractLifeCycle implements LifeCycle {

    private final AtomicBoolean isStarted = new AtomicBoolean(false);

    @Override
    public void startup() throws LifeCycleException {
        if (isStarted.compareAndSet(false, true)) {
            return;
        }
        throw new LifeCycleException("component has been started.");
    }

    @Override
    public void shutdown() throws LifeCycleException {
        if (isStarted.compareAndSet(true, false)) {
            return;
        }
        throw new LifeCycleException("component has been shutdown.");
    }

    @Override
    public boolean isStarted() {
        return isStarted.get();
    }
}
