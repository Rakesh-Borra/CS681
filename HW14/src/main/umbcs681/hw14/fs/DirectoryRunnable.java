package umbcs681.hw14.fs;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class DirectoryRunnable implements Runnable {

    private Directory directory;
    private AtomicBoolean terminateFlag;

    public DirectoryRunnable(Directory directory, AtomicBoolean terminateFlag) {
        this.directory = directory;
        this.terminateFlag = terminateFlag;
    }

    @Override
    public void run() {
        while (!terminateFlag.get()) {
            File file = new File(directory,"File"+System.currentTimeMillis(),100,LocalDateTime.now());
            directory.appendChild(file);
            System.out.println("Added file to directory:"+directory.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Directory task completed.");
    }



}
