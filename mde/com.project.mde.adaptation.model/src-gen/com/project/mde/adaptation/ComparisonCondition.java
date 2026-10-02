/**
 */
package com.project.mde.adaptation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comparison Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.ComparisonCondition#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link com.project.mde.adaptation.ComparisonCondition#getOperator <em>Operator</em>}</li>
 *   <li>{@link com.project.mde.adaptation.ComparisonCondition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getComparisonCondition()
 * @model
 * @generated
 */
public interface ComparisonCondition extends Condition {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' attribute.
	 * @see #setAttribute(String)
	 * @see com.project.mde.adaptation.AdaptationPackage#getComparisonCondition_Attribute()
	 * @model required="true"
	 * @generated
	 */
	String getAttribute();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.ComparisonCondition#getAttribute <em>Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' attribute.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(String value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.adaptation.ComparisonOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see com.project.mde.adaptation.ComparisonOperator
	 * @see #setOperator(ComparisonOperator)
	 * @see com.project.mde.adaptation.AdaptationPackage#getComparisonCondition_Operator()
	 * @model required="true"
	 * @generated
	 */
	ComparisonOperator getOperator();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.ComparisonCondition#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see com.project.mde.adaptation.ComparisonOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(ComparisonOperator value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(Value)
	 * @see com.project.mde.adaptation.AdaptationPackage#getComparisonCondition_Value()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Value getValue();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.ComparisonCondition#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Value value);

} // ComparisonCondition
