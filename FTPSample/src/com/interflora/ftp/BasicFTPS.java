package com.interflora.ftp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.TrustManagerUtils;


public class BasicFTPS {

/**
 * 
 */
private static final long serialVersionUID = -11L;





public static void main(String []args) throws SocketException, IOException
{



    //flf.Invoke(null, null);
    FTPSClient c = new FTPSClient("SSL",false);//SSL, false
    c.setNeedClientAuth(true);
    System.out.println("====-==="+c.getNeedClientAuth());
   // c.setTrustManager(TrustManagerUtils.getValidateServerCertificateTrustManager());
    System.out.println("Setted trustmanaget and now trying to connect to FTPS server.........");
   try
   {
    c.connect("rose-misc01.hyd.ftd.untd.com",21);
    c.login("rosedev", "test123");
    System.out.println("=====================Clent Auth neede?"+c.getAuthValue()+" \n Default Port"+c.getDefaultPort()+"Need Client Auth's"+c.getNeedClientAuth());
   /* c.execPBSZ(0);
    c.execPROT("P");*/
    c.enterLocalPassiveMode();
   /* c.connect("172.16.160.70", 21);
    c.login("demand", "quefoc5");*/
    c.changeToParentDirectory();
    System.out.println("Changed the directory success");
    for(String s: c.getReplyStrings())
    {
        System.out.println("Reply Strings are::::::"+s);
    }
    FTPFile [] f = c.listFiles();
    for(String s: c.getReplyStrings())
    {
        System.out.println(s);
    }
    for(FTPFile ff: c.listFiles("/"))
    {
        System.out.println("file");
        System.out.println(ff.getName());
    }
    System.out.println("Deleting a file");
    
    String remoteFile="/TestSftp/interflora-new.ear";
   // File file=new File(remoteFile);
    boolean  option=false;
    boolean deleted;
    if(option)
    {
  deleted= c.deleteFile(remoteFile);
  if(deleted)
  {
  	System.out.println("File deleted succesfully");
  }
    }

    System.out.println("Trying to Movefile from /TestSftp/test/random.sql to /TestSftp/random.sql ");
    FTPFile[] files=c.listFiles("/TestSftp/test");
    for (int i = 0; i < files.length; i++) {
    	System.out.println(files.toString());
		
	}
    String[] name=c.listNames("/TestSftp/test/");
    for (int i = 0; i < name.length; i++) {
    	if (!name.equals("random.sql"))
    	{
    		System.out.println("File Doesnot exists");
    	}else {
    		c.rename("/TestSftp/test/random.sql", "/TestSftp/random.sql");
    	    System.out.println("Moved successfully");
    		
    	}
		
	}
    
   //c.sendCommand("cp",/TestSftp/test/sample.txt", "/TestSftp/sample.txt");
  // System.out.println("The Trust manager is "+c.getTrustManager()); 
    
    //FileOutputStream fos=new FileOutputStream(file);
    
  
   }
    catch (IOException e)
    {
        if (c.isConnected())
        {
            try
            {
                c.disconnect();
            }
            catch (IOException f)
            {
                // do nothing
            }
        }
        System.err.println("Could not connect to server.");
        e.printStackTrace();
        System.exit(1);
    }
    //c.disconnect();

}
}




