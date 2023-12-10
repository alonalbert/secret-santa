package com.alonalbert.secretsanta

import com.google.gson.Gson
import java.io.FileInputStream
import java.io.InputStreamReader

fun main(args: Array<String>) {
  val config = Gson().fromJson(InputStreamReader(FileInputStream(args[0])), Config::class.java)
  val contentTemplate = FileInputStream(config.emailBodyFile).reader().readText()

  val sendMail = SendMail(config.gmailUsername, config.gmailPassword)

  val secretSanta = SecretSanta()

  val giftings = secretSanta.drawGiftings(config.participants, config.numGifts)
  giftings.forEach { gifting ->
    val content = contentTemplate
      .replace("<SENDER>", gifting.sender.name)
      .replace("<RECIPIENTS>", gifting.recipients.joinToString("") { config.emailRecipientFormat.format(it) })

    println("Sending email to ${gifting.sender}:")

    println("Subject: ${config.emailSubject}")
    println(content)
//    sendMail.sendEmail(gifting.sender.email, subject, content)
  }
}