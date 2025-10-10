package umbcs681.hw2.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.*;

import umbcs681.hw2.fs.Directory;
import umbcs681.hw2.fs.File;
import umbcs681.hw2.fs.FileSystem;
import umbcs681.hw2.fs.TestFixtureInitializer;
import umbcs681.hw2.util.CountingVisitor;
import umbcs681.hw2.util.FileCrawlingVisitor;
import umbcs681.hw2.util.FileSearchVisitor;

import static java.util.stream.Collectors.groupingBy;

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
	
	
	@Test
    public void verifyFileSizeStatisticsByExtension() {
		FileCrawlingVisitor visitor = new FileCrawlingVisitor(); 
		Directory root = fs.getRootDirs().get(0);
        root.accept(visitor);


        
        Map<String, List<File>> FilesGroupedByExtensions = visitor.files().collect(groupingBy((File file)->
        {
        	String fileName = file.getName();
        	int DIndex = fileName.lastIndexOf('.');
        	return (DIndex == -1) ? "NoExt" : fileName.substring(DIndex + 1);
        	}));
        		
        IntSummaryStatistics javaStats = FilesGroupedByExtensions.get("java").stream()
        	    .mapToInt(File::getSize).summaryStatistics();
        assertEquals(2, javaStats.getCount());
        assertEquals(4, javaStats.getSum());
        assertEquals(1, javaStats.getMin());
        assertEquals(3, javaStats.getMax());
        assertEquals(2.0, javaStats.getAverage());

        IntSummaryStatistics xmlStats = FilesGroupedByExtensions.get("xml").stream()
        	    .mapToInt(File::getSize).summaryStatistics();
        assertEquals(1, xmlStats.getCount());
        assertEquals(4, xmlStats.getSum());
        assertEquals(4, xmlStats.getMin());
        assertEquals(4, xmlStats.getMax());
        assertEquals(4.0, xmlStats.getAverage());


        IntSummaryStatistics mdStats = FilesGroupedByExtensions.get("md").stream()
        	    .mapToInt(File::getSize).summaryStatistics();
        assertEquals(1, mdStats.getCount());
        assertEquals(2, mdStats.getSum());
        assertEquals(2, mdStats.getMin());
        assertEquals(2, mdStats.getMax());
        assertEquals(2.0, mdStats.getAverage());
    }
	

}
