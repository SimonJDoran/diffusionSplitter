/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diffusionsplitter;

import etherj.PathScan;
import etherj.dicom.DicomReceiver;
import etherj.dicom.DicomToolkit;
import etherj.dicom.Patient;
import etherj.dicom.PatientRoot;
import etherj.dicom.Series;
import etherj.dicom.SopInstance;
import etherj.dicom.Study;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dcm4che2.data.DicomObject;

/**
 *
 * @author simond
 */
public class DiffusionSplitter
{
   private static final Logger logger =
		LoggerFactory.getLogger(DiffusionSplitter.class);
   
   private static final String SEP = File.separator; 

	private final DicomToolkit dcmTk = DicomToolkit.getDefaultToolkit();

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
      DiffusionSplitter  ds = new DiffusionSplitter();
      ds.run();
      
   }
   
   private void run()
   {
      PatientRoot proot = scanPath("/Volumes/Marta\\ Toshiba\\ HDD/MARTA/Casi\\ IVIM/AJJOUR");
   }
   
   
   private PatientRoot scanPath(String path)
	{
		logger.info("DICOM search: " + path);
		
		DicomReceiver         dcmRec   = new DicomReceiver();
		PathScan<DicomObject> pathScan = dcmTk.createPathScan();
		
		pathScan.addContext(dcmRec);
		PatientRoot root = null;
		try
		{
			pathScan.scan(path, true);
			root = dcmRec.getPatientRoot();
		}
		catch (IOException ex)
		{
			logger.warn(ex.getMessage(), ex);
		}
		return root;
	}
   
}
