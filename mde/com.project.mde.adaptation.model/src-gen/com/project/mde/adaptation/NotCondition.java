/**
 */
package com.project.mde.adaptation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Not Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.NotCondition#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getNotCondition()
 * @model
 * @generated
 */
public interface NotCondition extends Condition {
	/**
	 * Returns the value of the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' containment reference.
	 * @see #setOperand(Condition)
	 * @see com.project.mde.adaptation.AdaptationPackage#getNotCondition_Operand()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Condition getOperand();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.NotCondition#getOperand <em>Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' containment reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(Condition value);

} // NotCondition
