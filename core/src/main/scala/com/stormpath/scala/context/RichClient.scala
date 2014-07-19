package com.stormpath.scala.context

import com.stormpath.sdk.client.Client
import com.stormpath.sdk.resource.{ Resource, ResourceException }
import scala.reflect.{ ClassTag, classTag }
import scala.util.{ Try, Success, Failure }

/**
 *
 * @author Arleigh Dickerson
 *
 * "Pimp my library" pattern...
 * makes interaction with java Client easier in a scala environment
 *
 */
object RichClient {
  implicit def client2richClient(client: Client) = new RichClient(client)

  private def tagToClass[T](tag: ClassTag[T]): Class[T] = tag
    .runtimeClass.asInstanceOf[Class[T]]

  private def typeToClass[T: ClassTag]: Class[T] = tagToClass(classTag[T])
}

//this should be an implicit class, but I'm not sure where to put it...
class RichClient(client: Client) {
  import RichClient.typeToClass

  def apply[T <: Resource: ClassTag]: T = client.instantiate(typeToClass[T])

  def apply[T <: Resource: ClassTag](href: String): T = client
    .getResource(href, typeToClass[T])

  def catching[T <: Resource: ClassTag](call: => T): Option[T] = Try(call).toOption

  def catching[T <: Resource: ClassTag](href: String): Option[T] = catching(
    apply[T](href))
}