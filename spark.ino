#include "SparkIntervalTimer.h";

//timer for the broadcast.
IntervalTimer myTimer;
void enableBroadcast(void);

//tcp server / udp server.
TCPServer server = TCPServer(23);
TCPClient client;
UDP Udp;
int minuut;
//to broadcast or not. used in an interrupt timer.
bool bc;

void setup()
{
    // start listening for clients
    server.begin();
    Udp.begin(4700);
    minuut = 0;
    bc=true;
    //client = 0;
    myTimer.begin(enableBroadcast, 1000, hmSec);  // blinkLED to run every 500ms (1000 * .5ms period)
}
//interrupt timer to broadcast.
void enableBroadcast(void) 
{
    if(!client.connected())
    {
        bc = true;
    }
}

void loop()
{
    connecttcp();
    if (bc)
    {
        broadcast();
    }
  
}

//accepteerd connectie en bounced bericht.
void connecttcp(){
    if (client.connected()) {
    // echo all available bytes back to the client
    while (client.available()) {
      server.write(client.read());
      //server.write("client.read()");
      if (!client.connected()) {
        client.stop();
      }
    }
  } else {
    // if no client is yet connected, check for a new connection
   
    client = server.available();
  }
}

//broadcast een bericht op udp
void broadcast(){
    //Udp.beginPacket(IPAddress (255,255,255,255), 4800);
    Udp.beginPacket(IPAddress (192,168,0,255), 4800);
     Udp.write("ip-broadcast");
     Udp.endPacket();
    bc = false;
    //delay(5000);
}