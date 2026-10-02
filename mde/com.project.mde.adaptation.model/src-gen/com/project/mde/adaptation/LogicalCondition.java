/**
 */
package com.project.mde.adaptation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logical Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.LogicalCondition#getLeft <em>Left</em>}</li>
 *   <li>{@link com.project.mde.adaptation.LogicalCondition#getOperator <em>Operator</em>}</li>
 *   <li>{@link com.project.mde.adaptation.LogicalCondition#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getLogicalCondition()
 * @model
 * @generated
 */
public interface LogicalCondition extends Condition {
	/**
	 * Returns the value of the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' containment reference.
	 * @see #setLeft(Condition)
	 * @see com.project.mde.adaptation.AdaptationPackage#getLogicalCondition_Left()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Condition getLeft();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.LogicalCondition#getLeft <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' containment reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(Condition value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.adaptation.LogicalOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see com.project.mde.adaptation.LogicalOperator
	 * @see #setOperator(LogicalOperator)
	 * @see com.project.mde.adaptation.AdaptationPackage#getLogicalCondition_Operator()
	 * @model required="true"
	 * @generated
	 */
	LogicalOperator getOperator();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.LogicalCondition#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see com.project.mde.adaptation.LogicalOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(LogicalOperator value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(Condition)
	 * @see com.project.mde.adaptation.AdaptationPackage#getLogicalCondition_Right()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Condition getRight();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.LogicalCondition#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(Condition value);

} // LogicalCondition
