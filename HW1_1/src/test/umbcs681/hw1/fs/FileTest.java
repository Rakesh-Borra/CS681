package umbcs681.hw1.fs;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import umbcs681.hw1.fs.Directory;
import umbcs681.hw1.fs.File;
import umbcs681.hw1.fs.FileSystem;

public class FileTest {

    private static FileSystem fs;

    @BeforeAll
    public static void setUpFS() {
    	FileSystem.resetFileSystem();
        fs = TestFixtureInitializer.createFS();
    }

    private String[] fileToStringArray(File f) {

        return new String[] {
            f.getName(),
            String.valueOf(f.getSize()),
            String.valueOf(f.getCreationTime())
        };
    }


    @Test
    public void verifyFileEqualityAjava() {

        Directory umbcs680 = fs.getRootDirs().get(0);
        Directory hw01 = umbcs680.getSubDirectories().get(0);
        Directory src = hw01.getSubDirectories().get(0);
        File aJava = src.getFiles().get(0);
        String[] expected = {"A.java", "1", LocalDateTime.of(2025, 12, 12, 0, 4).toString()};
        assertArrayEquals(expected, fileToStringArray(aJava));

    }

    @Test
    public void verifyFileEqualityBjava() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        Directory hw01 = umbcs680.getSubDirectories().get(0);
        Directory src = hw01.getSubDirectories().get(0);
        File bJava = src.getFiles().get(1);
        String[] expected = {"B.java","3",LocalDateTime.of(2025, 12, 12, 0, 5).toString()};
        assertArrayEquals(expected, fileToStringArray(bJava));

    }


    @Test
    public void verifyFileEqualityBuildXml() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        Directory hw01 = umbcs680.getSubDirectories().get(0);
        File buildXml = hw01.getFiles().get(0);
        String[] expected = {"build.xml","4",LocalDateTime.of(2025, 12, 12, 0, 6).toString()};
        assertArrayEquals(expected, fileToStringArray(buildXml));

    }

    @Test
    public void verifyFileEqualityReadmeMd() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        File readmeMd = umbcs680.getFiles().get(0);
        String[] expected = {"readme.md","2",LocalDateTime.of(2025, 12, 12, 0, 7).toString()};
        assertArrayEquals(expected, fileToStringArray(readmeMd));

    }


    @Test
    public void verifyIsDirectory() {
        Directory umbcs680 = fs.getRootDirs().get(0);
        File readme = umbcs680.getFiles().get(0);
        File buildXml = umbcs680.getSubDirectories().get(0).getFiles().get(0);
        File aJava = umbcs680.getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(0);
        File bJava = umbcs680.getSubDirectories().get(0).getSubDirectories().get(0).getFiles().get(1);
        assertFalse(readme.isDirectory());
        assertFalse(buildXml.isDirectory());
        assertFalse(aJava.isDirectory());
        assertFalse(bJava.isDirectory());

    }


}
