package umbcs681.hw13.fs;

import java.util.concurrent.atomic.AtomicBoolean;

public class FileRunnable implements Runnable {

    private Directory directory;
    private AtomicBoolean terminateFlag;

    public FileRunnable(Directory directory, AtomicBoolean terminateFlag) {
        this.directory = directory;
        this.terminateFlag = terminateFlag;
    }

    @Override
    public void run() {
        while (!terminateFlag.get()) {
            directory.getFiles().forEach(file -> System.out.println("File in directory: "+file.getName()));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("File task completed.");
    }



}

