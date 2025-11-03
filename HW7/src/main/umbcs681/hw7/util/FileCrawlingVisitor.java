package umbcs681.hw7.util;

import java.util.LinkedList;
import java.util.stream.Stream;

import umbcs681.hw7.fs.Directory;
import umbcs681.hw7.fs.File;
import umbcs681.hw7.fs.Link;

public class FileCrawlingVisitor implements FSVisitor{
	private LinkedList<File> files;
	
	FileCrawlingVisitor(){
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

