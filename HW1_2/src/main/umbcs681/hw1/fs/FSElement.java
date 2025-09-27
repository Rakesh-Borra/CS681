package umbcs681.hw1.fs;

import java.time.LocalDateTime;

import umbcs681.hw1.util.FSVisitor;

public abstract class FSElement {
	
	private String name;
	private int size;
	private LocalDateTime creationTime;
	private Directory parent;
	
	public FSElement(Directory parent,String name, int size, LocalDateTime creationTime) {
		super();
		this.parent=parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;

	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	
	public Directory getParent() {
		return parent;
	}

	public void setParent(Directory parent) {
		this.parent = parent;
	}

	public abstract boolean isDirectory();
	
	public abstract boolean isFile();
	
	public abstract boolean isLink();
	
	public abstract void accept(FSVisitor v);

	
}
