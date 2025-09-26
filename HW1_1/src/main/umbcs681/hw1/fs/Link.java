package umbcs681.hw1.fs;

import java.time.LocalDateTime;

import umbcs681.hw1.util.FSVisitor;


public class Link extends FSElement{

	private FSElement target;
	public Link(Directory parent, String name, int size, LocalDateTime creationTime,FSElement target ) {
		super(parent, name, size, creationTime);
		this.target=target;
	}

	@Override
	public boolean isDirectory() {
		return false;
	}

	@Override
	public boolean isFile() {
		return false;
	}

	public FSElement getTarget() {
		return target;
	}


	public void setTarget(FSElement target) {
		this.target = target;
	}

	@Override
	public boolean isLink() {
		return true;
	}

	@Override
	public void accept(FSVisitor v) {
		v.visit(this);
	}

	
}
