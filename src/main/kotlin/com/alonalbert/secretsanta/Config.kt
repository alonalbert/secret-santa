package com.alonalbert.secretsanta

data class Config(
  val gmailUsername: String,
  val gmailPassword: String,
  val participants: List<Participant>,
  val numGifts: Int,
  val emailSubject: String,
  val emailBodyFile: String,
  val emailRecipientFormat: String,
)
