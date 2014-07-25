package com.stormpath.scala.client

import com.stormpath.sdk.client.Client
import com.stormpath.sdk.resource.Resource
import scala.reflect.{ ClassTag, classTag }

/** RichClient adds scala convenience methods to Stormpath's Java Client interface
  *
  * One needs to have the client2rich method in scope to enable implicit conversion
  * of Client instances to RichClient.
  *
  * @author Arleigh Dickerson
  */
object RichClient {
  /** Converts a stormpath client to a RichClient
    *
    * If you intend to use RichClient operations on a Client,
    * make sure that this method is in scope
    *
    * @param client Client to be converted to a RichClient. May not be null.
    */
  implicit def client2rich(client: Client) = new RichClient(client)
}

/** RichClient class definition.
  *
  * @param client the Client to be wrapped into a RichClient. May not be null.
  */
class RichClient(client: Client) {
  import com.stormpath.scala.util.TypeTagUtils.typeToClass

  assertState

  /** instantiates a java stormpath resource
    * @tparam the type to be instantiated. Must be a subclass of Resource.
    */
  def apply[T <: Resource: ClassTag]: T = client.instantiate(typeToClass[T])

  /** retrieves a stormpath java resource by type and href.
    * @tparam the type to located. Must be a subclass of Resource.
    * @param href, the href of the resource to be located.
    * @throws ResourceException if resource was not found
    */
  def apply[T <: Resource: ClassTag](href: String): T = client
    .getDataStore
    .getResource(href, typeToClass[T])

  private def assertState {
    if (client == null) throw new IllegalStateException("Client must not be null.")
  }
}
