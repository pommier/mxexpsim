print ("serveur lance");
# Server code

import SimpleXMLRPCServer, time, re, threading


class Server():
    def __init__(self):
        import string
        self.python_string = string
        self.Terminated=False
        
    def getTerminated(self):
        return self.Terminated

    def setTerminated(self, boolean):
        self.Terminated = boolean 

    def load_queue(self,clientParam):
        self.DictParameter =eval(clientParam)   
        return 0
       
    def start_queue(self):  

        collect = ThreadCollect(self)
        collect.setName ( 'thread de collection des donnees' )
        collect.start()
        return 0

    def queue_status(self):
        if  self.Terminated==False:
            return "running"
        else:
            return "data collected"

class ThreadCollect ( threading.Thread ):
    def __init__(self,server, ):
        threading.Thread.__init__(self)
        self.server=server 
   
    def run ( self):
       
        for queueEntry in self.server.DictParameter:
            imageIndex=int(queueEntry["first_image"])
            lastImage=imageIndex+int(queueEntry["number_images"])
            while imageIndex<lastImage:
                print imageIndex
                fileName=queueEntry["prefix"]
                fileName=fileName+"_"+queueEntry["run_number"]
                fileName=fileName+"_%04d.jpg" % imageIndex
                print fileName
                imageIndex=imageIndex+1
                time.sleep(1)
        print  self.server.getTerminated()
        print "fin de collection "
        self.server.setTerminated(True)
        print  self.server.getTerminated()
        
    
server = SimpleXMLRPCServer.SimpleXMLRPCServer(("localhost", 8888))
server.register_instance(Server())
server.serve_forever()

