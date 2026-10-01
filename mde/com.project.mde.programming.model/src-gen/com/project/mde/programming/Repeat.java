/**
 */
package com.project.mde.programming;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repeat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Repetición de un cuerpo; count debe ser entero válido, restricción semántica diferida.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.programming.Repeat#getCount <em>Count</em>}</li>
 *   <li>{@link com.project.mde.programming.Repeat#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see com.project.mde.programming.ProgrammingPackage#getRepeat()
 * @model
 * @generated
 */
public interface Repeat extends Statement {
	/**
	 * Returns the value of the '<em><b>Count</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Expresión de control; su tipo se comprobará semánticamente en una fase posterior.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Count</em>' containment reference.
	 * @see #setCount(Expression)
	 * @see com.project.mde.programming.ProgrammingPackage#getRepeat_Count()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getCount();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.Repeat#getCount <em>Count</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' containment reference.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(Expression value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.programming.Statement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Secuencia contenida de sentencias de esta rama o cuerpo.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference list.
	 * @see com.project.mde.programming.ProgrammingPackage#getRepeat_Body()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getBody();

} // Repeat
