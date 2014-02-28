package com.interflora.vfs;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;

public class VFSWithConUrl {
	public static String createConnectionString(String hostName,String username, String password,
			 String remoteFilePath) {
		//return "sftp://" + username + ":" + password + "@" + hostName +"/"
			//	+ remoteFilePath;
		System.out.println("Forming the Connection URl");
		//return "ftp://" + hostName + remoteFilePath;
		return "ftp://" + username + ":" + password + "@" + hostName + remoteFilePath;	
	}
	public static void main(String[] args) {
		
	
	FileSystemManager fs=null;
	FileSystemOptions fsoptions = new FileSystemOptions();
	FtpFileSystemConfigBuilder.getInstance().setPassiveMode(fsoptions, true);
	StandardFileSystemManager manager = new StandardFileSystemManager();

	boolean flag;
	try {
		manager.init();
	

		// Create remote object
		FileObject remote = manager.resolveFile(createConnectionString(
				"rose-misc01.hyd.ftd.untd.com","rosedev","test123","/FtpAdapter/RosePaths.t1xt"),
				/*createDefaultOptions(this.connMode)*/fsoptions);

		flag = remote.exists();
		System.out.println("Is exists"+flag);
	
		
	} catch (FileSystemException e) {
		System.out.println("Runtime "+e.getMessage());
		throw new RuntimeException(e);	
} catch (Exception e) {
	//logger.error(logKey + " openConnection() " + e.getMessage());
	System.out.println("Error: "+e.getMessage());
	
}
finally {
	manager.close();
}
	


}
}