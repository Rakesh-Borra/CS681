package umbcs681.hw1.fs;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import umbcs681.hw1.fs.Directory;
import umbcs681.hw1.fs.FileSystem;

class FileSystemTest {
	private static FileSystem fs;

    @BeforeAll
    public static void setUpFS() {
    	FileSystem.resetFileSystem();
        fs = TestFixtureInitializer.createFS();
    
    }

	@Test
    public void verifySingletonInstance() {
        FileSystem fs1 = FileSystem.getFileSystem();
        FileSystem fs2 = FileSystem.getFileSystem();
        assertSame(fs1, fs2);

    }


    @Test
    public void verifyAppendAndGetRootDirs() {
        
        Directory root = new Directory(null, "umbcs680", 0, LocalDateTime.of(2024, 1, 1, 0, 0));
        fs.appendRootDir(root);
        assertEquals(2, fs.getRootDirs().size());

    }


}
