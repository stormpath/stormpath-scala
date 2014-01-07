/*
 * Copyright 2013 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stormpath.scala.provider

import context.StormpathExecutionContext
import java.lang.String
import com.stormpath.sdk.application.Application
import com.stormpath.sdk.authc.AuthenticationRequest
import com.stormpath.sdk.account.Account
import com.stormpath.sdk.authc.UsernamePasswordRequest
import com.stormpath.sdk.client.Client
import scala.concurrent.Future
import scala.util.Try


/**
 * A {@code Realm} implementation that uses the <a href="http://www.stormpath.com">Stormpath</a> Cloud Identity
 * Management service for authentication and authorization operations for a single Application.
 * <p/>
 * The Stormpath-registered
 * <a href="https://www.stormpath.com/docs/libraries/application-rest-url">Application's Stormpath REST URL</a>
 * must be configured as the {@code applicationRestUrl} property.
 * <h3>Authentication</h3>
 * Once your application's REST URL is configured, this service automatically executes authentication
 * attempts without any need of further configuration by interacting with the Application's
 * <a href="http://www.stormpath.com/docs/rest/api#ApplicationLoginAttempts">loginAttempts endpoint</a>.
 */
class StormpathAuthenticationService(stormpathClient: Client, stormpathApplicationRestUrl: String) {

  implicit val executionContext = StormpathExecutionContext.executionContext

  private var client = stormpathClient
  private val applicationRestUrl = stormpathApplicationRestUrl
  private var application : Application = _

  /**
   * Returns the {@code Client} instance used to communicate with Stormpath's REST API.
   *
   * @return the { @code Client} instance used to communicate with Stormpath's REST API.
   */
  def getClient: Client = {
    client
  }

  /**
   * Returns the Stormpath REST URL of the specific application communicating with Stormpath.
   * <p/>
   * Any application supported by Stormpath will have a
   * <a href="http://www.stormpath.com/docs/quickstart/authenticate-account">dedicated unique REST URL</a>.  The
   * Stormpath REST URL of the Shiro-enabled application communicating with Stormpath via this Realm must be
   * configured by this property.
   *
   * @return the Stormpath REST URL of the specific application communicating with Stormpath.
   */
  def getApplicationRestUrl: String = {
    applicationRestUrl
  }

  protected def onInit {
    assertState
  }

  private def assertState {
    if (client == null) {
      throw new IllegalStateException("Stormpath SDK Client instance must be configured.")
    }
    if (applicationRestUrl == null) {
      throw new IllegalStateException("\n\nThis application's Stormpath REST URL must be configured.\n\n  " +
        "You may get your application's Stormpath REST URL as shown here:\n\n " +
        "http://www.stormpath.com/docs/application-rest-url\n\n");
    }
  }

  //This is not thread safe, but the Client is, and this is only executed during initial Application
  //acquisition, so it is negligible if this executes a few times instead of just once.
  protected def ensureApplicationReference : Application = {
    if (application == null) {
      val href = getApplicationRestUrl
      application = client.getDataStore().getResource(href, classOf[Application])
    }
    application
  }

  def doAuthenticate(username: String, password: String): Future[Try[Account]] = Future {

    assertState
    val request = createAuthenticationRequest(username, password)
    val application = ensureApplicationReference

    try {
      Try(application.authenticateAccount(request).getAccount)
    } finally {
      //Clear the request data to prevent later memory access
      request.clear()
    }

  }

  protected def createAuthenticationRequest(username: String, password: String): AuthenticationRequest[_, _] = {
    return new UsernamePasswordRequest(username, password)
  }

}

