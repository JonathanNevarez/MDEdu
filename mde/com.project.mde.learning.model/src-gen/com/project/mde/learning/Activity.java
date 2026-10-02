/**
 */
package com.project.mde.learning;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.Activity#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.Activity#getTitle <em>Title</em>}</li>
 *   <li>{@link com.project.mde.learning.Activity#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.Activity#getLearningObjectives <em>Learning Objectives</em>}</li>
 * </ul>
 *
 * @see com.project.mde.learning.LearningPackage#getActivity()
 * @model
 * @generated
 */
public interface Activity extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.project.mde.learning.LearningPackage#getActivity_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Activity#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see com.project.mde.learning.LearningPackage#getActivity_Title()
	 * @model required="true"
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Activity#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept</em>' reference.
	 * @see #setConcept(Concept)
	 * @see com.project.mde.learning.LearningPackage#getActivity_Concept()
	 * @model required="true"
	 * @generated
	 */
	Concept getConcept();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Activity#getConcept <em>Concept</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept</em>' reference.
	 * @see #getConcept()
	 * @generated
	 */
	void setConcept(Concept value);

	/**
	 * Returns the value of the '<em><b>Learning Objectives</b></em>' reference list.
	 * The list contents are of type {@link com.project.mde.learning.LearningObjective}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Learning Objectives</em>' reference list.
	 * @see com.project.mde.learning.LearningPackage#getActivity_LearningObjectives()
	 * @model
	 * @generated
	 */
	EList<LearningObjective> getLearningObjectives();

} // Activity
