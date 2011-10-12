package org.embriq.emchat

import java.lang.String;

abstract class Message

case class ChatMessage(message: String, user: String) extends Message

case class Connect(name: String, sender: EMChatClient) extends Message

case class CreateChannel(name: String) extends Message

case class ErrorMessage(error: String) extends Message

case class Connection(c: Channel) extends Message
