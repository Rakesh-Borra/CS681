package umbcs681.hw13.fs;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

import umbcs681.hw13.util.FileCrawlingVisitor;


public class MainThread {

    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean terminateFlag = new AtomicBoolean(false);
        LinkedList<File> sharedFiles = new LinkedList<>();
        LocalDateTime currentTime = LocalDateTime.of(2023, 11, 2, 14, 30, 0);
        Directory root1 = new Directory(null, "root1", 0, LocalDateTime.now());
        for (int i = 1; i <= 10; i++) {
            String fileName = "root1-File-" + i + ".java";
            File file = new File(root1, fileName, 1, currentTime);  
            root1.appendChild(file);  
        }
        Directory root2 = new Directory(null, "root2", 0, LocalDateTime.now());
        for (int i = 1; i <= 10; i++) {
            String fileName = "root2-File-" + i + ".java";
            File file = new File(root2, fileName, 1, currentTime);  
            root2.appendChild(file);  
        }
        Directory root3 = new Directory(null, "root3", 0, LocalDateTime.now());
        for (int i = 1; i <= 10; i++) {
            String fileName = "root3-File-" + i + ".java";
            File file = new File(root3, fileName, 1, currentTime);  
            root3.appendChild(file);  
        }

        FileCrawlingVisitor visitor1 = new FileCrawlingVisitor();
        FileCrawlingVisitor visitor2 = new FileCrawlingVisitor();
        FileCrawlingVisitor visitor3 = new FileCrawlingVisitor();


        Thread thread1 = new Thread(new FileCrawlerRunnable(root1, terminateFlag, visitor1, sharedFiles));
        Thread thread2 = new Thread(new FileCrawlerRunnable(root2, terminateFlag, visitor2, sharedFiles));
        Thread thread3 = new Thread(new FileCrawlerRunnable(root3, terminateFlag, visitor3, sharedFiles));

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(5); 

        terminateFlag.set(true);
        System.out.println("Termination flag set to true.");

        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
        thread1.join();
        thread2.join();
        thread3.join();

//        synchronized (sharedFiles) {
//            sharedFiles.addAll(visitor1.getFiles());
//            sharedFiles.addAll(visitor2.getFiles());
//            sharedFiles.addAll(visitor3.getFiles());
//        }

        System.out.println("Identified files:");
        sharedFiles.forEach(file -> System.out.println(file.getName()));
    }



}
