/**
 */
package com.project.mde.adaptation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.BooleanValue#isValue <em>Value</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getBooleanValue()
 * @model
 * @generated
 */
public interface BooleanValue extends Value {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(boolean)
	 * @see com.project.mde.adaptation.AdaptationPackage#getBooleanValue_Value()
	 * @model required="true"
	 * @generated
	 */
	boolean isValue();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.BooleanValue#isValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #isValue()
	 * @generated
	 */
	void setValue(boolean value);

} // BooleanValue
