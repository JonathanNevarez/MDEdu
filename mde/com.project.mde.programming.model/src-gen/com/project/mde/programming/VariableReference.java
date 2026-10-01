/**
 */
package com.project.mde.programming;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Referencia a una declaración de variable existente.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.programming.VariableReference#getDeclaration <em>Declaration</em>}</li>
 * </ul>
 *
 * @see com.project.mde.programming.ProgrammingPackage#getVariableReference()
 * @model
 * @generated
 */
public interface VariableReference extends Expression {
	/**
	 * Returns the value of the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Declaración referenciada; no containment. Visibilidad y alcance diferidos.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Declaration</em>' reference.
	 * @see #setDeclaration(VariableDeclaration)
	 * @see com.project.mde.programming.ProgrammingPackage#getVariableReference_Declaration()
	 * @model required="true"
	 * @generated
	 */
	VariableDeclaration getDeclaration();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.VariableReference#getDeclaration <em>Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declaration</em>' reference.
	 * @see #getDeclaration()
	 * @generated
	 */
	void setDeclaration(VariableDeclaration value);

} // VariableReference
