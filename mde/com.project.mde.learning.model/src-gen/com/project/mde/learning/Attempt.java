/**
 */
package com.project.mde.learning;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attempt</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.Attempt#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#getStudent <em>Student</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#getActivity <em>Activity</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#isSuccessful <em>Successful</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#isFunctionalPassed <em>Functional Passed</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#getResolutionTime <em>Resolution Time</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#getHintCount <em>Hint Count</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#getSubmittedAt <em>Submitted At</em>}</li>
 *   <li>{@link com.project.mde.learning.Attempt#getErrorPatterns <em>Error Patterns</em>}</li>
 * </ul>
 *
 * @see com.project.mde.learning.LearningPackage#getAttempt()
 * @model
 * @generated
 */
public interface Attempt extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.project.mde.learning.LearningPackage#getAttempt_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#getId <em>Id</em>}' attribute.
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
	 * @see com.project.mde.learning.LearningPackage#getAttempt_Student()
	 * @model required="true"
	 * @generated
	 */
	Student getStudent();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#getStudent <em>Student</em>}' reference.
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
	 * @see com.project.mde.learning.LearningPackage#getAttempt_Activity()
	 * @model required="true"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#getActivity <em>Activity</em>}' reference.
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
	 * @see com.project.mde.learning.LearningPackage#getAttempt_Concept()
	 * @model required="true"
	 * @generated
	 */
	Concept getConcept();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#getConcept <em>Concept</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept</em>' reference.
	 * @see #getConcept()
	 * @generated
	 */
	void setConcept(Concept value);

	/**
	 * Returns the value of the '<em><b>Successful</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Successful</em>' attribute.
	 * @see #setSuccessful(boolean)
	 * @see com.project.mde.learning.LearningPackage#getAttempt_Successful()
	 * @model
	 * @generated
	 */
	boolean isSuccessful();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#isSuccessful <em>Successful</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Successful</em>' attribute.
	 * @see #isSuccessful()
	 * @generated
	 */
	void setSuccessful(boolean value);

	/**
	 * Returns the value of the '<em><b>Functional Passed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functional Passed</em>' attribute.
	 * @see #setFunctionalPassed(boolean)
	 * @see com.project.mde.learning.LearningPackage#getAttempt_FunctionalPassed()
	 * @model
	 * @generated
	 */
	boolean isFunctionalPassed();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#isFunctionalPassed <em>Functional Passed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Functional Passed</em>' attribute.
	 * @see #isFunctionalPassed()
	 * @generated
	 */
	void setFunctionalPassed(boolean value);

	/**
	 * Returns the value of the '<em><b>Resolution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolution Time</em>' attribute.
	 * @see #setResolutionTime(long)
	 * @see com.project.mde.learning.LearningPackage#getAttempt_ResolutionTime()
	 * @model
	 * @generated
	 */
	long getResolutionTime();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#getResolutionTime <em>Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolution Time</em>' attribute.
	 * @see #getResolutionTime()
	 * @generated
	 */
	void setResolutionTime(long value);

	/**
	 * Returns the value of the '<em><b>Hint Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hint Count</em>' attribute.
	 * @see #setHintCount(int)
	 * @see com.project.mde.learning.LearningPackage#getAttempt_HintCount()
	 * @model
	 * @generated
	 */
	int getHintCount();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#getHintCount <em>Hint Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hint Count</em>' attribute.
	 * @see #getHintCount()
	 * @generated
	 */
	void setHintCount(int value);

	/**
	 * Returns the value of the '<em><b>Submitted At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Submitted At</em>' attribute.
	 * @see #setSubmittedAt(Date)
	 * @see com.project.mde.learning.LearningPackage#getAttempt_SubmittedAt()
	 * @model required="true"
	 * @generated
	 */
	Date getSubmittedAt();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.Attempt#getSubmittedAt <em>Submitted At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Submitted At</em>' attribute.
	 * @see #getSubmittedAt()
	 * @generated
	 */
	void setSubmittedAt(Date value);

	/**
	 * Returns the value of the '<em><b>Error Patterns</b></em>' reference list.
	 * The list contents are of type {@link com.project.mde.learning.ErrorPattern}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Patterns</em>' reference list.
	 * @see com.project.mde.learning.LearningPackage#getAttempt_ErrorPatterns()
	 * @model
	 * @generated
	 */
	EList<ErrorPattern> getErrorPatterns();

} // Attempt
