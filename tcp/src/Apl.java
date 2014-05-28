import java.net.InetAddress;

public class Apl {
	public static void main(String[] args) throws Exception 
	{
		
		//Searching udp packet. 
		System.out.println("Search for spark IP-Broadcast");
		UDPServer udpserver = new UDPServer();
		udpserver.search();
		
		System.out.println("UDP PACKET FOUND");
		System.out.println("Spark Located at: " + udpserver.getIP().toString());
		
		TCPClient client = new TCPClient();
		InetAddress iP = null ;
		boolean hasIP = false;
		//Wait for an IP to be found.
		while(!hasIP)
		{
			 iP = udpserver.getIP();
			 hasIP = !( iP.toString().equals(null));
		}
		//Make TCP connection with ip adress
		client.setIP(iP);
		client.connect();
			
		//make an test button interface.(button triggers sendData).
		exampleJpanel panel = new exampleJpanel(client);
	}
}