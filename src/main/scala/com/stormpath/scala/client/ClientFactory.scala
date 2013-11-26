package com.stormpath.scala.client

import com.stormpath.sdk.client.ClientBuilder
import com.stormpath.sdk.client.Client
import java.io.{Reader, InputStream}
import java.util.Properties
import com.stormpath.sdk.cache.CacheManager

class ClientFactory {

  private var clientBuilder: ClientBuilder = _

  def ClientFactory() {
    this.clientBuilder = new ClientBuilder
  }

  protected def createInstance: Client = {
    return clientBuilder.build
  }

  def getObjectType: Class[_] = {
    return classOf[Client]
  }

  def getClientBuilder: ClientBuilder = {
    return clientBuilder
  }

  def setClientBuilder(clientBuilder: ClientBuilder) {
    this.clientBuilder = clientBuilder
  }

  /**
   * Calls {@code clientBuilder.} {@link ClientBuilder#setApiKeyFileLocation(String) setApiKeyFileLocation(location)}.
   * See that JavaDoc for expected syntax/format.
   *
   * @param apiKeyFileLocation the file, classpath or url location of the API Key { @code .properties} file to load when
   *                                                                                      constructing the API Key to use for communicating with the Stormpath REST API.
   * @see ClientBuilder#setApiKeyFileLocation(String)
   */
  def setApiKeyFileLocation(apiKeyFileLocation: String) {
    this.clientBuilder.setApiKeyFileLocation(apiKeyFileLocation)
  }

  /**
   * Calls {@code clientBuilder.} {@link ClientBuilder#setApiKeyInputStream(java.io.InputStream) setApiKeyInputStream}.
   *
   * @param apiKeyInputStream the InputStream to use to construct a configuration Properties instance.
   * @see ClientBuilder#setApiKeyInputStream(java.io.InputStream)
   */
  def setApiKeyInputStream(apiKeyInputStream: InputStream) {
    this.clientBuilder.setApiKeyInputStream(apiKeyInputStream)
  }

  /**
   * Calls {@code clientBuilder.} {@link ClientBuilder#setApiKeyReader(java.io.Reader) setApiKeyReader}.
   *
   * @param apiKeyReader the reader to use to construct a configuration Properties instance.
   * @see ClientBuilder#setApiKeyReader(java.io.Reader)
   */
  def setApiKeyReader(apiKeyReader: Reader) {
    this.clientBuilder.setApiKeyReader(apiKeyReader)
  }

  /**
   * Calls {@code clientBuilder.} {@link ClientBuilder#setApiKeyProperties(java.util.Properties)}.
   *
   * @param properties the properties instance to use to load the API Key ID and Secret.
   * @see ClientBuilder#setApiKeyProperties(java.util.Properties)
   */
  def setApiKeyProperties(properties: Properties) {
    this.clientBuilder.setApiKeyProperties(properties)
  }

  /**
   * Calls {@code clientBuilder.} {@link ClientBuilder#setApiKeyIdPropertyName(String) setApiKeyIdPropertyName}.
   *
   * @param apiKeyIdPropertyName the name used to query for the API Key ID from a Properties instance.
   * @see ClientBuilder#setApiKeyIdPropertyName(String)
   */
  def setApiKeyIdPropertyName(apiKeyIdPropertyName: String) {
    this.clientBuilder.setApiKeyIdPropertyName(apiKeyIdPropertyName)
  }

  /**
   * Calls {@code clientBuilder.} {@link ClientBuilder#setApiKeySecretPropertyName(String) setApiKeySecretPropertyName}.
   *
   * @param apiKeySecretPropertyName the name used to query for the API Key Secret from a Properties instance.
   * @see ClientBuilder#setApiKeySecretPropertyName(String)
   */
  def setApiKeySecretPropertyName(apiKeySecretPropertyName: String) {
    this.clientBuilder.setApiKeySecretPropertyName(apiKeySecretPropertyName)
  }

  /**
   * Calls {@code clientBuilder.} {@link ClientBuilder#setCacheManager(com.stormpath.sdk.cache.CacheManager) setCacheManager}
   * using the specified Stormpath {@link com.stormpath.sdk.cache.CacheManager CacheManager} instance, but <b>note:</b>
   * <p/>
   *
   * @param cacheManager the Storpmath SDK-specific CacheManager to use for the Stormpath SDK Client's caching needs.
   */
  def setCacheManager(cacheManager: CacheManager) {
    this.clientBuilder.setCacheManager(cacheManager)
  }

}
