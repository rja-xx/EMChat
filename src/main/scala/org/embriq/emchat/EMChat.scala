package org.embriq.emchat

object EMChat {
  def main(args: Array[String]): Unit = {
    EMChatServer.start();
    val client: EMChatClient = new EMChatClient("roger");
    client.start()
    for (x <- List.range(1, 5000)) {
      client.createChannel("test" + x)
    }
    client.createChannel("test");
    Thread.sleep(1000)
    client.connect("test");
    Thread.sleep(1000)
    client.speak("Hello world!");
    Thread.sleep(1000);
    System.exit(0
    );
  }
}                               //denne.meldingen.skal.ikke.brukes
