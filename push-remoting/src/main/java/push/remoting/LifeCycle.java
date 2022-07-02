package push.remoting;

import push.remoting.exception.LifeCycleException;

public interface LifeCycle {
    void startup() throws LifeCycleException;

    void shutdown() throws LifeCycleException;

    boolean isStarted();
}
