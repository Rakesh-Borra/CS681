package umbcs681.hw1.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import umbcs681.hw1.fs.Directory;
import umbcs681.hw1.fs.File;
import umbcs681.hw1.fs.FileSystem;
import umbcs681.hw1.fs.TestFixtureInitializer;
import umbcs681.hw1.util.CountingVisitor;
import umbcs681.hw1.util.FileCrawlingVisitor;
import umbcs681.hw1.util.FileSearchVisitor;

class FSVisitorTest {

	private static FileSystem fs;

	@BeforeAll
    public static void setUpFS() {
		FileSystem.resetFileSystem();
        fs = TestFixtureInitializer.createFS();
    }
	
	@Test
	void FileSearchVisitorTest() {
		FileSearchVisitor visitor = new FileSearchVisitor("A.java"); 
		Directory root = fs.getRootDirs().get(0);
		root.accept( visitor );

		 assertEquals(1, visitor.getFoundFiles().size());

	}
	
	@Test
	void CountingVisitorTest() {
		CountingVisitor visitor = new CountingVisitor(); 
		Directory root = fs.getRootDirs().get(0);
		root.accept( visitor );

		assertEquals(3, visitor.getDirectories_counter() );
		assertEquals(4, visitor.getFiles_counter() );
		assertEquals(1, visitor.getLinks_counter() );
	}
	
	@Test
	void FileCrawlingVisitorTest() {

		FileCrawlingVisitor visitor = new FileCrawlingVisitor(); 
		Directory root = fs.getRootDirs().get(0);
		root.accept( visitor );
		LinkedList<File> collectedFiles = visitor.getFiles();
		String[] actualFileNames = new String[collectedFiles.size()];
	    for (int i = 0; i < collectedFiles.size(); i++) {
	        actualFileNames[i] = collectedFiles.get(i).getName();
	    }
	    String[] expectedFileNames = {"build.xml", "A.java", "B.java", "readme.md"};

	    assertEquals(4, collectedFiles.size());
	    assertArrayEquals(expectedFileNames, actualFileNames);
	    
	    LocalDateTime cutoff = LocalDateTime.of(2025, 12, 12, 0, 4);
	    Long after_cutoff=visitor.files().filter(f->f.getCreationTime().isAfter(cutoff)).count();
	    assertEquals(3, after_cutoff);
	    Long ends_with_java=visitor.files().filter(f->f.getName().endsWith(".java")).count();
	    assertEquals(2, ends_with_java);
	    
	}
	
	

}
