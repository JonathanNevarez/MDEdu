/**
 */
package com.project.mde.context;

import java.math.BigDecimal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Student Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.context.StudentContext#getStudentId <em>Student Id</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getMasteryScore <em>Mastery Score</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getAttemptCount <em>Attempt Count</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getSuccessCount <em>Success Count</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getFailureCount <em>Failure Count</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getConsecutiveFailures <em>Consecutive Failures</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getHintCount <em>Hint Count</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getAverageResolutionTime <em>Average Resolution Time</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getCurrentResolutionTime <em>Current Resolution Time</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#isActivityPassed <em>Activity Passed</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#isFunctionalPassed <em>Functional Passed</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#isRequiredConceptUsed <em>Required Concept Used</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#getDetectedPatterns <em>Detected Patterns</em>}</li>
 *   <li>{@link com.project.mde.context.StudentContext#isRepeatedErrorPattern <em>Repeated Error Pattern</em>}</li>
 * </ul>
 *
 * @see com.project.mde.context.ContextPackage#getStudentContext()
 * @model
 * @generated
 */
public interface StudentContext extends EObject {
	/**
	 * Returns the value of the '<em><b>Student Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Student Id</em>' attribute.
	 * @see #setStudentId(String)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_StudentId()
	 * @model required="true"
	 * @generated
	 */
	String getStudentId();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getStudentId <em>Student Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Student Id</em>' attribute.
	 * @see #getStudentId()
	 * @generated
	 */
	void setStudentId(String value);

	/**
	 * Returns the value of the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept Id</em>' attribute.
	 * @see #setConceptId(String)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_ConceptId()
	 * @model required="true"
	 * @generated
	 */
	String getConceptId();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getConceptId <em>Concept Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept Id</em>' attribute.
	 * @see #getConceptId()
	 * @generated
	 */
	void setConceptId(String value);

	/**
	 * Returns the value of the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Id</em>' attribute.
	 * @see #setActivityId(String)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_ActivityId()
	 * @model required="true"
	 * @generated
	 */
	String getActivityId();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getActivityId <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Id</em>' attribute.
	 * @see #getActivityId()
	 * @generated
	 */
	void setActivityId(String value);

	/**
	 * Returns the value of the '<em><b>Mastery Score</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mastery Score</em>' attribute.
	 * @see #setMasteryScore(BigDecimal)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_MasteryScore()
	 * @model required="true"
	 * @generated
	 */
	BigDecimal getMasteryScore();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getMasteryScore <em>Mastery Score</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mastery Score</em>' attribute.
	 * @see #getMasteryScore()
	 * @generated
	 */
	void setMasteryScore(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Attempt Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attempt Count</em>' attribute.
	 * @see #setAttemptCount(int)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_AttemptCount()
	 * @model required="true"
	 * @generated
	 */
	int getAttemptCount();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getAttemptCount <em>Attempt Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attempt Count</em>' attribute.
	 * @see #getAttemptCount()
	 * @generated
	 */
	void setAttemptCount(int value);

	/**
	 * Returns the value of the '<em><b>Success Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Success Count</em>' attribute.
	 * @see #setSuccessCount(int)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_SuccessCount()
	 * @model required="true"
	 * @generated
	 */
	int getSuccessCount();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getSuccessCount <em>Success Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Success Count</em>' attribute.
	 * @see #getSuccessCount()
	 * @generated
	 */
	void setSuccessCount(int value);

	/**
	 * Returns the value of the '<em><b>Failure Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failure Count</em>' attribute.
	 * @see #setFailureCount(int)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_FailureCount()
	 * @model required="true"
	 * @generated
	 */
	int getFailureCount();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getFailureCount <em>Failure Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failure Count</em>' attribute.
	 * @see #getFailureCount()
	 * @generated
	 */
	void setFailureCount(int value);

	/**
	 * Returns the value of the '<em><b>Consecutive Failures</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Consecutive Failures</em>' attribute.
	 * @see #setConsecutiveFailures(int)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_ConsecutiveFailures()
	 * @model required="true"
	 * @generated
	 */
	int getConsecutiveFailures();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getConsecutiveFailures <em>Consecutive Failures</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Consecutive Failures</em>' attribute.
	 * @see #getConsecutiveFailures()
	 * @generated
	 */
	void setConsecutiveFailures(int value);

	/**
	 * Returns the value of the '<em><b>Hint Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hint Count</em>' attribute.
	 * @see #setHintCount(int)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_HintCount()
	 * @model required="true"
	 * @generated
	 */
	int getHintCount();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getHintCount <em>Hint Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hint Count</em>' attribute.
	 * @see #getHintCount()
	 * @generated
	 */
	void setHintCount(int value);

	/**
	 * Returns the value of the '<em><b>Average Resolution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Average Resolution Time</em>' attribute.
	 * @see #setAverageResolutionTime(BigDecimal)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_AverageResolutionTime()
	 * @model required="true"
	 * @generated
	 */
	BigDecimal getAverageResolutionTime();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getAverageResolutionTime <em>Average Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Average Resolution Time</em>' attribute.
	 * @see #getAverageResolutionTime()
	 * @generated
	 */
	void setAverageResolutionTime(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Current Resolution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Resolution Time</em>' attribute.
	 * @see #setCurrentResolutionTime(long)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_CurrentResolutionTime()
	 * @model required="true"
	 * @generated
	 */
	long getCurrentResolutionTime();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#getCurrentResolutionTime <em>Current Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Resolution Time</em>' attribute.
	 * @see #getCurrentResolutionTime()
	 * @generated
	 */
	void setCurrentResolutionTime(long value);

	/**
	 * Returns the value of the '<em><b>Activity Passed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Passed</em>' attribute.
	 * @see #setActivityPassed(boolean)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_ActivityPassed()
	 * @model required="true"
	 * @generated
	 */
	boolean isActivityPassed();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#isActivityPassed <em>Activity Passed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Passed</em>' attribute.
	 * @see #isActivityPassed()
	 * @generated
	 */
	void setActivityPassed(boolean value);

	/**
	 * Returns the value of the '<em><b>Functional Passed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functional Passed</em>' attribute.
	 * @see #setFunctionalPassed(boolean)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_FunctionalPassed()
	 * @model required="true"
	 * @generated
	 */
	boolean isFunctionalPassed();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#isFunctionalPassed <em>Functional Passed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Functional Passed</em>' attribute.
	 * @see #isFunctionalPassed()
	 * @generated
	 */
	void setFunctionalPassed(boolean value);

	/**
	 * Returns the value of the '<em><b>Required Concept Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Concept Used</em>' attribute.
	 * @see #setRequiredConceptUsed(boolean)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_RequiredConceptUsed()
	 * @model required="true"
	 * @generated
	 */
	boolean isRequiredConceptUsed();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#isRequiredConceptUsed <em>Required Concept Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required Concept Used</em>' attribute.
	 * @see #isRequiredConceptUsed()
	 * @generated
	 */
	void setRequiredConceptUsed(boolean value);

	/**
	 * Returns the value of the '<em><b>Detected Patterns</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Detected Patterns</em>' attribute list.
	 * @see com.project.mde.context.ContextPackage#getStudentContext_DetectedPatterns()
	 * @model
	 * @generated
	 */
	EList<String> getDetectedPatterns();

	/**
	 * Returns the value of the '<em><b>Repeated Error Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repeated Error Pattern</em>' attribute.
	 * @see #setRepeatedErrorPattern(boolean)
	 * @see com.project.mde.context.ContextPackage#getStudentContext_RepeatedErrorPattern()
	 * @model required="true"
	 * @generated
	 */
	boolean isRepeatedErrorPattern();

	/**
	 * Sets the value of the '{@link com.project.mde.context.StudentContext#isRepeatedErrorPattern <em>Repeated Error Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repeated Error Pattern</em>' attribute.
	 * @see #isRepeatedErrorPattern()
	 * @generated
	 */
	void setRepeatedErrorPattern(boolean value);

} // StudentContext
