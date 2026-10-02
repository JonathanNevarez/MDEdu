/**
 */
package com.project.mde.learning;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.Concept#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.Concept#getName <em>Name</em>}</li>
 *   <li>{@link com.project.mde.learning.Concept#getPrerequisites <em>Prerequisites</em>}</li>
 *   <li>{@link com.project.mde.learning.Concept#getMinimumMasteryToUnlock <em>Minimum Mastery To Unlock</em>}</li>
 *   <li>{@link com.project.mde.learning.Concept#getActivities <em>Activities</em>}</li>
 *   <li>{@link com.project.mde.learning.Concept#getReinforcementActivities <em>Reinforcement Activities</em>}</li>
 * </ul>
 *
 * @see com.project.mde.learning.LearningPackage#getConcept()
 * @model
 * @generated
 */
public interface Concept extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.project.mde.learning.LearningPackage#getConcept_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Concept#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.project.mde.learning.LearningPackage#getConcept_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Concept#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Prerequisites</b></em>' reference list.
	 * The list contents are of type {@link com.project.mde.learning.Concept}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prerequisites</em>' reference list.
	 * @see com.project.mde.learning.LearningPackage#getConcept_Prerequisites()
	 * @model
	 * @generated
	 */
	EList<Concept> getPrerequisites();

	/**
	 * Returns the value of the '<em><b>Minimum Mastery To Unlock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minimum Mastery To Unlock</em>' attribute.
	 * @see #setMinimumMasteryToUnlock(double)
	 * @see com.project.mde.learning.LearningPackage#getConcept_MinimumMasteryToUnlock()
	 * @model
	 * @generated
	 */
	double getMinimumMasteryToUnlock();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Concept#getMinimumMasteryToUnlock <em>Minimum Mastery To Unlock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minimum Mastery To Unlock</em>' attribute.
	 * @see #getMinimumMasteryToUnlock()
	 * @generated
	 */
	void setMinimumMasteryToUnlock(double value);

	/**
	 * Returns the value of the '<em><b>Activities</b></em>' reference list.
	 * The list contents are of type {@link com.project.mde.learning.Activity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activities</em>' reference list.
	 * @see com.project.mde.learning.LearningPackage#getConcept_Activities()
	 * @model
	 * @generated
	 */
	EList<Activity> getActivities();

	/**
	 * Returns the value of the '<em><b>Reinforcement Activities</b></em>' reference list.
	 * The list contents are of type {@link com.project.mde.learning.Activity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reinforcement Activities</em>' reference list.
	 * @see com.project.mde.learning.LearningPackage#getConcept_ReinforcementActivities()
	 * @model
	 * @generated
	 */
	EList<Activity> getReinforcementActivities();

} // Concept
