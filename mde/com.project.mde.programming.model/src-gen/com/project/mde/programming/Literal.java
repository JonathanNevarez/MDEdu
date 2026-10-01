/**
 */
package com.project.mde.programming;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Valor léxico portable, interpretado según ValueType; compatibilidad léxica diferida.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.programming.Literal#getType <em>Type</em>}</li>
 *   <li>{@link com.project.mde.programming.Literal#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see com.project.mde.programming.ProgrammingPackage#getLiteral()
 * @model
 * @generated
 */
public interface Literal extends Expression {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.programming.ValueType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tipo con el que se interpreta el valor léxico.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.project.mde.programming.ValueType
	 * @see #setType(ValueType)
	 * @see com.project.mde.programming.ProgrammingPackage#getLiteral_Type()
	 * @model required="true"
	 * @generated
	 */
	ValueType getType();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.Literal#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.project.mde.programming.ValueType
	 * @see #getType()
	 * @generated
	 */
	void setType(ValueType value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Representación léxica del valor, por ejemplo 5 o true.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see com.project.mde.programming.ProgrammingPackage#getLiteral_Value()
	 * @model required="true"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.Literal#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // Literal
