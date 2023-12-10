package com.alonalbert.secretsanta

import kotlin.random.Random

class SecretSanta(seed: Long = System.currentTimeMillis()) {
  private val random = Random(seed)
  fun drawGiftings(participants: List<Participant>, numGifts: Int): List<Gifting> {
    while (true) {
      return try {
        tryDrawGiftings(participants, numGifts)
      } catch (e: Exception) {
//        println("Bad draw, retrying")
        continue
      }
    }
  }

  private fun tryDrawGiftings(participants: List<Participant>, numGifts: Int): List<Gifting> {
    val pool = mutableListOf<String>()
    participants.flatMapTo(pool) { participant -> (0 until numGifts).map { participant.name } }
    return participants.map { sender ->
      val eligible = pool.filter { it != sender.name }.toMutableList()
      val recipients = buildList<String> {
        repeat(numGifts) {
          val recipient = eligible[random.nextInt(eligible.size)]
          add(recipient)
          eligible.removeAll { it == recipient }
          pool.remove(recipient)
        }
      }
      Gifting(sender, recipients)
    }
  }
}