package com.alonalbert.secretsanta

import javax.mail.Authenticator
import javax.mail.Message.RecipientType
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

private const val HOST = "smtp.gmail.com"

class SendMail(private val username: String, private val password: String) {
  private val session: Session

  init {
    val properties = System.getProperties()
    properties["mail.smtp.host"] = HOST
    properties["mail.smtp.port"] = "465"
    properties["mail.smtp.ssl.enable"] = "true"
    properties["mail.smtp.auth"] = "true"

    session = Session.getInstance(properties, object : Authenticator() {
      override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(username, password)
      }
    })
    session.debug = false
  }

  fun sendEmail(address: String, subject: String, content: String) {
    val message = MimeMessage(session)
    message.setFrom(InternetAddress(username))
    message.addRecipient(RecipientType.TO, InternetAddress(address))
    message.subject = subject
    message.setText(content)
    Transport.send(message)
  }
}
