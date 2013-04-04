'''
Created on Mar 22, 2013

@author: pommier
'''
import time, xmlrpclib

import MxCuBEXMLRPCServer

if __name__ == "__main__":
        serverXMLRPC=MxCuBEXMLRPCServer.MxCuBEXMLRPCServer()
        serverXMLRPC.serverLaunch()
        sourceDirectorypath = "/scisoft/pxsoft/data/mxexpsim/id23eh1"
        time.sleep(1)
        configuration={'sourceDirectoryPath':  sourceDirectorypath,'suffix':'.cbf'}
        serverMxCubeSimulator=xmlrpclib.ServerProxy("http://localhost:8888")
        serverMxCubeSimulator.setConfiguration(configuration)


#
#        
#server= Server.Server()     
#simpleXMLRPCServer = SimpleXMLRPCServer.SimpleXMLRPCServer(("localhost", server.port))
#simpleXMLRPCServer.register_instance(server)        
#simpleXMLRPCServer.serve_forever()    

