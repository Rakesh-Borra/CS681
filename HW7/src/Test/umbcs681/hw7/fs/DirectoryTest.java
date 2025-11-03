package umbcs681.hw7.fs;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import umbcs681.hw7.fs.Directory;
import umbcs681.hw7.fs.FSElement;
import umbcs681.hw7.fs.File;
import umbcs681.hw7.fs.FileSystem;

public class DirectoryTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUpFS() {

    	FileSystem.resetFileSystem();
        fs = TestFixtureInitializer.createFS();
    }

    private String[] dirToStringArray(Directory d){

        return new String[] {
            d.getName(),
            String.valueOf(d.getSize()),
            String.valueOf(d.getCreationTime())
        };
    }
    
    @Test
    public void verifyIsDirectory() {
        Directory umbcs680 = fs.getRootDirs().get(0);

        assertTrue(umbcs680.isDirectory());

    }

    @Test
    public void verifyGetChildren() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        LinkedList<FSElement> children = umbcs680.getChildren();

        assertEquals(2, children.size());

    }

    @Test
    public void verifyAppendChild() {
        Directory newDir = new Directory(null, "test", 0, LocalDateTime.now());
        File testFile = new File(newDir, "file.txt", 5, LocalDateTime.now());
        newDir.appendChild(testFile);

        assertEquals(1, newDir.countChildren());
        assertSame(newDir, testFile.getParent());

    }

    @Test
    public void verifyCountChildren() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        Directory hw01 = umbcs680.getSubDirectories().get(0);
        Directory src = hw01.getSubDirectories().get(0);

        assertEquals(2, umbcs680.countChildren());
        assertEquals(3, hw01.countChildren());
        assertEquals(2, src.countChildren());

    }

    @Test
    public void verifyGetSubDirectories() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        LinkedList<Directory> subDirs = umbcs680.getSubDirectories();

        assertEquals(1, subDirs.size());
        assertEquals("hw01", subDirs.get(0).getName());

    }


    @Test
    public void verifyGetFiles() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        LinkedList<File> files = umbcs680.getFiles();

        assertEquals(1, files.size()); 
        assertEquals("readme.md", files.get(0).getName());

    }

    @Test
    public void verifyGetTotalSize() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        Directory hw01 = umbcs680.getSubDirectories().get(0);
        Directory src = hw01.getSubDirectories().get(0);

        assertEquals(10, umbcs680.getTotalSize()); 
        assertEquals(8, hw01.getTotalSize());     
        assertEquals(4, src.getTotalSize());

    }


    @Test
    public void verifyDirectoryEqualityRoot() {
        String[] expected = {"umbcs680", "0", LocalDateTime.of(2025, 12, 12, 0, 1).toString()};
        Directory actual = fs.getRootDirs().get(0);

        assertArrayEquals(expected, dirToStringArray(actual));
    }

    @Test
    public void verifyDirectoryEqualityHw01() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        Directory hw01 = umbcs680.getSubDirectories().get(0);
        String[] expected = {"hw01", "0", LocalDateTime.of(2025, 12, 12, 0, 2).toString()};
        String[] actual = dirToStringArray(hw01);

        assertArrayEquals(expected, actual);

    }

    @Test
    public void verifyDirectoryEqualitySrc() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        Directory hw01 = umbcs680.getSubDirectories().get(0);
        Directory src = hw01.getSubDirectories().get(0);
        String[] expected = {"src", "0", LocalDateTime.of(2025, 12, 12, 0, 3).toString()};

        assertArrayEquals(expected, dirToStringArray(src));

    }



}
