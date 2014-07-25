package com.stormpath.scala.util
import org.easymock._
import org.easymock.EasyMock._
import scala.reflect.ClassTag
import com.stormpath.sdk.client.Client
object MockUtils {
  def apply[T: ClassTag](strict: Boolean = true): T = {
    def cls = TypeTagUtils.typeToClass[T]
    if (strict) createStrictMock(cls) else createMock(cls)
  }
}