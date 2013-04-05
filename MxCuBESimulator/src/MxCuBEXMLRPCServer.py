'''
Created on 13 fevr. 2013
@author: Jonathan
'''
print ("serveur lance");
# Server code

import threading,PyTango,json, SimpleXMLRPCServer, time

class MxCBESimulator():
    def __init__(self):
        self.server=None
        self.imageStatus="running"
        self.port=8888 
        
    def getTerminated(self):
        return self.imageStatus

    def setTerminated(self, status):
        self.imageStatus = status

    def load_queue(self,clientParam):
        self.DictParameter =eval(clientParam)
        return 0
       
    def start_queue(self):
        collect = ThreadCollect(self)
        collect.setName ( 'thread de collection des donnees' )
        collect.start()
        return 0

    def queue_status(self):
        return self.imageStatus
        

    def setConfiguration(self,configuration):
        print "setConfiguration connection tango :"
        tangotest2=PyTango.DeviceProxy("tango://localhost:12345/tmp/test/device#dbase=no");
        tangotest2.command_inout("setSourcePath",json.dumps(configuration)) 
        return 0
    
    def grid_info(self):
        grid={"x1": -0.050,
        "y1": -0.050,
        "dx_mm": 0.1,
        "dy_mm": 0.1,
        "steps_x": 3,
        "steps_y": 3}
        return grid
            
class ThreadCollect ( threading.Thread ):
    def __init__(self,server):
        threading.Thread.__init__(self)
        self.server=server
   
    def run ( self):
        tangotest=PyTango.DeviceProxy("tango://localhost:12345/tmp/test/device#dbase=no");
        processImage="running"
        for queueEntry in self.server.DictParameter:         
            if processImage!="failure":
                processImage="running"
                tangotest.command_inout("startGeneratingImages",json.dumps(queueEntry)) 
                time.sleep(1)       
                while  processImage=="running":                           
                    processImage=tangotest.imageStatut                 
                    time.sleep(1)
            else:
                break
        print "status MxCuBESERVER = "+processImage
        print "Data collected "
        if processImage=="successful":           
            self.server.setTerminated("successful")
        else:
            self.server.setTerminated("failure")

class MxCuBEXMLRPCServer ( threading.Thread ):
    def __init__(self):
        threading.Thread.__init__(self)
        print "server init"
        self.simpleXMLRPCServer=None
        
    def run(self): 
        print "serverLaunch"
        server= MxCBESimulator()      
        self.simpleXMLRPCServer = SimpleXMLRPCServer.SimpleXMLRPCServer(("localhost", server.port))
        self.simpleXMLRPCServer.register_instance(server)        
        self.simpleXMLRPCServer.serve_forever()  
        
    def serverLaunch(self):
        self.start()    
        
    def stopServer(self):
        print "close"
        self.simpleXMLRPCServer.shutdown()
        
    def serve_forever(self):
        self.join()
        
        
        