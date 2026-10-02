/**
 */
package com.project.mde.adaptation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.IntegerValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getIntegerValue()
 * @model
 * @generated
 */
public interface IntegerValue extends Value {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(long)
	 * @see com.project.mde.adaptation.AdaptationPackage#getIntegerValue_Value()
	 * @model required="true"
	 * @generated
	 */
	long getValue();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.IntegerValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(long value);

} // IntegerValue
