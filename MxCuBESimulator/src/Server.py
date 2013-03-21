'''
Created on 13 fevr. 2013
@author: Jonathan
'''
print ("serveur lance");
# Server code

import SimpleXMLRPCServer, time, threading, os, shutil,PyTango


class Server():
    def __init__(self):
        self.terminated=False
        self.suffix=".cbf"
        self.port=8888
        self.directoryOfRecording='/scisoft/pxsoft/data/mxexpsim/'
        
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
        if  self.terminated==False:
            return "running"
        else:
            return "data collected"

class ThreadCollect ( threading.Thread ):
    def __init__(self,server):
        threading.Thread.__init__(self)
        self.server=server 
        tangotest=PyTango.DeviceProxy("tango://localhost:12345/tmp/test/device#dbase=no");
   
    def run ( self):      
        for queueEntry in self.server.DictParameter:
            imageIndex=int(queueEntry["first_image"])
            lastImage=imageIndex+int(queueEntry["number_images"])
            targetDirectory=queueEntry["directory"]
            runNumber=queueEntry["run_number"]
            prefix=queueEntry["prefix"]
            template=prefix+"_"+runNumber+"_####"+self.server.suffix
            
            
            
#            while imageIndex<lastImage:
#                print imageIndex
#                fileName=queueEntry["prefix"]
#                fileName=fileName+"_"+queueEntry["run_number"]
#                fileName=fileName+"_%04d" % imageIndex+self.server.suffix
#                filePath=os.path.join(self.server.directoryOfRecording,fileName)
#                shutil.copy(filePath, queueEntry["directory"])
#                print fileName
#                imageIndex=imageIndex+1
#                time.sleep(1)
        print "fin de collection "
        self.server.setTerminated(True)
        
        


#tangotest=PyTango.DeviceProxy("tango://localhost:12345/tmp/test/device#dbase=no");
tangotest.command_inout("test","blablalba")
       

print "test1"
server= Server()      
simpleXMLRPCServer = SimpleXMLRPCServer.SimpleXMLRPCServer(("localhost", server.port))
simpleXMLRPCServer.register_instance(server)        
simpleXMLRPCServer.serve_forever()    


