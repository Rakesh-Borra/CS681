package umbcs681.hw1.fs;

import java.util.LinkedList;

public class FileSystem {

	private LinkedList<Directory> rootDirs;
	private static FileSystem instance=null;

	private FileSystem() {
		this.rootDirs = new LinkedList<Directory>();
	}
	
	public static FileSystem getFileSystem(){
	    if (instance == null) {
	         instance = new FileSystem();
	      }
	   return instance;
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


}
