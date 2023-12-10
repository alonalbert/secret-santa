package com.alonalbert.secretsanta

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import kotlin.test.Test
import kotlin.test.fail

private val PARTICIPANTS = listOf(
  Participant("User1", "user1@gmail.com"),
  Participant("User2", "user2@gmail.com"),
  Participant("User3", "user3@gmail.com"),
  Participant("User4", "user4@gmail.com"),
  Participant("User5", "user5@gmail.com"),
  Participant("User6", "user6@gmail.com"),
  Participant("User7", "user7@gmail.com"),
)


class SecretSantaTest {
  @Test
  fun noDuplicates() {
    repeat(100) { seed ->
      val secretSanta = SecretSanta(seed.toLong())

      val giftings = try {
        secretSanta.drawGiftings(PARTICIPANTS, 2)
      } catch (e: Exception) {
        fail("Seed=$seed", e)
      }

      giftings.forEach { gifting ->
        val recipients = gifting.recipients.mapTo(HashSet()) { it }
        assertWithMessage("Gifting: $gifting Seed=$seed").that(recipients.size).isEqualTo(2)
      }
    }
  }

  @Test
  fun everyoneGetsEnough() {
    repeat(100) { seed ->
      val secretSanta = SecretSanta(seed.toLong())

      val giftings = secretSanta.drawGiftings(PARTICIPANTS, 2)

      val giftsPerPerson: Map<String, Int> = buildMap {
        giftings.forEach { gifting ->
          gifting.recipients.forEach {
            put(it, getOrPut(it) { 0 } + 1)
          }
        }
      }

      giftsPerPerson.forEach { (name, count) ->
        assertWithMessage(name).that(count).isEqualTo(2)
      }
    }
  }

  @Test
  fun noSelfGifting() {
    repeat(100) { seed ->
      val secretSanta = SecretSanta(seed.toLong())

      val giftings = secretSanta.drawGiftings(PARTICIPANTS, 2)

      giftings.forEach {
        assertThat(it.recipients).doesNotContain(it.sender)
      }
    }
  }
}