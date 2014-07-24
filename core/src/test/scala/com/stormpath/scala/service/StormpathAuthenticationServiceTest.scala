package com.stormpath.scala.service

import org.junit.{ Assert, Test }
import com.stormpath.sdk.client.Client
import scala.concurrent._
import ExecutionContext.Implicits.global
import org.easymock.EasyMock._
import com.stormpath.sdk.ds.DataStore
import com.stormpath.sdk.authc.{ AuthenticationResult, AuthenticationRequest }
import com.stormpath.sdk.account.Account
import org.easymock._
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import org.scalatest.mock.EasyMockSugar

class StormpathAuthenticationServiceTest {

  private val appUrl = "https://api.stormpath.com/v1/applications/2TqayZ4qh74eDN4tYo2P91"

  @Test
  def testInitWithoutClient() {
    val service = new StormpathAuthenticationService(null, appUrl)

    val future = service.doAuthenticate("username", "password")

    future onSuccess {
      case msg => Assert.fail("Future should have failed")
    }
    future onFailure {
      case msg => //nothing to be done, failure expected
    }
  }

  @Test
  def testInitWithoutAppRestUrl() {
    val client = createStrictMock(classOf[Client])
    val service = new StormpathAuthenticationService(client, "")

    val future = service.doAuthenticate("username", "password")

    future onSuccess {
      case msg => Assert.fail("Future should have failed")
    }
    future onFailure {
      case msg => //nothing to be done, failure expected
    }
  }

  @Test
  def testSetClient() {

    val client = createStrictMock(classOf[Client])
    val dataStore = createMock(classOf[DataStore])
    def service = new StormpathAuthenticationService(client, appUrl)

    EasyMock.expect(client.getDataStore()).andReturn(dataStore)

    replay(client)

    service.getClient.getDataStore

    verify(client)

  }

  @Test
  def testDoGetAuthenticationInfoSuccess() {
    import java.util.Arrays

    val client = createStrictMock(classOf[Client])
    val dataStore = createStrictMock(classOf[DataStore])
    val application = createStrictMock(classOf[com.stormpath.sdk.application.Application])
    val authResult = createStrictMock(classOf[AuthenticationResult])
    val account = createStrictMock(classOf[Account])

    EasyMock.expect(client.getDataStore()).andReturn(dataStore)
    EasyMock.expect(dataStore.getResource(appUrl, classOf[com.stormpath.sdk.application.Application])).andReturn(application)
    EasyMock.expect(application.authenticateAccount(anyObject(classOf[AuthenticationRequest[_, _]]))).andAnswer(new IAnswer[AuthenticationResult] {
      def answer(): AuthenticationResult = {

        val authRequest = getCurrentArguments()(0).asInstanceOf[AuthenticationRequest[_, _]]

        Assert.assertEquals("foo", authRequest.getPrincipals)
        Assert.assertTrue(Arrays.equals("bar".toCharArray, authRequest.getCredentials.asInstanceOf[Array[Char]]))

        authResult
      }
    })

    EasyMock.expect(authResult.getAccount()).andReturn(account)
    EasyMock.expect(account.getEmail()).andReturn("foo@email.com")

    replay(client, dataStore, application, authResult, account)

    def service = new StormpathAuthenticationService(client, appUrl)
    def future = service.doAuthenticate("foo", "bar")
    val futureResult = Await.result(future, Duration(5, TimeUnit.SECONDS))

    if (futureResult.isSuccess) {
      Assert.assertEquals("foo@email.com", futureResult.get.getEmail)
    } else {
      Assert.fail("Future should not have failed")
    }

    verify(account, authResult, application, dataStore, client)

  }

}
