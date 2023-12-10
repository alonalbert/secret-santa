package com.alonalbert.secretsanta

data class Participant(val name: String, val email: String) {
  override fun toString() = "$name ($email)"
}
