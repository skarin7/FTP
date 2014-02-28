package com.interflora.ftp;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.TrustManagerUtils;

import java.io.File;

public class TestFtp {
	public void Download(String protocol) {
		FTPClient ftp = null;
		String trustmgr="all";
		if (protocol == null) {
			ftp = new FTPClient();
			System.out.println("Normal FTP client");
		} else {
			FTPSClient ftps;
			System.out.println("enterde into FTPS with protocol"+protocol);
			if (protocol.equals("false")) {
				ftps = new FTPSClient(protocol);
			} else {
				ftps = new FTPSClient(protocol);

			}
			ftp = ftps;

		}

		FileOutputStream fos = null;

		try {
			
			 int reply; ftp.connect("172.16.160.70"); 
			 ftp.login("demand","quevfoc5"); 
			 reply = ftp.getReplyCode();
			  System.out.println("Reply code from FTP server"); if
			 (!FTPReply.isPositiveCompletion(reply)) { ftp.disconnect();
			 System.err.println("FTP server refused connection.");
			 System.exit(1); }
			 
			//doConnect(ftp);

			//
			// The remote to be saved in local as
			//
			String localFilePathWhereToCopy = "D:/FTPDownloads";
			String itsNameInLocal = "Temparatureconvertor.wsdl";
			File copyTo = new File(localFilePathWhereToCopy + "/"
					+ itsNameInLocal);
			fos = new FileOutputStream(copyTo);

			//
			// Download file from FTP server
			//
			String remoteFileToDownload = "Temparatureconvertor.wsdl";
			String remoteFilePath = "/home/demand/TestSftp/";
			ftp.retrieveFile(remoteFilePath + remoteFileToDownload, fos);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void doConnect(FTPClient ftp) {
		int reply;
		try {
			 
			ftp.connect("rose-misc01.hyd.ftd.untd.com");//rose-misc01.hyd.ftd.untd.com,172.16.160.70
			//ftp.setConnectTimeout(1000);
			//ftp.set
			ftp.login("rosedev", "test123");//demand,quevfoc5,rosedev,test123
		
			
	
			
			reply = ftp.getReplyCode();
			System.out.println("Reply code from FTP server"+reply);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Some Eception in conection");
		}
	}

	public void upload(String protocol) {
		String trustmgr="valid";
		FTPClient ftp = null;
		if (protocol == null) {
			ftp = new FTPClient();
			System.out.println("Normal FTP client");
		} else {
			FTPSClient ftps;
			System.out.println("enterde into FTPS");
			System.out.println("Output"+protocol.equals("false"));
			if (protocol.equals("false")) {
				ftps = new FTPSClient(false);
			} else {
				ftps = new FTPSClient(true);

			}
			
			ftp = ftps;
			ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
			 if ("all".equals(trustmgr)) {
	                ftps.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
	            } else if ("valid".equals(trustmgr)) {
	                ftps.setTrustManager(TrustManagerUtils.getValidateServerCertificateTrustManager());
	            } else if ("none".equals(trustmgr)) {
	                ftps.setTrustManager(null);
	            }
			 

		}
		FileInputStream fis = null;
		try {
			doConnect(ftp);
			
			String localFilePath = "C:/Users/skarin/Desktop";
			String localFileName = "RosePaths.txt";
			File filenameWithCompletePath = new File(localFilePath + "/"
					+ localFileName);
			fis = new FileInputStream(filenameWithCompletePath);
			System.out.println("Formed inpt strem for the input file");
			String file = filenameWithCompletePath.getName();

			//
			// Store file to server
			//
			ftp.storeFile("/TestSftp/" + file, fis);
			ftp.logout();
			System.out.println("logged out from the server");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		TestFtp t = new TestFtp();
		final String protocol = "SSL";//SSL
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print("Press 1 for upload 2 for download");
		try {
			String s = input.readLine();
			int j = Integer.parseInt(s);
			if (j == 1) {
				t.upload(protocol);
			} else if (j == 2) {
				t.Download(protocol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
