package umbcs681.hw14.util;

import java.util.LinkedList;
import java.util.stream.Stream;

import umbcs681.hw14.fs.Directory;
import umbcs681.hw14.fs.File;
import umbcs681.hw14.fs.Link;

public class FileCrawlingVisitor implements FSVisitor{
	private LinkedList<File> files;
	
	public FileCrawlingVisitor(){
		files=new LinkedList<File>();
	}
	

	public LinkedList<File> getFiles() {
		return files;
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
		files.add(file);
	}
	
	public Stream<File> files(){
		return files.stream();
	}




}
