'''
Created on Mar 22, 2013

@author: pommier
'''
import unittest , time,  xmlrpclib, MxCuBEXMLRPCServer

class Test(unittest.TestCase):
    
    def __init__(self, methodName='runTest'):
        unittest.TestCase.__init__(self, methodName=methodName)
        self.serverXMLRPC=None
        self.serverMxCubeSimulator=None
    
    def setUp(self):
        self.serverXMLRPC=MxCuBEXMLRPCServer.MxCuBEXMLRPCServer()
        self.serverXMLRPC.serverLaunch()
        self.serverMxCubeSimulator=xmlrpclib.ServerProxy("http://localhost:8888")    
        pass

    def tearDown(self):
        self.serverXMLRPC.stopServer()
        pass

    def testName(self):  
        time.sleep(1)
        print  self.serverMxCubeSimulator.grid_info()
        assert(len(self.serverMxCubeSimulator.grid_info())>0)
        

if __name__ == "__main__":
    unittest.main()