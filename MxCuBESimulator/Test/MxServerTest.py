'''
Created on Mar 22, 2013

@author: pommier
'''
import unittest , time, os, shutil, xmlrpclib, MxCuBEXMLRPCServer, tempfile

class Test(unittest.TestCase):
    
    def __init__(self, methodName='runTest'):
        unittest.TestCase.__init__(self, methodName=methodName)
        self.server=None
    
    def setUp(self):
        self.server=MxCuBEXMLRPCServer.MxCuBEXMLRPCServer()
        self.server.serverLaunch()
        
        if not os.path.exists('/tmp/sourceTmpTest'):   
            try:
                os.mkdir('/tmp/sourceTmpTest')
            except OSError, e:
                print e.errno, e.strerror, e.filename
          
        if not os.path.exists('/tmp/destinationTmpTest'):    
            try:
                os.mkdir('/tmp/destinationTmpTest')
            except OSError, e:
                print e.errno, e.strerror, e.filename
        pass
    
        for i in range(5):
            try:
                open("/tmp/sourceTmpTest/queue_test1_1_000"+str(i)+".testexpsim", "w")
            except IOError:
                pass

    def tearDown(self):
        self.server.stopServer()
        if  os.path.exists('/tmp/sourceTmpTest'):    
            shutil.rmtree('/tmp/sourceTmpTest')
          
        if  os.path.exists('/tmp/destinationTmpTest'): 
            shutil.rmtree('/tmp/destinationTmpTest')
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
          'directory': '/tmp/destinationTmpTest',
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
          'template': 'queue_test%d_%d_####' % (index+1, runNumber),
          'transmission': '100.0',
          'tth': ''}
            listQueue.append(dictQueueEntry)
        ServerMxCubeSimulator = xmlrpclib.ServerProxy("http://localhost:8888")
     
        strQueue = "%r" % listQueue
        ServerMxCubeSimulator.load_queue(strQueue)
        t1 = time.time()
        ServerMxCubeSimulator.start_queue()
        time.sleep(0.1)
        while ServerMxCubeSimulator.queue_status() == "running":
            print ServerMxCubeSimulator.queue_status()
            print "Waiting for queue collect to finish..."
            time.sleep(0.5)
        t2 = time.time()
        print "Elapsed time: %.2f s" % (t2-t1)       
        pass

if __name__ == "__main__":
   # Server.launchServer();
    unittest.main()