package org.esrf.mxsim;

public class Configuration {
	private String suffix;
	private String sourceFileDirectory;
	
	public Configuration(String suffix, String sourceFileDirectory) {
		this.suffix = suffix;
		this.sourceFileDirectory = sourceFileDirectory;
	}

	public String getSuffix() {
		return suffix;
	}


	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


	public String getSourceImageDirectory() {
		return sourceFileDirectory;
	}


	public void setSourceImageDirectory(String sourceImageDirectory) {
		this.sourceFileDirectory = sourceImageDirectory;
	};

	
}
