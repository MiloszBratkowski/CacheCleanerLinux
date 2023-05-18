package pl.techbrat.linux;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class MainListener implements Runnable{
    private boolean stop = false;

    public void stop() {
        stop = true;
    }

    public boolean isStop() {
        return stop;
    }

    @Override
    public void run() {
        while(!stop) {
            try {
                CacheManager.getInstance().checkCache();
                CacheManager.getInstance().clearCache();
                sleep(300000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
