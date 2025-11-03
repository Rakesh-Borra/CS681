package umbcs681.hw7.fs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import umbcs681.hw7.fs.Directory;
import umbcs681.hw7.fs.FSElement;
import umbcs681.hw7.fs.File;
import umbcs681.hw7.fs.FileSystem;
import umbcs681.hw7.fs.Link;

class TimeStampComparatorTest {

	private static FileSystem fs;
	private static Comparator<FSElement> comparator;

    @BeforeAll
    public static void setUpFS() {
        FileSystem.resetFileSystem();
        fs = TestFixtureInitializer.createFS(); 
        comparator=(FSElement o1, FSElement o2)->o1.getCreationTime().compareTo(o2.getCreationTime());

    }

    private String[] elementsToNames(LinkedList<? extends FSElement> elements){
        String[] names=new String[elements.size()];
        final int[] index = {0};
        elements.forEach(e -> {
            names[index[0]++] = e.getName();
        });
        return names;

    }
    
    
    @Test
    void testGetChildren(){
        Directory root=fs.getRootDirs().get(0);
        LinkedList<FSElement> children=root.getChildren(comparator);

        String[] expected={"hw01", "readme.md"};
        String[] actual=elementsToNames(children);

        assertArrayEquals(expected, actual);

    }

    @Test
    void testGetFiles(){

        Directory root = fs.getRootDirs().get(0);
        LinkedList<File> files=root.getFiles(comparator);

        String[] expected={"readme.md"};
        String[] actual = elementsToNames(files);

        assertArrayEquals(expected, actual);

    }

    @Test
    void testGetSubDirectories(){
        Directory root = fs.getRootDirs().get(0);
        LinkedList<Directory> subDirs=root.getSubDirectories(comparator);

        String[] expected={"hw01"};
        String[] actual=elementsToNames(subDirs);

        assertArrayEquals(expected, actual);

    }

    @Test
    void testGetLinks() {
        Directory root=fs.getRootDirs().get(0);
        Directory hw01=root.getSubDirectories().get(0);
        LinkedList<Link> links=hw01.getLinks(comparator);

        String[] expected={"rm.md:Link"};
        String[] actual=elementsToNames(links);

        assertArrayEquals(expected, actual);

    }


}
