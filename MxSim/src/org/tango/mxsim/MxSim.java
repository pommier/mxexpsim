/*----- PROTECTED REGION ID(MxSim.java) ENABLED START -----*/
//=============================================================================
//
// file :        MxSim.java
//
// description : Java source for the MxSim class and its commands.
//               The class is derived from Device. It represents the
//               CORBA servant object which will be accessed from the
//               network. All commands which can be executed on the
//               MxSim are implemented in this file.
//
// project :     MX simulator
//
// This file is part of Tango device class.
// 
// Tango is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// Tango is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with Tango.  If not, see <http://www.gnu.org/licenses/>.
// 
// $Author:  $
//
// $Revision:  $
// $Date:  $
//
// $HeadURL:  $
//
//=============================================================================
//                This file is generated by POGO
//        (Program Obviously used to Generate tango Object)
//=============================================================================

/*----- PROTECTED REGION END -----*/	//	MxSim.java

package org.tango.mxsim;

/*----- PROTECTED REGION ID(MxSim.imports) ENABLED START -----*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.tango.DeviceState;
import org.tango.server.InvocationContext;
import org.tango.server.ServerManager;
import org.tango.server.annotation.AroundInvoke;
import org.tango.server.annotation.Attribute;
import org.tango.server.annotation.AttributeProperties;
import org.tango.server.annotation.ClassProperty;
import org.tango.server.annotation.Command;
import org.tango.server.annotation.Delete;
import org.tango.server.annotation.Device;
import org.tango.server.annotation.DeviceProperty;
import org.tango.server.annotation.DynamicManagement;
import org.tango.server.annotation.Init;
import org.tango.server.annotation.State;
import org.tango.server.annotation.StateMachine;
import org.tango.server.annotation.Status;
import org.tango.server.dynamic.DynamicManager;
import org.tango.utils.DevFailedUtils;

//	Import Tango IDL types
import fr.esrf.Tango.*;

/*----- PROTECTED REGION END -----*/	//	MxSim.imports

/**
 *  MxSim class description:
 *    Simulate an MX experiment
 */

@Device
public class MxSim {

    private static final Logger logger = LoggerFactory.getLogger(MxSim.class);
    private static final XLogger xlogger = XLoggerFactory.getXLogger(MxSim.class);
	//========================================================
	//	Programmer's data members
	//========================================================
    /*----- PROTECTED REGION ID(MxSim.variables) ENABLED START -----*/
    
    //	Put static variables here
    
    /*----- PROTECTED REGION END -----*/	//	MxSim.variables
	/*----- PROTECTED REGION ID(MxSim.private) ENABLED START -----*/
	
	//	Put private variables here
	
	/*----- PROTECTED REGION END -----*/	//	MxSim.private

	//========================================================
	//	Property data members and related methods
	//========================================================


	//========================================================
	//	Miscellaneous methods
	//========================================================
	/**
	 * Initialize the device.
	 * 
	 * @throws DevFailed if something fails during the device initialization.
	 */
	@Init(lazyLoading = false)
	public final void initDevice() throws DevFailed {
		xlogger.entry();
		logger.debug("init");
		/*----- PROTECTED REGION ID(MxSim.initDevice) ENABLED START -----*/
		
		setState(DevState.OFF);
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.initDevice
		xlogger.exit();
	}

	/**
	 * all resources may be closed here. Collections may be also cleared.
	 * 
	 * @throws DevFailed if something fails during the device object delation.
	 */
	@Delete
	public final void deleteDevice() throws DevFailed {
		xlogger.entry();
		/*----- PROTECTED REGION ID(MxSim.deleteDevice) ENABLED START -----*/
		
		//	Put your device clearing code here
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.deleteDevice
		xlogger.exit();
	}

	/**
	 * Method called before and after command and attribute calls.
	 * @param ctx the invocation context
	 * @throws DevFailed if something fails during the this method execution.
	 */
	@AroundInvoke
	public final void aroundInvoke(final InvocationContext ctx) throws DevFailed {
		xlogger.entry(ctx);
		/*----- PROTECTED REGION ID(MxSim.aroundInvoke) ENABLED START -----*/
		
		//	Put aroundInvoke code here
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.aroundInvoke
		xlogger.exit();
	}

	
	/**
	 * dynamic command and attribute management. Will be injected by the framework.
	 */
	@DynamicManagement
	private DynamicManager dynamicManager;
	/**
	 * @param dynamicManager the DynamicManager instance 
	 */
	public void setDynamicManager(final DynamicManager dynamicManager) {
		this.dynamicManager = dynamicManager;
		/*----- PROTECTED REGION ID(MxSim.setDynamicManager) ENABLED START -----*/
		
		//	Put your code here
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.setDynamicManager
	}


	//========================================================
	//	Attribute data members and related methods
	//========================================================
	/**
	 * Attribute ImageDirectory, String, Scalar, READ_WRITE
	 * description:
	 *     
	 */
	@Attribute(name="ImageDirectory", isMemorized=true, isMemorizedAtInit=true)
	private String imageDirectory;
	/**
	 * Read attribute ImageDirectory
	 * 
	 * @return attribute value
	 */
	public String getImageDirectory() {
		xlogger.entry();
		/*----- PROTECTED REGION ID(MxSim.getImageDirectory) ENABLED START -----*/
		
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.getImageDirectory
		xlogger.exit();
		return imageDirectory;
	}
	/**
	 * Write attribute ImageDirectory
	 * @param  imageDirectory value to write
	 */
	public void setImageDirectory(String imageDirectory){
		xlogger.entry();
		/*----- PROTECTED REGION ID(MxSim.setImageDirectory) ENABLED START -----*/
		
		if (imageDirectory != null) this.imageDirectory = imageDirectory;
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.setImageDirectory
		xlogger.exit();
	}
	
	/**
	 * Attribute Motors, String, Scalar, READ
	 * description:
	 *     
	 */
	@Attribute(name="Motors")
	private String motors;
	/**
	 * Read attribute Motors
	 * 
	 * @return attribute value
	 */
	public String getMotors() {
		xlogger.entry();
		/*----- PROTECTED REGION ID(MxSim.getMotors) ENABLED START -----*/
		
		motors = "none";
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.getMotors
		xlogger.exit();
		return motors;
	}
	
	/**
	 * Attribute suffix, String, Scalar, READ
	 * description:
	 *     
	 */
	@Attribute(name="suffix", isPolled=true, pollingPeriod=3000)
	private String suffix;
	/**
	 * Read attribute suffix
	 * 
	 * @return attribute value
	 */
	public String getsuffix() {
		xlogger.entry();
		/*----- PROTECTED REGION ID(MxSim.getsuffix) ENABLED START -----*/
		
		//	Put read attribute code here
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.getsuffix
		xlogger.exit();
		return suffix;
	}
	

	//========================================================
	//	Command data members and related methods
	//========================================================
	/**
	 * The state of the device
	*/
	@State
	private DevState state = DevState.UNKNOWN;
	/**
	 * Execute command "State".
	 * description: This command gets the device state (stored in its 'state' data member) and returns it to the caller.
	 * @return Device state
	 * @throws DevFailed if command execution failed.
	 */
	public final DevState getState() throws DevFailed {
		/*----- PROTECTED REGION ID(MxSim.getState) ENABLED START -----*/
		
		//	Put state code here
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.getState
		return state;
	}
	/**
	 * Set the device state
	 * @param state the new device state
	 */
	public void setState(final DevState state) {
		this.state = state;
	}
	
	/**
	 * The status of the device
	 */
	@Status
	private String status = "Server is starting. The device state is unknown";
	/**
	 * Execute command "Status".
	 * description: This command gets the device status (stored in its 'status' data member) and returns it to the caller.
	 * @return Device status
	 * @throws DevFailed if command execution failed.
	 */
	public final String getStatus() throws DevFailed {
		/*----- PROTECTED REGION ID(MxSim.getStatus) ENABLED START -----*/
		
		status = "MxSim is in OFF state";
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.getStatus
		return status;
	}
	/**
	 * Set the device status
	 * @param status the new device status
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * Execute command "StartDataCollect".
	 * description: Start the data collection
	 * @throws DevFailed if command execution failed.
	 */
	@Command(name="StartDataCollect", inTypeDesc="", outTypeDesc="")
	public void StartDataCollect() throws DevFailed {
		xlogger.entry();
		/*----- PROTECTED REGION ID(MxSim.startDataCollect) ENABLED START -----*/
		
		//	Put command code here
		
		/*----- PROTECTED REGION END -----*/	//	MxSim.startDataCollect
		xlogger.exit();
	}
	

	//========================================================
	//	Programmer's methods
	//========================================================
	/*----- PROTECTED REGION ID(MxSim.methods) ENABLED START -----*/
	
	//	Put your own methods here
	
	/*----- PROTECTED REGION END -----*/	//	MxSim.methods


	
	
	
	
	/**
	 * Starts the server.
	 * @param args program arguments (instance_name [-v[trace level]]  [-nodb [-dlist <device name list>] [-file=fileName]])
	 */
	public static void main(final String[] args) {
		ServerManager.getInstance().start(args, MxSim.class);
		System.out.println("------- Started -------------");
	}
}