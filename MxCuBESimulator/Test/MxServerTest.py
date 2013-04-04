'''
Created on Mar 22, 2013

@author: pommier
'''
import unittest , time, os, shutil, xmlrpclib, MxCuBEXMLRPCServer, tempfile

class Test(unittest.TestCase):
    
    def __init__(self, methodName='runTest'):
        unittest.TestCase.__init__(self, methodName=methodName)
        self.serverXMLRPC=None
        self.serverMxCubeSimulator=None
        self.destinationDirectorypath=None
        self.fileNumberSource=None
        self.fileNumberDestination=None
    
    def setUp(self):
        self.serverXMLRPC=MxCuBEXMLRPCServer.MxCuBEXMLRPCServer()
        self.serverXMLRPC.serverLaunch()
        self.sourceDirectorypath = tempfile.mkdtemp()
        self.destinationDirectorypath = tempfile.mkdtemp()
        time.sleep(1)
        configuration={'sourceDirectoryPath':  self.sourceDirectorypath}
        self.serverMxCubeSimulator=xmlrpclib.ServerProxy("http://localhost:8888")
        self.serverMxCubeSimulator.setConfiguration(configuration)
        for i in range(5):
            try:
                open(self.sourceDirectorypath+"/queue_test1_1_000"+str(i)+".testexpsim", "w")
            except IOError:
                pass
        self.fileNumberSource = len(os.listdir(self.sourceDirectorypath)) - 1   
        pass

    def tearDown(self):
        self.serverXMLRPC.stopServer()
        if  os.path.exists(self.sourceDirectorypath):    
            shutil.rmtree(self.sourceDirectorypath)
        if  os.path.exists(self.destinationDirectorypath): 
            shutil.rmtree(self.destinationDirectorypath)
        pass

    def testName(self):
        nQueue = 1
        runNumber = nQueue
        listQueue = []
        
        for index in range(nQueue):
            dictQueueEntry = {'anomalous': 'False',
          'axis': '',
          'barcode': '',
          'centring_status': None,
          'comments': '',
          'current_osc_start': 0.0,
          'detector_mode': 'Hardware binned',
          'directory': self.destinationDirectorypath,
          'do_inducedraddam': '',
          'energy': '',
          'exposure_time': '1',
          'first_image': '0',
          'gshg': '',
          'gsvg': '',
          'in_queue': 0,
          'inv_interval': '',
          'inverse_beam': 'False',
          'kap': '',
          'kappaStart': 0,
          'kth': '',
          'location': '',
          'mad_1_energy': (False, '', ''),
          'mad_2_energy': (False, '', ''),
          'mad_3_energy': (False, '', ''),
          'mad_4_energy': (False, '', ''),
          'mad_energies': 'pk - ip - rm - rm2',
          'motors': {},
          'number_images': '4',
          'number_passes': '1',
          'osc_range': '0.00',
          'osc_start': '0.00',
          'overlap': '0',
          'phi': '',
          'phiStart': 0,
          'prefix': 'ref-x%d' % (index+1),
          'process_directory': '/tmp/destinationTmpTest', 
          'processing': 'False',
          'residues': '',
          'resolution': '',
          'run_number': '%d' % runNumber, 
          'sum_images': (False, ''),
          'template': 'queue_test%d_%d_####.testexpsim' % (index+1, runNumber),
          'transmission': '100.0',
          'tth': ''}
            listQueue.append(dictQueueEntry)

        strQueue = "%r" % listQueue
        self.serverMxCubeSimulator.load_queue(strQueue)
        t1 = time.time()
        self.serverMxCubeSimulator.start_queue()
        time.sleep(0.1)
        while  self.serverMxCubeSimulator.queue_status() == "running":
            print  self.serverMxCubeSimulator.queue_status()
            print "Waiting for queue collect to finish..."
            time.sleep(0.5)
        t2 = time.time()
        print "Status client = "+self.serverMxCubeSimulator.queue_status()
        print "Elapsed time: %.2f s" % (t2-t1)
        self.fileNumberDestination = len(os.listdir(self.destinationDirectorypath))          
        assert(self.fileNumberDestination==self.fileNumberSource) 
        pass

if __name__ == "__main__":
    unittest.main()