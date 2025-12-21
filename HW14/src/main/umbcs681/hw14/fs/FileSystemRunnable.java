package umbcs681.hw14.fs;

import java.util.concurrent.atomic.AtomicBoolean;

public class FileSystemRunnable implements Runnable {

    private FileSystem fileSystem;
    private AtomicBoolean terminateFlag;

    public FileSystemRunnable(FileSystem fileSystem, AtomicBoolean terminateFlag) {
        this.fileSystem = fileSystem;
        this.terminateFlag = terminateFlag;
    }

    @Override
    public void run() {
        while (!terminateFlag.get()) {
            System.out.println("Current root directories: "+fileSystem.getRootDirs().size());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("FileSystem task completed.");
    }



}
