import java.io.*;
import java.net.*;
//this class is here to find the ip adress of your spark.
class UDPServer
{
	//Ip adress to be found. Set to null in constructor.
	private static InetAddress ip;
	
	UDPServer() throws Exception
	{
		//Set Ip adress to null.
		ip = null;
	}

	public InetAddress getIP(){
		return ip;
	}
	//Search for the packet of the spark.
	public void search() throws Exception
    {
         	DatagramSocket serverSocket = new DatagramSocket(4800);
            byte[] receiveData = new byte[1024];
            while(ip == null)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  //Set the ip adress
                  ip = IPAddress;
               }
      }
}