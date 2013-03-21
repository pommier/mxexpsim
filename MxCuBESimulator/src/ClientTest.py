'''
Created on 13 fevr. 2013
@author: Jonathan
'''

# Server code

import SimpleXMLRPCServer, time, threading, os, shutil,PyTango

playlist = {}
playlist["nom"] = "MeshowRandom"
playlist["musiques"] = []
playlist["musiques"].append("Best Improvisation Ever 2")
playlist["musiques"].append("My Theory (Bonus)")
playlist["nom"] = "john"
playlist["titre"] = []
playlist["titre"].append("star wars")
playlist["titre"].append("mickey")

print(playlist)
print(json.dumps(playlist))



tangotest=PyTango.DeviceProxy("tango://localhost:12345/tmp/test/device#dbase=no");
tangotest.command_inout("test")
