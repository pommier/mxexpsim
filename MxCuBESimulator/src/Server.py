'''
Created on 13 fevr. 2013
@author: Jonathan
'''
print ("serveur lance");
# Server code

import threading,PyTango,json, SimpleXMLRPCServer

class Server():
    def __init__(self):
        self.terminated=False
        self.port=8888
        
    def getTerminated(self):
        return self.terminated

    def setTerminated(self, boolean):
        self.terminated = boolean

    def load_queue(self,clientParam):
        self.DictParameter =eval(clientParam)
        return 0
       
    def start_queue(self):
        collect = ThreadCollect(self)
        collect.setName ( 'thread de collection des donnees' )
        collect.start()
        return 0

    def queue_status(self):
        if self.terminated==False:
            return "running"
        else:
            return "data collected"

class ThreadCollect ( threading.Thread ):
    def __init__(self,server):
        threading.Thread.__init__(self)
        self.server=server
   
    def run ( self):
        tangotest=PyTango.DeviceProxy("tango://localhost:12345/tmp/test/device#dbase=no");
        for queueEntry in self.server.DictParameter:        
            tangotest.command_inout("startGeneratingImages",json.dumps(queueEntry)) 
            processImage=False
            while  processImage==False:             
                processImage=tangotest.imageStatut 
        print "fin de collection "
        self.server.setTerminated(True)

def launchServer(): 
    server= Server()     
    simpleXMLRPCServer = SimpleXMLRPCServer.SimpleXMLRPCServer(("localhost", server.port))
    simpleXMLRPCServer.register_instance(server)        
    simpleXMLRPCServer.serve_forever()    
    
if __name__ == "__main__":
    launchServer()    
