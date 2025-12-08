package umbcs681.hw12.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import umbcs681.hw12.util.FSVisitor;

public abstract class FSElement {
	
	private String name;
	private int size;
	private LocalDateTime creationTime;
	private Directory parent;
	protected ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public FSElement(Directory parent,String name, int size, LocalDateTime creationTime) {
		super();
		this.parent=parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
		if(parent!=null) {
			parent.appendChild(this);
		}
	}
	
	public String getName() {
		rwLock.readLock().lock();
		try {
			return name;
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public void setName(String name) {
		rwLock.writeLock().lock();
		try {
			this.name = name;
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public int getSize() {
		rwLock.readLock().lock();
		try {
			return size;
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	public void setSize(int size) {
		rwLock.writeLock().lock();
		try {
			this.size = size;
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public LocalDateTime getCreationTime() {
		rwLock.readLock().lock();
		try {
			return creationTime;
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public void setCreationTime(LocalDateTime creationTime) {
		rwLock.writeLock().lock();
		try {
			this.creationTime = creationTime;
		} finally {
			rwLock.writeLock().unlock();
		}
		
	}
	
	public Directory getParent() {
		rwLock.readLock().lock();
		try {
			return parent;
		} finally {
			rwLock.readLock().unlock();
		}
		
	}

	public void setParent(Directory parent) {
		rwLock.writeLock().lock();
		try {
			this.parent = parent;
		} finally {
			rwLock.writeLock().unlock();
		}
		
	}

	public abstract boolean isDirectory();
	
	public abstract boolean isFile();
	
	public abstract boolean isLink();
	
	public abstract void accept(FSVisitor v);

	
}
