/**
 */
package com.project.mde.learning;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hint Usage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.HintUsage#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.HintUsage#getStudent <em>Student</em>}</li>
 *   <li>{@link com.project.mde.learning.HintUsage#getActivity <em>Activity</em>}</li>
 *   <li>{@link com.project.mde.learning.HintUsage#getHintLevel <em>Hint Level</em>}</li>
 *   <li>{@link com.project.mde.learning.HintUsage#getUsedAt <em>Used At</em>}</li>
 * </ul>
 *
 * @see com.project.mde.learning.LearningPackage#getHintUsage()
 * @model
 * @generated
 */
public interface HintUsage extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.project.mde.learning.LearningPackage#getHintUsage_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.HintUsage#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Student</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Student</em>' reference.
	 * @see #setStudent(Student)
	 * @see com.project.mde.learning.LearningPackage#getHintUsage_Student()
	 * @model required="true"
	 * @generated
	 */
	Student getStudent();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.HintUsage#getStudent <em>Student</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Student</em>' reference.
	 * @see #getStudent()
	 * @generated
	 */
	void setStudent(Student value);

	/**
	 * Returns the value of the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' reference.
	 * @see #setActivity(Activity)
	 * @see com.project.mde.learning.LearningPackage#getHintUsage_Activity()
	 * @model required="true"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.HintUsage#getActivity <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

	/**
	 * Returns the value of the '<em><b>Hint Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hint Level</em>' attribute.
	 * @see #setHintLevel(int)
	 * @see com.project.mde.learning.LearningPackage#getHintUsage_HintLevel()
	 * @model
	 * @generated
	 */
	int getHintLevel();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.HintUsage#getHintLevel <em>Hint Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hint Level</em>' attribute.
	 * @see #getHintLevel()
	 * @generated
	 */
	void setHintLevel(int value);

	/**
	 * Returns the value of the '<em><b>Used At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used At</em>' attribute.
	 * @see #setUsedAt(Date)
	 * @see com.project.mde.learning.LearningPackage#getHintUsage_UsedAt()
	 * @model required="true"
	 * @generated
	 */
	Date getUsedAt();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.HintUsage#getUsedAt <em>Used At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Used At</em>' attribute.
	 * @see #getUsedAt()
	 * @generated
	 */
	void setUsedAt(Date value);

} // HintUsage
