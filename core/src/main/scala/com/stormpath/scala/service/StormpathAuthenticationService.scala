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

package com.stormpath.scala.service

import com.stormpath.scala.context.StormpathExecutionContext.executionContext
import com.stormpath.scala.client.RichClient.client2rich
import com.stormpath.sdk.application.Application
import com.stormpath.sdk.account.Account
import com.stormpath.sdk.client.Client
import scala.concurrent.Future
import scala.util.Try
import scala.beans.BeanProperty
import com.stormpath.sdk.authc.UsernamePasswordRequest
import com.stormpath.sdk.authc.AuthenticationRequest

/** A Service that uses the <a href="http://www.stormpath.com">Stormpath</a> Cloud Identity
  * Management service for authentication and authorization operations for a single Application.
  * <p/>
  * The Stormpath-registered
  * <a href="https://www.stormpath.com/docs/libraries/application-rest-url">Application's Stormpath REST URL</a>
  * must be configured as the {@code applicationRestUrl} property.
  * <h3>Authentication</h3>
  * Once your application's REST URL is configured, this service automatically executes authentication
  * attempts without any need of further configuration by interacting with the Application's
  * <a href="http://www.stormpath.com/docs/rest/api#ApplicationLoginAttempts">loginAttempts endpoint</a>.
  *
  * @param client the {@code Client} instance used to communicate with Stormpath's REST API.
  *
  * @param applicationRestUrl the Stormpath REST URL of the specific application communicating with Stormpath.
  *
  */
class StormpathAuthenticationService(
  @BeanProperty val client: Client,
  @BeanProperty val applicationRestUrl: String) {

  //thread safe lazy initialization
  private lazy val application = {
    assertState
    client[Application](applicationRestUrl)
  }

  private def assertState {
    if (client == null) {
      throw new IllegalStateException("Stormpath SDK Client instance must be configured.")
    }
    if (applicationRestUrl == null || applicationRestUrl.size == 0) {
      throw new IllegalStateException("\n\nThis application's Stormpath REST URL must be configured.\n\n  " +
        "You may get your application's Stormpath REST URL as shown here:\n\n " +
        "http://www.stormpath.com/docs/application-rest-url\n\n");
    }
  }

  def doAuthenticate(username: String, password: String): Future[Try[Account]] = Future {
    assertState
    val request = createAuthenticationRequest(username, password)
    try {
      Try(application.authenticateAccount(request).getAccount)
    } finally {
      request.clear
    }
  }

  protected def createAuthenticationRequest(username: String, password: String): AuthenticationRequest[_, _] = {
    return new UsernamePasswordRequest(username, password)
  }
}
