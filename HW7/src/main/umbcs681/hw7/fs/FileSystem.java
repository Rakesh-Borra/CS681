package umbcs681.hw7.fs;

import java.util.LinkedList;
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
		FileSystem.resetFileSystem();

        Runnable task = () -> {
        	FileSystem instance = FileSystem.getFileSystem();
        	System.out.println(Thread.currentThread().getName() +" got instance: " + instance.hashCode());
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
	}
	


}

