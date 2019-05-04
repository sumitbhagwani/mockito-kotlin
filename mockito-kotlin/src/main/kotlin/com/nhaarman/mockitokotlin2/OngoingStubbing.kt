/*
 * The MIT License
 *
 * Copyright (c) 2018 Niek Haarman
 * Copyright (c) 2007 Mockito contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.nhaarman.mockitokotlin2

import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.mockito.stubbing.OngoingStubbing
import kotlin.DeprecationLevel.ERROR
import kotlin.reflect.KClass


/**
 * Enables stubbing methods. Use it when you want the mock to return particular value when particular method is called.
 *
 * Alias for [Mockito.when].
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T> whenever(methodCall: T): OngoingStubbing<T> {
    return Mockito.`when`(methodCall)!!
}

/**
 * Sets a return value to be returned when the method is called.
 *
 * Alias for [OngoingStubbing.thenReturn].
 */
infix fun <T> OngoingStubbing<T>.doReturn(t: T): OngoingStubbing<T> {
    return thenReturn(t)
}

/**
 * Sets consecutive return values to be returned when the method is called.
 *
 * Alias for [OngoingStubbing.thenReturn].
 */
fun <T> OngoingStubbing<T>.doReturn(t: T, vararg ts: T): OngoingStubbing<T> {
    return thenReturn(t, *ts)
}

/**
 * Sets consecutive return values to be returned when the method is called.
 */
inline infix fun <reified T> OngoingStubbing<T>.doReturnConsecutively(ts: List<T>): OngoingStubbing<T> {
    return thenReturn(
          ts[0],
          *ts.drop(1).toTypedArray()
    )
}

/**
 * Sets Throwable objects to be thrown when the method is called.
 *
 * Alias for [OngoingStubbing.thenThrow].
 */
infix fun <T> OngoingStubbing<T>.doThrow(t: Throwable): OngoingStubbing<T> {
    return thenThrow(t)
}

/**
 * Sets Throwable objects to be thrown when the method is called.
 *
 * Alias for [OngoingStubbing.doThrow].
 */
fun <T> OngoingStubbing<T>.doThrow(
    t: Throwable,
    vararg ts: Throwable
): OngoingStubbing<T> {
    return thenThrow(t, *ts)
}

/**
 * Sets a Throwable type to be thrown when the method is called.
 */
infix fun <T> OngoingStubbing<T>.doThrow(t: KClass<out Throwable>): OngoingStubbing<T> {
    return thenThrow(t.java)
}

/**
 * Sets Throwable classes to be thrown when the method is called.
 */
fun <T> OngoingStubbing<T>.doThrow(
    t: KClass<out Throwable>,
    vararg ts: KClass<out Throwable>
): OngoingStubbing<T> {
    return thenThrow(t.java, *ts.map { it.java }.toTypedArray())
}

/**
 * Sets a generic Answer for the method.
 *
 * Alias for [OngoingStubbing.thenAnswer].
 */
infix fun <T> OngoingStubbing<T>.doAnswer(answer: Answer<*>): OngoingStubbing<T> {
    return thenAnswer(answer)
}

/**
 * Sets a generic Answer for the method using a lambda.
 */
infix fun <T> OngoingStubbing<T>.doAnswer(answer: (InvocationOnMock) -> T?): OngoingStubbing<T> {
    return thenAnswer(answer)
}


/**
 * Sets a generic Answer for a unary function from a lambda.
 * Examples:
 * <pre>
 *   whenever(foo.hash(any())) doAnswer String::hashCode
 *   whenever(bar.addPunctuation(any())) doAnswer { s: String -> s + "!" }
 * </pre>
 */
@JvmName("doAnswer1")
infix fun <T, R> OngoingStubbing<R>.doAnswer(f: (T) -> R): OngoingStubbing<R> {
  return thenAnswer({ inv -> f(inv.arguments[0] as T) })
}

/** Sets a generic Answer for a binary function from a lambda. */
@JvmName("doAnswer2")
infix fun <T, U, R> OngoingStubbing<R>.doAnswer(f: (T, U) -> R): OngoingStubbing<R> {
  return thenAnswer({ inv -> f(inv.arguments[0] as T, inv.arguments[1] as U) })
}

/** Sets a generic Answer for a ternary function from a lambda. */
@JvmName("doAnswer3")
infix fun <T, U, V, R> OngoingStubbing<R>.doAnswer(f: (T, U, V) -> R): OngoingStubbing<R> {
  return thenAnswer(
    {
      inv ->
        f(
          inv.arguments[0] as T,
          inv.arguments[1] as U,
          inv.arguments[2] as V
        )
      }
  )
}

/** Sets a generic Answer for a 4-argument function from a lambda. */
@JvmName("doAnswer4")
infix fun <T, U, V, W, R> OngoingStubbing<R>.doAnswer(f: (T, U, V, W) -> R): OngoingStubbing<R> {
  return thenAnswer(
    {
      inv ->
        f(
          inv.arguments[0] as T,
          inv.arguments[1] as U,
          inv.arguments[2] as V,
          inv.arguments[3] as W
        )
      }
  )
}


/** Sets a generic Answer for a 5-argument function from a lambda. */
@JvmName("doAnswer5")
infix fun <T, U, V, W, X, R> OngoingStubbing<R>.doAnswer(f: (T, U, V, W, X) -> R): OngoingStubbing<R> {
  return thenAnswer(
    {
      inv ->
        f(
          inv.arguments[0] as T,
          inv.arguments[1] as U,
          inv.arguments[2] as V,
          inv.arguments[3] as W,
          inv.arguments[4] as X
        )
      }
  )
}

/** Sets a generic Answer for a 6-argument function from a lambda. */
@JvmName("doAnswer6")
infix fun <T, U, V, W, X, Y, R> OngoingStubbing<R>.doAnswer(f: (T, U, V, W, X, Y) -> R): OngoingStubbing<R> {
  return thenAnswer(
    {
      inv ->
        f(
          inv.arguments[0] as T,
          inv.arguments[1] as U,
          inv.arguments[2] as V,
          inv.arguments[3] as W,
          inv.arguments[4] as X,
          inv.arguments[5] as Y
        )
      }
  )
}

/** Sets a generic Answer for a 7-argument function from a lambda. */
@JvmName("doAnswer7")
infix fun <T, U, V, W, X, Y, Z, R> OngoingStubbing<R>.doAnswer(f: (T, U, V, W, X, Y, Z) -> R): OngoingStubbing<R> {
  return thenAnswer(
    {
      inv ->
        f(
          inv.arguments[0] as T,
          inv.arguments[1] as U,
          inv.arguments[2] as V,
          inv.arguments[3] as W,
          inv.arguments[4] as X,
          inv.arguments[5] as Y,
          inv.arguments[6] as Z
        )
      }
  )
}

