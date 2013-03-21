'''
Created on 13 fevr. 2013
@author: Jonathan
'''

# Server code

import SimpleXMLRPCServer, time, threading, os, shutil,PyTango





tangotest=PyTango.DeviceProxy("tango://localhost:12345/tmp/test/device#dbase=no");
tangotest.command_inout("test")
