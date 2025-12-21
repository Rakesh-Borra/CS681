package umbcs681.hw14.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {

	private LinkedList<Directory> rootDirs;
	private static FileSystem instance=null;
	private static final ReentrantLock lock = new ReentrantLock();
	
	private FileSystem() {
		this.rootDirs = new LinkedList<Directory>();
	}
	
	public static FileSystem getFileSystem(){
		lock.lock();
		try {
		    if (instance == null) {
		         instance = new FileSystem();
		      }
	   return instance;
		} finally {
            lock.unlock();
        }
	}

	public LinkedList<Directory> getRootDirs() {
		return rootDirs;
	}
	
	public void appendRootDir( Directory root) {
		rootDirs.add(root);
	}
	
	public static void resetFileSystem() {
	    instance = null;
	}
	
	public static void main(String[] args) throws InterruptedException {
        FileSystem fs = FileSystem.getFileSystem();
        Directory root1 = new Directory(null,"root1",0,LocalDateTime.now());
        Directory root2 = new Directory(null,"root2",0,LocalDateTime.now());
        fs.appendRootDir(root1);
        fs.appendRootDir(root2);
        AtomicBoolean terminateFlag = new AtomicBoolean(false);
        Thread[] threads = new Thread[40];
        for (int i = 0; i < 10; i++) {
            threads[i]=new Thread(new DirectoryRunnable(root1,terminateFlag));
            threads[i].start();
            threads[i+10]=new Thread(new FileRunnable(root1,terminateFlag));
            threads[i+10].start();
            threads[i+20]=new Thread(new LinkRunnable(root1,terminateFlag));
            threads[i+20].start();
            threads[i+30]=new Thread(new FileSystemRunnable(fs,terminateFlag));
            threads[i+30].start();
        }
        Thread.sleep(500);
        terminateFlag.set(true);
        System.out.println("Termination flag set to true.");
        for (Thread t:threads) {
            t.interrupt();
        }
        for (Thread t:threads) {
            t.join();
        }
        System.out.println("All threads have completed.");
    }

	


}
