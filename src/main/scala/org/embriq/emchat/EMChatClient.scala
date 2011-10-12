package org.embriq.emchat

import java.lang.String
import scala.actors.Actor

class EMChatClient(username: String) extends Actor {

  def user = username

  var channel: Channel = null;

  def connect(channel: String) = {
    EMChatServer ! new Connect(channel, this)
  }

  def createChannel(channel: String) = EMChatServer ! new CreateChannel(channel)

  def speak(message: String) {
    if (channel != null)
      channel ! new ChatMessage(message, username)
    else
      printf("ERROR: You are not connected to a channel")
  }

  //denne.meldingen.skal.brukes dsflksdfs
  def act() {
    loop {
      react {
        case ChatMessage(text, user) => println(channel.name + "> " + user + " says " + text);
        case ErrorMessage(text) => println("ERROR: " + text);
        case Connection(c) => println("Kobler til " + c.name); channel = c;
      }
    }
  }

}