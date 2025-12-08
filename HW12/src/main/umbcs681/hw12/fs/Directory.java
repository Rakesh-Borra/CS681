package umbcs681.hw12.fs;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import umbcs681.hw12.util.FSVisitor;

public class Directory extends FSElement{
	
	private LinkedList<FSElement> children;
	private Directory parent;
	private static Comparator<FSElement> comparator;


	public Directory(Directory parent,String name, int size, LocalDateTime creationTime) {
		super(parent,name, size, creationTime);
		children=new LinkedList<FSElement>();
		comparator=(FSElement o1, FSElement o2)->o1.getName().compareTo(o2.getName());
	}

	@Override
	public boolean isDirectory() {
		return true;
	}
	
	@Override
	public boolean isFile() {
		return false;
	}

	@Override
	public boolean isLink() {
		return false;
	}
	
	public LinkedList<FSElement> getChildren(Comparator<FSElement> comparator){
		rwLock.readLock().lock();
		try {
			return children.stream().sorted(comparator).collect(Collectors.toCollection(LinkedList::new));
		} finally {
			rwLock.readLock().unlock();
		}
			
	}
	
	
	public LinkedList<FSElement> getChildren(){
		rwLock.readLock().lock();
		try {
			return getChildren(comparator);
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public void appendChild(FSElement child) {
			this.children.add(child);
			child.setParent(this);
			Collections.sort(children, comparator);

	}
	
	public int countChildren() {
		rwLock.readLock().lock();
		try {
			return children.size();
		} finally {
			rwLock.readLock().unlock();
		}
		
	}

	public LinkedList<Directory> getSubDirectories(Comparator<FSElement> comparator){
		rwLock.readLock().lock();
		try {
			return children.stream().filter(element->element.isDirectory()).map(element->(Directory)element)
					.sorted(comparator).collect(Collectors.toCollection(LinkedList::new));
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public LinkedList<Directory> getSubDirectories() {
		rwLock.readLock().lock();
		try {
			return getSubDirectories(comparator);
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public LinkedList<File> getFiles(Comparator<FSElement> comparator){
		rwLock.readLock().lock();
		try {
			return children.stream().filter(element->element.isFile()).map(element->(File)element)
					.sorted(comparator).collect(Collectors.toCollection(LinkedList::new));
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public LinkedList<File> getFiles(){
		rwLock.readLock().lock();
		try {
			return getFiles(comparator);
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public LinkedList<Link> getLinks(Comparator<FSElement> comparator){
		rwLock.readLock().lock();
		try {
			return children.stream().filter(element->element.isLink()).map(element->(Link)element)
					.sorted(comparator).collect(Collectors.toCollection(LinkedList::new));
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public LinkedList<Link> getLinks(){
		rwLock.readLock().lock();
		try {
			return getLinks(comparator); 
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public int getTotalSize(){
		rwLock.readLock().lock();
		try {
			return this.getSize()+children.stream()
			.mapToInt(element->element.isDirectory()?((Directory) element).getTotalSize():element.getSize()).sum();
		} finally {
			rwLock.readLock().unlock();
		}
		
	}

	@Override
	public void accept(FSVisitor v) {
		rwLock.writeLock().lock();
		try {
			v.visit(this);
		    children.stream().forEach(element -> element.accept(v));
		} finally {
			rwLock.writeLock().unlock();
		}

		
		
	}

	

	
}