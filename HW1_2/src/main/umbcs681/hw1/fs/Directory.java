package umbcs681.hw1.fs;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import umbcs681.hw1.util.FSVisitor;

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
		return children.stream().sorted(comparator).collect(Collectors.toCollection(LinkedList::new));	
	}

	public LinkedList<FSElement> getChildren(){
		return getChildren(comparator);
	}
	
	public void appendChild(FSElement child) {
		this.children.add(child);
		child.setParent(this);
		Collections.sort(children, comparator);

	}


	public int countChildren() {
		return children.size();
	}

	public LinkedList<Directory> getSubDirectories(Comparator<FSElement> comparator){
		return children.stream().filter(element->element.isDirectory()).map(element->(Directory)element)
				.sorted(comparator).collect(Collectors.toCollection(LinkedList::new));
	}
	
	public LinkedList<Directory> getSubDirectories() {
		return getSubDirectories(comparator);
	}
	
	public LinkedList<File> getFiles(Comparator<FSElement> comparator){
		return children.stream().filter(element->element.isFile()).map(element->(File)element)
				.sorted(comparator).collect(Collectors.toCollection(LinkedList::new));
	}


	public LinkedList<File> getFiles(){
		return getFiles(comparator);
	}
	
	public LinkedList<Link> getLinks(Comparator<FSElement> comparator){
		return children.stream().filter(element->element.isLink()).map(element->(Link)element)
				.sorted(comparator).collect(Collectors.toCollection(LinkedList::new));
	}
	
	public LinkedList<Link> getLinks(){
		return getLinks(comparator); 
	}
	
	public int getTotalSize(){	
		return this.getSize()+children.stream()
		.mapToInt(element->element.isDirectory()?((Directory) element).getTotalSize():element.getSize()).sum();
	}


	@Override
	public void accept(FSVisitor v) {
		v.visit(this);
        children.stream().forEach(element -> element.accept(v));
	}


}
