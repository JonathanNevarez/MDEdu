/**
 */
package com.project.mde.learning;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Error Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.ErrorPattern#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.ErrorPattern#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.ErrorPattern#getSeverity <em>Severity</em>}</li>
 *   <li>{@link com.project.mde.learning.ErrorPattern#getPedagogicalMeaning <em>Pedagogical Meaning</em>}</li>
 * </ul>
 *
 * @see com.project.mde.learning.LearningPackage#getErrorPattern()
 * @model
 * @generated
 */
public interface ErrorPattern extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.project.mde.learning.LearningPackage#getErrorPattern_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ErrorPattern#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept</em>' reference.
	 * @see #setConcept(Concept)
	 * @see com.project.mde.learning.LearningPackage#getErrorPattern_Concept()
	 * @model required="true"
	 * @generated
	 */
	Concept getConcept();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ErrorPattern#getConcept <em>Concept</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept</em>' reference.
	 * @see #getConcept()
	 * @generated
	 */
	void setConcept(Concept value);

	/**
	 * Returns the value of the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Severity</em>' attribute.
	 * @see #setSeverity(String)
	 * @see com.project.mde.learning.LearningPackage#getErrorPattern_Severity()
	 * @model required="true"
	 * @generated
	 */
	String getSeverity();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ErrorPattern#getSeverity <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Severity</em>' attribute.
	 * @see #getSeverity()
	 * @generated
	 */
	void setSeverity(String value);

	/**
	 * Returns the value of the '<em><b>Pedagogical Meaning</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pedagogical Meaning</em>' attribute.
	 * @see #setPedagogicalMeaning(String)
	 * @see com.project.mde.learning.LearningPackage#getErrorPattern_PedagogicalMeaning()
	 * @model required="true"
	 * @generated
	 */
	String getPedagogicalMeaning();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ErrorPattern#getPedagogicalMeaning <em>Pedagogical Meaning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pedagogical Meaning</em>' attribute.
	 * @see #getPedagogicalMeaning()
	 * @generated
	 */
	void setPedagogicalMeaning(String value);

} // ErrorPattern
