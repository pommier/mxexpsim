package org.tango.mxsim.client;

import org.tango.utils.DevFailedUtils;

import fr.esrf.Tango.DevFailed;
import fr.esrf.TangoApi.DeviceProxy;

public class MxSimClient {
    public static void main(final String[] args) {
	final String deviceName = "tango://localhost:12345/tmp/test/device#dbase=no";
	try {
	    final DeviceProxy dev = new DeviceProxy(deviceName);
	    dev.command_inout("test");
	} catch (final DevFailed e) {
	    DevFailedUtils.printDevFailed(e);
	}

    }
}