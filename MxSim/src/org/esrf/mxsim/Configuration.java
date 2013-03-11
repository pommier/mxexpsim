package org.esrf.mxsim;

public class Configuration {
	private String suffix;
	private String sourceImageDirectory;
	
	
	Configuration(){};	
	


	public String getSuffix() {
		return suffix;
	}


	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


	public String getSourceImageDirectory() {
		return sourceImageDirectory;
	}


	public void setSourceImageDirectory(String sourceImageDirectory) {
		this.sourceImageDirectory = sourceImageDirectory;
	};

	
}
