package org.embriq.emchat

import java.lang.String;
import scala.actors.Actor

class Channel(n: String) extends Actor {

  private var clients: List[EMChatClient] = Nil;

  def name = n

  def act() {
    loop {
      react {
        case message => clients foreach (x => x ! message);
      }
    }
  }

  def addClient(client: EMChatClient) = {
    this.start()
    client ! new Connection(this)
    clients = client :: clients;
    clients foreach (x => x ! new ChatMessage(client.user + " koblet til!", "INFO"));
  }
}