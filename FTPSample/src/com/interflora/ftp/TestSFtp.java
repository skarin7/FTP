package com.interflora.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.UserAuthenticator;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

//import javax.tools.FileObject;

public class TestSFtp {

	/*
	 * args[0]: hostName args[1]: username args[2]: password args[3]:
	 * localFilePath args[4]: remoteFilePath
	 */
	public static void main(String[] args) {
		/*
		 * if (args.length < 5) throw new RuntimeException(
		 * "Error. Please enter " +
		 * "args[0]: hostName, args[1]: username, args[2]: password, " +
		 * "args[3]: localFilePath, args[4]: remoteFilePath.");
		 */

		String hostName = "rose-misc01.hyd.ftd.untd.com";
		//String hostName = "rose-misc01.hyd.ftd.untd.com";
		String username = "skarin";//demand
		String password = "sK@rIn@7";//quevfoc5
		String localFilePath = "D:/FTP/Uploads/log4j.jar";
		String remoteFilePath = "/Share/TEST.wsdl";
		

		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print("Press 1 for upload" + "\n 2 for check existance "
				+ "\n 3 fordownload " + "\n 4 for Deleting Remote file");
		try {
			String s = input.readLine();
			int value = Integer.parseInt(s);
			System.out.println(" You have choosen :"+value);

		
		switch (value) {
		case 1:
			upload(hostName, username, password, localFilePath, remoteFilePath);

			break;
		case 2:
			exist(hostName, username, password, remoteFilePath);

			break;
		case 3:
			download(hostName, username, password, localFilePath,
					remoteFilePath);

			break;
		case 4:
			delete(hostName, username, password, remoteFilePath);

			break;

		default:
			exist(hostName, username, password, remoteFilePath);
			break;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void upload(String hostName, String username,
			String password, String localFilePath, String remoteFilePath) {

		File f = new File(localFilePath);
		if (!f.exists())
			throw new RuntimeException("Error. Local file not found");

		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			manager.init();

			// Create local file object
			FileObject localFile = manager.resolveFile(f.getAbsolutePath());

			// Create remote file object
			FileObject remoteFile = manager.resolveFile(createConnectionString(
					hostName, remoteFilePath),
					createDefaultOptions(username,password));

			// Copy local file to sftp server
			remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);

			System.out.println("File upload success");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			manager.close();
		}
	}

	public static void download(String hostName, String username,
			String password, String localFilePath, String remoteFilePath) {

		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			manager.init();

			String downloadFilePath = localFilePath.substring(0, localFilePath
					.lastIndexOf("."))
					+ "_downlaod_from_sftp"
					+ localFilePath.substring(localFilePath.lastIndexOf("."),
							localFilePath.length());

			// Create local file object
			FileObject localFile = manager.resolveFile(downloadFilePath);

			// Create remote file object
			FileObject remoteFile = manager.resolveFile(createConnectionString(
					hostName, remoteFilePath),
					createDefaultOptions(username,password));

			// Copy local file to sftp server
			localFile.copyFrom(remoteFile, Selectors.SELECT_SELF);

			System.out.println("File download success");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			manager.close();
		}
	}

	public static void delete(String hostName, String username,
			String password, String remoteFilePath) {
		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			manager.init();

			// Create remote object
			FileObject remoteFile = manager.resolveFile(createConnectionString(
					hostName, remoteFilePath),
					createDefaultOptions(username,password));

			if (remoteFile.exists()) {
				remoteFile.delete();
				System.out.println("Delete remote file success");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			manager.close();
		}
	}

	public static boolean exist(String hostName, String username,
			String password, String remoteFilePath) {
		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			manager.init();

			// Create remote object
			FileObject remoteFile = manager.resolveFile(createConnectionString(
					hostName, remoteFilePath),
					createDefaultOptions(username,password));

			System.out.println("File exists:?? " + remoteFile.exists());

			return remoteFile.exists();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			manager.close();
		}
	}

	public static String createConnectionString(String hostName,
			 String remoteFilePath) {
		// result: "sftp://user:123456@domainname.com/resume.pdf
		//return "sftp://" + username + ":" + password + "@" + hostName +"/"
			//	+ remoteFilePath;
		return "sftp://" + hostName +"/"+ remoteFilePath;
	}

	public static FileSystemOptions createDefaultOptions(String username, String password)
			throws FileSystemException {

		 UserAuthenticator auth = new StaticUserAuthenticator(null, username,
	                password);
			FileSystemOptions opts = new FileSystemOptions();
		   try {
	            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts,
	                    auth);
	        } catch (FileSystemException ex) {
	            throw new RuntimeException("setUserAuthenticator failed", ex);
	        }
	    	SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
					opts, "no");
		// Root directory set to user home
		SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);

		// Timeout is count by Milliseconds
		SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);
		return opts;
	}
}