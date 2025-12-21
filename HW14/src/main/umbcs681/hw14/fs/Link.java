package umbcs681.hw14.fs;

import java.time.LocalDateTime;

import umbcs681.hw14.util.FSVisitor;


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
		rwLock.readLock().lock();
		try {
			return target;
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public void setTarget(FSElement target) {
		rwLock.writeLock().lock();
		try {
			this.target = target;
		} finally {
			rwLock.writeLock().unlock();
		}
		
	}

	@Override
	public boolean isLink() {
		return true;
	}

	@Override
	public void accept(FSVisitor v) {
		rwLock.writeLock().lock();
		try {
			v.visit(this);
		} finally {
			rwLock.writeLock().unlock();
		}
		
	}




}
