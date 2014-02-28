package com.interflora.ftp;
import com.jcraft.jsch.*;

public class SampleJsch {
	    public static void main(String args[]) {
	        JSch jsch = new JSch();
	        Session session = null;
	        try {
	            session = jsch.getSession("rosedev", "rose-misc01.hyd.ftd.untd.com", 22);
	            session.setConfig("StrictHostKeyChecking", "no");
	          
	           // session.setConfig("PreferredAuthentications", "password");
	            session.setPassword("test123");
	            session.connect();
	            System.out.println(session.getServerVersion());

	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel;
	            sftpChannel.get("remotefile.txt", "localfile.txt");
	            sftpChannel.exit();
	            session.disconnect();
	        } catch (JSchException e) {
	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }
	    }
	}

