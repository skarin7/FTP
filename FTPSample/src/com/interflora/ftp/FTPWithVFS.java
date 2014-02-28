package com.interflora.ftp;
import org.apache.commons.vfs2.FileObject;

import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.ftp.*;

public class FTPWithVFS {
	public static void main(String[] args) {
		

		//String hostName = "172.16.160.70";
		String hostName = "rose-misc01.hyd.ftd.untd.com";
		String username = "rosedev";//demand
		String password = "test123";//quevfoc5
		String localFilePath = "D:/FTP/Uploads/log4j.jar";
		String remoteFilePath = "/TestSftp/test/";
	FileSystemManager fs=null;
	FileSystemOptions fsoptions = new FileSystemOptions();
	FtpFileSystemConfigBuilder.getInstance().setPassiveMode(fsoptions, true);
	try {
		fs=VFS.getManager();
		
		FileObject path =  fs.resolveFile("ftp://rosedev:test123@rose-misc01.hyd.ftd.untd.com/TestSftp/test/sample.txt", fsoptions);
	
		System.out.println("Connection successfully established to " + path.getName().getPath());
		
		
		if(!path.exists())
		{
			System.out.println("File Doesnot exists on the remote host");
		}else
		{
			System.out.println("File Exists");
		}
		long time=path.getContent().getLastModifiedTime();
		System.out.println("Last  modified tiem of the file is :: "+time);
		long s=path.getContent().getSize();
		System.out.println("sizeof  file is :: "+s);
	
		FileObject[] details=null;
		String[] fileDetails=null;
		System.out.println("trying to connect again");
		details =  fs.resolveFile("/TestSftp/test/").getChildren();
		for (int i = 0; i < details.length; i++) {
			fileDetails[i]=details[i].getName().toString();
			System.out.println("File Names are"+fileDetails[i]);
			
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
		
	}
	
	

}
