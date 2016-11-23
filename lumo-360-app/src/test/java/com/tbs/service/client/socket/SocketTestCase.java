package com.tbs.service.client.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.junit.Test;

public class SocketTestCase {
	
	@Test
	public void socket()throws Exception{
		String hostName = "snet0105lx";
		int portNumber = 59999;
		
		    Socket kkSocket = new Socket(hostName, portNumber);
		    PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		        new InputStreamReader(kkSocket.getInputStream()));
		    out.println("414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F414A4F");
		    String i =null;
		    while((i=in.readLine())!=null){
		    	System.out.println(i);
		    }
	}

}
