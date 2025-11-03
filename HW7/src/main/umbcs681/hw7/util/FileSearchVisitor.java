package umbcs681.hw7.util;

import java.util.LinkedList;

import umbcs681.hw7.fs.Directory;
import umbcs681.hw7.fs.File;
import umbcs681.hw7.fs.Link;

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
