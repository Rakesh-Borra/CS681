package umbcs681.hw13.fs;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class LinkRunnable implements Runnable {

    private Directory directory;
    private AtomicBoolean terminateFlag;

    public LinkRunnable(Directory directory, AtomicBoolean terminateFlag) {
        this.directory = directory;
        this.terminateFlag = terminateFlag;
    }

    @Override
    public void run() {
        while (!terminateFlag.get()) {
            Link link = new Link(directory,"Link"+System.currentTimeMillis(),0,LocalDateTime.now(),directory);
            System.out.println("Created link in directory: "+directory.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Link task completed.");
    }



}
