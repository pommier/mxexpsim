'''
Created on Mar 22, 2013

@author: pommier
'''
import time, xmlrpclib, os

import MxCuBEXMLRPCServer

if __name__ == "__main__":
        serverXMLRPC=MxCuBEXMLRPCServer.MxCuBEXMLRPCServer()
        serverXMLRPC.serverLaunch()
        sourceDirectorypath = "/scisoft/pxsoft/data/mxexpsim/id14eh4"
        time.sleep(1)
        print os.system("hostname")
        configuration={'sourceDirectoryPath':  sourceDirectorypath,'suffix':'.img'}
        serverMxCubeSimulator=xmlrpclib.ServerProxy("http://localhost:8888")
        serverMxCubeSimulator.setConfiguration(configuration)
        serverXMLRPC.serve_forever()


#
#        
#server= Server.Server()     
#simpleXMLRPCServer = SimpleXMLRPCServer.SimpleXMLRPCServer(("localhost", server.port))
#simpleXMLRPCServer.register_instance(server)        
#simpleXMLRPCServer.serve_forever()    

