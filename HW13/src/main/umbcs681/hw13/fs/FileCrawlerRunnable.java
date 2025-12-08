package umbcs681.hw13.fs;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

import umbcs681.hw13.util.FileCrawlingVisitor;

public class FileCrawlerRunnable implements Runnable {

    private Directory rootDirectory;
    private AtomicBoolean terminateFlag;
    private FileCrawlingVisitor visitor;
    private LinkedList<File> sharedFiles;

    public FileCrawlerRunnable(Directory rootDirectory, AtomicBoolean terminateFlag, FileCrawlingVisitor visitor, LinkedList<File> sharedFiles) {
        this.rootDirectory = rootDirectory;
        this.terminateFlag = terminateFlag;
        this.visitor = visitor;
        this.sharedFiles = sharedFiles;
    }

    @Override
    public void run() {
        while (!terminateFlag.get()) {
            rootDirectory.accept(visitor);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        synchronized (sharedFiles) {
            sharedFiles.addAll(visitor.getFiles());
        }
    }



}

