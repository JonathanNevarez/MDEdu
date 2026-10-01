/**
 */
package com.project.mde.programming;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Condicional concreto con una rama de ejecución.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.programming.If#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.project.mde.programming.If#getThenBranch <em>Then Branch</em>}</li>
 * </ul>
 *
 * @see com.project.mde.programming.ProgrammingPackage#getIf()
 * @model
 * @generated
 */
public interface If extends Statement {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Expresión de control; su tipo se comprobará semánticamente en una fase posterior.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Expression)
	 * @see com.project.mde.programming.ProgrammingPackage#getIf_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.If#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Then Branch</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.programming.Statement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Secuencia contenida de sentencias de esta rama o cuerpo.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Then Branch</em>' containment reference list.
	 * @see com.project.mde.programming.ProgrammingPackage#getIf_ThenBranch()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getThenBranch();

} // If
