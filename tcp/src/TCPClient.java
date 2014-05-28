// Client Side
import java.io.*;
import java.net.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;



public class TCPClient {
	private InetAddress ip;
	private int serverPort;
	private Socket socket;
	private PrintWriter toServer;
	private BufferedReader fromServer;
	
	private static int CLIENTPORT = 23;
	
	TCPClient()
	{
		serverPort = CLIENTPORT;
		socket = null;
		toServer = null;
		fromServer = null;
		
		//close connection when program ends.
		Runtime.getRuntime().addShutdownHook(new closeConnection());
	}
	public void setIP(InetAddress ip)
	{
		this.ip = ip;
	}
	public boolean checkConnection()
	{
		if(socket == null){
			return false;
		}
		else
		{
			return true;
		}
	}

	public void connect() {
			try {
				//making connection
				System.out.println("Verbinding maken met spark op poort: " + serverPort); 
				socket = new Socket(ip,serverPort); 
				//this keeps the socket open.
				socket.setKeepAlive(true);
				
				System.out.println("Connectie opgezet met: " + socket.getRemoteSocketAddress()); 
				//make a writer and reader for the socket.
				toServer = new PrintWriter(socket.getOutputStream(),true);
				fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//this Reads incomming data.
				(new readData()).start();
			}
	//error handling.
	catch(UnknownHostException ex) {
		ex.printStackTrace();
	}
	catch(IOException e){
		e.printStackTrace();
	}
  }

	public void sendData(String msg){
		//toServer.println(msg); 
		toServer.write(msg +" \n");
		toServer.flush();
	}
	

	 class closeConnection extends Thread {

	      public void run() {
	
	         //System.out.println("Bye.");
	         try {
				disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	   }
	

	public void disconnect() throws IOException
	{
		toServer.close();
		fromServer.close();
		socket.close();
	}	
	
	public class readData extends Thread {

	    public void run() {
	    	while(true){
	    		
	    		String line = "";
	    		try{
	    		     line = fromServer.readLine();
	    		     System.out.println("Text received: " + line);
	    		   } catch (IOException e){
	    		     System.out.println("Read failed");
	    		     System.exit(1);
	    		   }
	    	
	    		System.out.println("ReadLine is reset");
	    	}
	    }

	}
	
}