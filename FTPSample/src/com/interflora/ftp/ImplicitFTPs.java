package com.interflora.ftp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.TrustManagerUtils;

public class ImplicitFTPs {

	public static void main(String []args) throws SocketException, IOException
	{



	    //flf.Invoke(null, null);
	    FTPSClient c = new FTPSClient(true);//SSL, false
	    c.setNeedClientAuth(true);
	    System.out.println("====-==="+c.getNeedClientAuth());
	    System.out.println("Setted trustmanaget and now trying to connect to FTPS server.........");
	    c.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());//Accepts all certis ....it will perform no checks
	   System.out.println( c.getTrustManager());
	   try
	   {
	    c.connect("92.52.106.201",990);
	    c.login("interflora", "67896GKhj-^a");
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

