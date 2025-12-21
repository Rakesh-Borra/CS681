package umbcs681.hw14.util;

import java.util.LinkedList;

import umbcs681.hw14.fs.Directory;
import umbcs681.hw14.fs.File;
import umbcs681.hw14.fs.Link;

public class FileSearchVisitor implements FSVisitor{

	private LinkedList<File> foundFiles;
	private String fileName;
	
	FileSearchVisitor(String fileName){
		foundFiles=new LinkedList<File>();
		this.fileName=fileName;
	}
	
	@Override
	public void visit(Link link) {
		return;
	}

	@Override
	public void visit(Directory dir) {
		return;
	}

	@Override
	public void visit(File file) {
		if(file.getName().equals(fileName)){
			  foundFiles.add(file); }
	}
	
	public LinkedList<File> getFoundFiles() {
		return foundFiles;	
	}





}
