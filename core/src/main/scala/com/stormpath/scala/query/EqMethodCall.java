package com.stormpath.scala.query;

import com.stormpath.sdk.query.Criterion;
import com.stormpath.sdk.query.EqualsExpressionFactory;

/**
 * workaround for eq method on EqualsExpressionFactory
 * 
 * @author Arleigh Dickerson
 *
 */
final class EqMethodCall {
	/**
	 * This class should not be instantiated. Don't call this.
	 */
	private EqMethodCall() {
	}

	/**
	 * 
	 * @param factory
	 *            the EqualsExpressionFactory on which the eq method will be
	 *            called
	 * @param value
	 *            the value to be provided as an argument to the factory's eq
	 *            method
	 * @return the Criterion returned from the call
	 */
	static Criterion apply(EqualsExpressionFactory factory, Object value) {
		return factory.eq(value);
	}
}
