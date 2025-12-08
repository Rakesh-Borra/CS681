package umbcs681.hw12.util;

import umbcs681.hw12.fs.Directory;
import umbcs681.hw12.fs.File;
import umbcs681.hw12.fs.Link;

public class CountingVisitor implements FSVisitor{
	private int links_counter;
	private int files_counter;
	private int directories_counter;
	
	CountingVisitor(){
		this.directories_counter=0;
		this.files_counter=0;
		this.links_counter=0;
	}
	
	
	public int getLinks_counter() {
		return links_counter;
	}


	public int getFiles_counter() {
		return files_counter;
	}



	public int getDirectories_counter() {
		return directories_counter;
	}


	@Override
	public void visit(Link link) {
		this.links_counter+=1;
	}
	

	@Override
	public void visit(Directory dir) {
			this.directories_counter+=1;
	}

	@Override
	public void visit(File file) {
		this.files_counter+=1;
	}



}
