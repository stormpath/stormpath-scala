package com.stormpath.scala.util
import scala.reflect._

/** @author Henryk Konsek
  *
  * Borrowed from https://github.com/spring-projects/spring-scala
  */
object TypeTagUtils {
  /** Returns the [[java.lang.Class]] corresponding to the given class tag.
    *
    * @param tag the class tag to convert
    * @tparam T the tag's bound type
    * @return the runtime class of the tag
    */
  def tagToClass[T](tag: ClassTag[T]): Class[T] = tag
    .runtimeClass
    .asInstanceOf[Class[T]]

  /** Returns the [[java.lang.Class]] corresponding to the given type.
    *
    * @tparam T the bound type to convert
    * @return the runtime class of the given type
    */
  def typeToClass[T: ClassTag]: Class[T] = tagToClass(classTag[T])
}