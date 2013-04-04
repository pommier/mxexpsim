package org.esrf.mxsim.test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.esrf.mxsim.ImageGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImageGeneratorTest {

	private File sourceFolder;
	private File destinationFolder;

	@Before
	public void init(){
		/* Create source Temporary folder */
		this.sourceFolder =new File("/tmp/sourceTmpTest");
		if(!sourceFolder.exists()){
			System.out.println("The source folder doesn't exist, is created now");
			sourceFolder.mkdir();
		}
		else{
			System.out.println("The source folder already exist");
		}

		/* Create source Temporary folder */
		this.destinationFolder =new File("/tmp/destinationTmpTest");
		if(!destinationFolder.exists()){
			System.out.println("The destination folder doesn't exist, is created now");
			destinationFolder.mkdir();

		}
		else{
			System.out.println("The destination folder already exist");
		}

		/* Create 4 files to test it into the function */
		for(int i=0;i<4;i++){
			File testFile=new File ("/tmp/sourceTmpTest/img-test_0_000"+i+".testexpsim");
			try {
				if (testFile.createNewFile()){
					System.out.println("Source file "+i +" is created!");
				}else{
					System.out.println("Source file "+i +" already exists.");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Test
	public void testImageGenerator() {
	
		ImageGenerator generator = new ImageGenerator(0, 4, "img-test_0_####.testexpsim", "/tmp/sourceTmpTest", "/tmp/destinationTmpTest/");
		generator.start();
		int numberLoopStatus=0;
		while ((generator.status().equals("failure") || generator.status().equals("successful")) && numberLoopStatus<5){
			try {
				Thread.sleep(1000);
				numberLoopStatus++;
				System.out.println(numberLoopStatus);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}

		assertTrue("Time out waiting for running status",numberLoopStatus<5);
		numberLoopStatus=0;
		while (generator.status().equals("running") && numberLoopStatus<5){
		
			System.out.println("Collecting Data");
			try {
				Thread.sleep(1000);
				numberLoopStatus++;
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}	
		assertTrue("Time out waiting for successful or failure status",numberLoopStatus<5);
		System.out.println(generator.status());
		assertEquals(sourceFolder.list().length, destinationFolder.list().length);	
		System.out.println(" data collected successfully !");
	}

	@After
	public void clear(){	
		if(sourceFolder.exists()){			
			try {
				FileUtils.deleteDirectory(sourceFolder);
				System.out.println("The source folder has been removed");	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("The source folder can't be removed, it's doesn't exist");
		}

		if(destinationFolder.exists()){			
			try {
				FileUtils.deleteDirectory(destinationFolder);
				System.out.println("The destination folder has been removed");	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("The destination folder can't be removed, it's doesn't exist");
		}

	}
}
