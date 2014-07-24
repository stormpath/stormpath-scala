package com.stormpath.scala.query

import com.stormpath.sdk.query.EqualsExpressionFactory
import scala.collection.JavaConversions._

/** Implicit conversions for querying the stormpath java api in scala
  *
  * Usage: import com.stormpath.scala.query.Conversions._
  *
  * @author Arleigh Dickerson
  *
  */
object Conversions {
  /** Used to workaround the eq call in EqualsExpressionFactory,
    * as scala expects a Boolean return value. use IsEq(value) instead.
    */
  implicit class ScalaEqualsExpressionFactory(factory: EqualsExpressionFactory) {

    /** Identical behavior to calling EqualsExpressionFactory.eq(value) in a java environment.
      *
      * Delegates a calling EqualsExpressionFactory.eq(value) to a java class (EqMethodCall).
      *
      * @see com.stormpath.scala.query.EqMethodCall
      */
    def isEq(value: AnyRef) = EqMethodCall.apply(factory, value)
  }
}