/**
 */
package com.project.mde.adaptation;

import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decimal Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.DecimalValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getDecimalValue()
 * @model
 * @generated
 */
public interface DecimalValue extends Value {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(BigDecimal)
	 * @see com.project.mde.adaptation.AdaptationPackage#getDecimalValue_Value()
	 * @model required="true"
	 * @generated
	 */
	BigDecimal getValue();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.DecimalValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(BigDecimal value);

} // DecimalValue
