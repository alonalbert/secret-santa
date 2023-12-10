package com.alonalbert.secretsanta

data class Gifting(val sender: Participant, val recipients: List<String>) {
  override fun toString() = "${sender.name} -> ${recipients.joinToString { it }}"
}