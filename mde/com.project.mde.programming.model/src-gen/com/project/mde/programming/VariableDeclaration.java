/**
 */
package com.project.mde.programming;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Declaración de una variable con tipo y valor inicial opcional.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.programming.VariableDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link com.project.mde.programming.VariableDeclaration#getType <em>Type</em>}</li>
 *   <li>{@link com.project.mde.programming.VariableDeclaration#getInitialValue <em>Initial Value</em>}</li>
 * </ul>
 *
 * @see com.project.mde.programming.ProgrammingPackage#getVariableDeclaration()
 * @model
 * @generated
 */
public interface VariableDeclaration extends Statement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Nombre declarado; validez y unicidad en alcance se difieren.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.project.mde.programming.ProgrammingPackage#getVariableDeclaration_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.VariableDeclaration#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.programming.ValueType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tipo declarado de la variable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.project.mde.programming.ValueType
	 * @see #setType(ValueType)
	 * @see com.project.mde.programming.ProgrammingPackage#getVariableDeclaration_Type()
	 * @model required="true"
	 * @generated
	 */
	ValueType getType();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.VariableDeclaration#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.project.mde.programming.ValueType
	 * @see #getType()
	 * @generated
	 */
	void setType(ValueType value);

	/**
	 * Returns the value of the '<em><b>Initial Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Valor inicial opcional contenido por la declaración.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Initial Value</em>' containment reference.
	 * @see #setInitialValue(Expression)
	 * @see com.project.mde.programming.ProgrammingPackage#getVariableDeclaration_InitialValue()
	 * @model containment="true"
	 * @generated
	 */
	Expression getInitialValue();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.VariableDeclaration#getInitialValue <em>Initial Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Value</em>' containment reference.
	 * @see #getInitialValue()
	 * @generated
	 */
	void setInitialValue(Expression value);

} // VariableDeclaration
