package com.stormpath.scala.client

import com.stormpath.sdk.client.Client
import com.stormpath.scala.client.RichClient.client2rich
import com.stormpath.scala.util.MockUtils
import scala.reflect.ClassTag
import com.stormpath.sdk.account.Account
import org.junit.Test
import org.junit.Ignore
import com.stormpath.sdk.application.Application
class RichClientTests {
  val nullClient: Client = null
  val validClient: Client = MockUtils[Client]()

  @Test(expected = classOf[IllegalStateException])
  def testInitWithNullClient: Unit = init(nullClient)

  @Test
  def testInitValidClient: Unit = Option(init(validClient)).ensuring(_.isDefined)

  @Test
  @Ignore("Implement me!")
  def testFactory: Unit = {}

  @Test
  @Ignore("Implement me!")
  def testFind: Unit = {}

  private def init(client: Client): RichClient = client
}