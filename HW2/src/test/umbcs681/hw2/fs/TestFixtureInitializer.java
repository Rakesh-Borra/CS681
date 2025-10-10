package umbcs681.hw2.fs;

import java.time.LocalDateTime;

import umbcs681.hw2.fs.Directory;
import umbcs681.hw2.fs.File;
import umbcs681.hw2.fs.FileSystem;
import umbcs681.hw2.fs.Link;

public class TestFixtureInitializer {
	
	public static FileSystem createFS() {

		FileSystem fs=FileSystem.getFileSystem();
		Directory umbcs680=new Directory(null,"umbcs680",0,LocalDateTime.of(2025, 12, 12, 0, 1));
		Directory hw01=new Directory(umbcs680,"hw01",0,LocalDateTime.of(2025, 12, 12, 0, 2));
		Directory src=new Directory(hw01,"src",0,LocalDateTime.of(2025, 12, 12, 0, 3));

		File aJava=new File(src,"A.java",1,LocalDateTime.of(2025, 12, 12, 0, 4));
		File bJava=new File(src,"B.java",3,LocalDateTime.of(2025, 12, 12, 0, 5));
		File buildXml=new File(hw01,"build.xml",4,LocalDateTime.of(2025, 12, 12, 0, 6));
		File readmeMd=new File(umbcs680,"readme.md",2,LocalDateTime.of(2025, 12, 12, 0, 7));
		Link rmMdLink=new Link(hw01,"rm.md:Link",0,LocalDateTime.of(2025, 12, 12, 0, 8),readmeMd);
		
		src.appendChild(aJava);
        src.appendChild(bJava);

        hw01.appendChild(src);
        hw01.appendChild(buildXml);
        hw01.appendChild(rmMdLink);

        umbcs680.appendChild(hw01);
        umbcs680.appendChild(readmeMd);
        fs.appendRootDir(umbcs680);
		
		return fs;
		
	}


}
