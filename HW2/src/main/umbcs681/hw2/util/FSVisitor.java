package umbcs681.hw2.util;

import umbcs681.hw2.fs.Directory;
import umbcs681.hw2.fs.File;
import umbcs681.hw2.fs.Link;

public interface FSVisitor {
	
	public void visit(Link link);  
	public void visit(Directory dir); 
	public void visit(File file);

}
