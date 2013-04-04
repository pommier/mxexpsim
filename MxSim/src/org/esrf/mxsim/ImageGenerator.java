package org.esrf.mxsim;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ImageGenerator extends Thread{
	private int firstImageToCopy;
	private int numberOfimages;
	private String sourceFileDirectory;
	private String targetDirectory;
	private String template;
	private String collectStatus="successful";

	public ImageGenerator(int firstImageNumber, int numberOfImages,String template,
			String sourceFileDirectory, String targetDirectory) {
		super();
		this.firstImageToCopy = firstImageNumber;
		this.numberOfimages = numberOfImages;
		this.template=template;
		this.sourceFileDirectory = sourceFileDirectory;
		this.targetDirectory = targetDirectory;	
	}

	public String status(){
		return this.collectStatus;
	}
	
	public void run(){
		System.out.println("run");
		int imageIndex=firstImageToCopy;
		int lastImage=firstImageToCopy+numberOfimages;
		String fileName=null;	
		this.collectStatus="running";
		boolean errorRaise=false;
		while(imageIndex<lastImage && !errorRaise){	
			int counterX = template.split("#").length - 1;
			String formatImageIndex = String.format("%0"+counterX+"d", imageIndex); 
			fileName=template.replaceAll("#{"+counterX+"}", formatImageIndex);	
			File fileImageSource=new File(sourceFileDirectory,fileName);
			System.out.println("path Image Source ="+fileImageSource.getPath());
			File fileDestination=new File(targetDirectory+fileName);
			System.out.println("path destination ="+fileDestination.getPath());
			try {
				FileUtils.copyFile(fileImageSource, fileDestination);
			} catch (IOException e) {
				//e.printStackTrace();
				this.collectStatus="failure";
				errorRaise=true;
			}	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			imageIndex++;
		}
		
		if (!errorRaise)
			this.collectStatus="successful";
	}
}