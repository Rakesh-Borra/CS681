package umbcs681.hw13.util;

import umbcs681.hw13.fs.Directory;
import umbcs681.hw13.fs.File;
import umbcs681.hw13.fs.Link;

public interface FSVisitor {
	
	public void visit(Link link);  
	public void visit(Directory dir); 
	public void visit(File file);



}
