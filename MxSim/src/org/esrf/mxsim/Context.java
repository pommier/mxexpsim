package org.esrf.mxsim;

public class Context {
	private String samplePosition;
	private String sampleOrientation;

	public Context() {
		this.samplePosition="Position of the sample";
		this.sampleOrientation="Orientation of the sample";
	}

	public String getSamplePosition() {
		return samplePosition;
	}

	public void setSamplePosition(String samplePosition) {
		this.samplePosition = samplePosition;
	}

	public String getSampleOrientation() {
		return sampleOrientation;
	}

	public void setSampleOrientation(String sampleOrientation) {
		this.sampleOrientation = sampleOrientation;
	}
	
	
	
}
