package org.esrf.mxsim;

public class Configuration {
	private String sourceFileDirectory;
	
	public Configuration(){};
	
	public Configuration(String sourceFileDirectory) {
		this.sourceFileDirectory = sourceFileDirectory;
	}

	public String getSourceImageDirectory() {
		return sourceFileDirectory;
	}


	public void setSourceImageDirectory(String sourceImageDirectory) {
		this.sourceFileDirectory = sourceImageDirectory;
	};

	
}
