/**
 */
package com.project.mde.programming;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>While</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Ciclo condicionado; se difieren tipo booleano y análisis de terminación.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.programming.While#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.project.mde.programming.While#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see com.project.mde.programming.ProgrammingPackage#getWhile()
 * @model
 * @generated
 */
public interface While extends Statement {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Expresión de control; su tipo se comprobará semánticamente en una fase posterior.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Expression)
	 * @see com.project.mde.programming.ProgrammingPackage#getWhile_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.While#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.programming.Statement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Secuencia contenida de sentencias de esta rama o cuerpo.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference list.
	 * @see com.project.mde.programming.ProgrammingPackage#getWhile_Body()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getBody();

} // While
