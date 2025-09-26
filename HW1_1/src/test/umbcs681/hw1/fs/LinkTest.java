package umbcs681.hw1.fs;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import umbcs681.hw1.fs.Directory;
import umbcs681.hw1.fs.FileSystem;
import umbcs681.hw1.fs.Link;

class LinkTest {

	private static FileSystem fs;
	
	@BeforeAll
    public static void setUpFS() {
		FileSystem.resetFileSystem();
        fs = TestFixtureInitializer.createFS();
    }
	
	private String[] linkToStringArray(Link l) {
        return new String[] {
            l.getName(),
            String.valueOf(l.getSize()),
            String.valueOf(l.getCreationTime()),
            l.getTarget().getName()
        };
    }
	
	@Test
	public void verifyLinkEqualityRmMd() {
	    Directory umbcs680 = fs.getRootDirs().get(0);
	    Directory hw01 = umbcs680.getSubDirectories().get(0);
	    Link rmMdLink = hw01.getLinks().get(0);
	    String[] expected = {"rm.md:Link", "0",LocalDateTime.of(2025, 12, 12, 0, 8).toString(),"readme.md"};
	    String[] actual = linkToStringArray(rmMdLink);	    
	    assertArrayEquals(expected, actual);

	}


}
