package com.interflora.ftp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class JschDemo {

	private static final String HOST = "rose-misc01.hyd.ftd.untd.com";

	private static final int PORT = 22;

	private static final String USER = "skarin";

	//private static final String PRIVATE_KEY_LOCATION = "C:/Users/skarin/Desktop/Downloads/putty.ppk";

	//private static final byte[] bytStream = null;
	private Logger logger = Logger.getLogger(this.getClass());
 
	public static void main(String[] args) throws Exception {

		// JSch.setLogger(new MyJschLogger());

		JSch jSch = new JSch();

		//final byte[] privateKey = getPrivateKeyAsByteStream(PRIVATE_KEY_LOCATION);

		final byte[] password = "sK@rIn@7".getBytes();
		
/*	jSch.addIdentity(USER,

		privateKey,

		null,

		password

		);*/
		//jSch.addIdentity(privateKey, password)

		// TODO: remove this line in real life. Work with known_hosts!

		Session session = jSch.getSession(USER, HOST, PORT);
		session.setPassword("sK@rIn@7");
		System.out.println("Got A session object");

		Properties config = new Properties();

		config.put("StrictHostKeyChecking", "no");

		session.setConfig(config);
		

		session.connect();
		System.out.println("Connect to session  succeful");
		Channel channel = session.openChannel("sftp");

		ChannelSftp sftp = (ChannelSftp) channel;

		sftp.connect();
		System.out.println("SFTP Connect succeful");

		final Vector files = sftp.ls(".");

		Iterator itFiles = files.iterator();

		while (itFiles.hasNext()) {

			System.out.println("Index: " + itFiles.next());

		}

		final ByteArrayInputStream in = new ByteArrayInputStream(

		"This is a sample text".getBytes());

		sftp.put(in, "test.txt", ChannelSftp.OVERWRITE);
		System.out.println("Sent data successfully and now will disconnect");

		sftp.disconnect();

		session.disconnect();

	/*	public  byte[] getPrivateKeyAsByteStream(String file) throws IOException {
			            InputStream in = null;
			            try {
		              in = openInputStream(file);
			             return IOUtils.toByteArray(in, file.length());
			         } finally {
			               IOUtils.closeQuietly(in);
		        }
			        
}
	public static FileInputStream openInputStream(final File file) throws IOException {
		          if (file.exists()) {
	              if (file.isDirectory()) {
		                 throw new IOException("File '" + file + "' exists but is a directory");
              if (file.canRead() == false) {
		                   throw new IOException("File '" + file + "' cannot be read");
		                }
		           } else {
		                throw new FileNotFoundException("File '" + file + "' does not exist");
		           }
		           return new FileInputStream(file);
		
		          }
	}
}
*/
}



	private static  byte[] getPrivateKeyAsByteStream(final String privateKeyLocation) {
		// TODO Auto-generated method stub
		byte[] bytes=null;
		try {
			   File dt = new File(privateKeyLocation);
			   InputStream fis = new FileInputStream(dt);
			   System.out.println("Formed a inpit stream");
			    bytes = IOUtils.toByteArray(fis);
			}
			catch(FileNotFoundException ex) {
			   ex.printStackTrace();
			  
			}
			catch(IOException ex) {
			   ex.printStackTrace();
			}
		

		
		return bytes;	 
	}
	
}
 
	
	

