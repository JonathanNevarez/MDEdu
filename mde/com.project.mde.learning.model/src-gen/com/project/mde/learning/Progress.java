/**
 */
package com.project.mde.learning;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Progress</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.Progress#getActivity <em>Activity</em>}</li>
 *   <li>{@link com.project.mde.learning.Progress#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.Progress#isCompleted <em>Completed</em>}</li>
 *   <li>{@link com.project.mde.learning.Progress#isUnlocked <em>Unlocked</em>}</li>
 *   <li>{@link com.project.mde.learning.Progress#getCompletedAt <em>Completed At</em>}</li>
 * </ul>
 *
 * @see com.project.mde.learning.LearningPackage#getProgress()
 * @model
 * @generated
 */
public interface Progress extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' reference.
	 * @see #setActivity(Activity)
	 * @see com.project.mde.learning.LearningPackage#getProgress_Activity()
	 * @model required="true"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Progress#getActivity <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

	/**
	 * Returns the value of the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept</em>' reference.
	 * @see #setConcept(Concept)
	 * @see com.project.mde.learning.LearningPackage#getProgress_Concept()
	 * @model required="true"
	 * @generated
	 */
	Concept getConcept();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Progress#getConcept <em>Concept</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept</em>' reference.
	 * @see #getConcept()
	 * @generated
	 */
	void setConcept(Concept value);

	/**
	 * Returns the value of the '<em><b>Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completed</em>' attribute.
	 * @see #setCompleted(boolean)
	 * @see com.project.mde.learning.LearningPackage#getProgress_Completed()
	 * @model
	 * @generated
	 */
	boolean isCompleted();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Progress#isCompleted <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completed</em>' attribute.
	 * @see #isCompleted()
	 * @generated
	 */
	void setCompleted(boolean value);

	/**
	 * Returns the value of the '<em><b>Unlocked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unlocked</em>' attribute.
	 * @see #setUnlocked(boolean)
	 * @see com.project.mde.learning.LearningPackage#getProgress_Unlocked()
	 * @model
	 * @generated
	 */
	boolean isUnlocked();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Progress#isUnlocked <em>Unlocked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unlocked</em>' attribute.
	 * @see #isUnlocked()
	 * @generated
	 */
	void setUnlocked(boolean value);

	/**
	 * Returns the value of the '<em><b>Completed At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completed At</em>' attribute.
	 * @see #setCompletedAt(Date)
	 * @see com.project.mde.learning.LearningPackage#getProgress_CompletedAt()
	 * @model
	 * @generated
	 */
	Date getCompletedAt();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Progress#getCompletedAt <em>Completed At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completed At</em>' attribute.
	 * @see #getCompletedAt()
	 * @generated
	 */
	void setCompletedAt(Date value);

} // Progress
