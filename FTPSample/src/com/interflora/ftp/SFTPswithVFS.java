package com.interflora.ftp;

import java.io.File;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

public class SFTPswithVFS {

	/**
	 * @param args
	 * @throws FileSystemException 
	 */
	public static void main(String[] args) throws FileSystemException {
		// TODO Auto-generated method stub
		FileSystemManager fsManager = VFS.getManager();
		 StaticUserAuthenticator auth = new StaticUserAuthenticator(null,"skarin", "BETA@*nix7"); 
		 FileSystemOptions opts = new FileSystemOptions(); 
		 SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
		// SftpFileSystemConfigBuilder.getInstance().setPreferredAuthentications(opts, "keyboard-interactive");//different authentication methods publickey,password,keyboard-interactive
		 SftpFileSystemConfigBuilder.getInstance().setKnownHosts(opts, new File("D:/ssh/known_hosts.txt"));
		 File keyFile = new File("C:/Users/skarin/Downloads/putty.ppk");
		 SftpFileSystemConfigBuilder.getInstance().setIdentities(opts, new File[]{keyFile});
		 DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
		 
//		 SftpFileSystemConfigBuilder.getInstance().setProxyPort(opts, 3128);
		 //FileObject fo = VFS.getManager().resolveFile("smb://host/anyshare/dir", opts);
		//FileObject ftpsFile = fsManager.resolveFile( "ftps://rose-misc01.hyd.ftd.untd.com/FtpAdapter",opts );
		 FileObject ftpsFile = fsManager.resolveFile( "sftp://rose-appqa01.hyd.ftd.com:22/tmp",opts );
//		 FileObject ftpsFile = fsManager.resolveFile( "sftp://skarin:BETA@*nix7@rose-appqa01.hyd.ftd.com:22/" );
		// List the children of the Jar file
		FileObject[] children = ftpsFile.getChildren();
		System.out.println( "Children of " + ftpsFile.getName().getURI() );
		for ( int i = 0; i < children.length; i++ )
		{
		    System.out.println( children[ i ].getName().getBaseName());
		}
		
	}

}
