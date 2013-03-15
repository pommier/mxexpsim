package org.esrf.mxsim;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ImageGenerator extends Thread{
	private int firstImageToCopy;
	private int numberOfimages;
	private String sourceFileDirectory;
	private String targetDirectory;
	private static Boolean terminated=false;
	private String template;

	public ImageGenerator(int firstImageNumber, int numberOfImages,String template,
			String sourceFileDirectory, String targetDirectory) {
		super();
		this.firstImageToCopy = firstImageNumber;
		this.numberOfimages = numberOfimages;
		this.template=template;
		this.sourceFileDirectory = sourceFileDirectory;
		this.targetDirectory = targetDirectory;	
	}

	public Boolean status(){
		if(!terminated)
			return false;
		else
			return true;
	}

	public void run(){
		System.out.println("run");
		int imageIndex=firstImageToCopy;
		int lastImage=firstImageToCopy+numberOfimages;
		String fileName=null;			
		while(imageIndex<=lastImage){	
			int counterX = template.split("X").length - 1;
			String formatImageIndex = String.format("%0"+counterX+"d", imageIndex); 
			fileName=template.replaceAll("X{"+counterX+"}", formatImageIndex);	
			File fileImageSource=new File(sourceFileDirectory,fileName);
			System.out.println("path Image Source ="+fileImageSource.getPath());
			File fileDestination=new File(targetDirectory+fileName);
			System.out.println("path destination ="+fileDestination.getPath());
			try {
				FileUtils.copyFile(fileImageSource, fileDestination);
			} catch (IOException e) {
				e.printStackTrace();
			}	
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			imageIndex++;
		}

		/** add one file to make wrong the unit test **/
		/*	File testFile=new File ("/tmp/sourceTmpTest/img-test_0_0009.testexpsim");
		try {
			testFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		terminated=true;
	}
}


