/**
 */
package com.project.mde.programming;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Else</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Condicional que hereda condition y thenBranch y añade una rama alternativa.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.programming.IfElse#getElseBranch <em>Else Branch</em>}</li>
 * </ul>
 *
 * @see com.project.mde.programming.ProgrammingPackage#getIfElse()
 * @model
 * @generated
 */
public interface IfElse extends If {
	/**
	 * Returns the value of the '<em><b>Else Branch</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.programming.Statement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Secuencia contenida de sentencias de esta rama o cuerpo.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Else Branch</em>' containment reference list.
	 * @see com.project.mde.programming.ProgrammingPackage#getIfElse_ElseBranch()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getElseBranch();

} // IfElse
