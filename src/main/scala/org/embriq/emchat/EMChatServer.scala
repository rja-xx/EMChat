package org.embriq.emchat

import scala.actors.Actor
import java.lang.String

object EMChatServer extends Actor {

  private var channels: List[Channel] = Nil;

  def act() = {
    loop {
      react {
        case Connect(name, sender) => if (getChannel(name) != null) {
          getChannel(name).addClient(sender)
        } else {
          sender ! new ErrorMessage("Channel does not exist!")
        }
        case CreateChannel(name) => if (getChannel(name) == null) channels = new Channel(name) :: channels;
      }
    }
  }

  def getChannel(name: String): Channel = {
    (for (c <- channels if (c.name.equals(name))) yield c) match {
      case Nil => null;
      case x :: xs => x;
    }
  }

}

  

