/**
 */
package com.project.mde.learning;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concept Mastery</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getStudent <em>Student</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getMasteryScore <em>Mastery Score</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getAttemptCount <em>Attempt Count</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getSuccessCount <em>Success Count</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getFailureCount <em>Failure Count</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getConsecutiveFailures <em>Consecutive Failures</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getHintCount <em>Hint Count</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getAverageResolutionTime <em>Average Resolution Time</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getLastUpdated <em>Last Updated</em>}</li>
 *   <li>{@link com.project.mde.learning.ConceptMastery#getRecentErrorPatterns <em>Recent Error Patterns</em>}</li>
 * </ul>
 *
 * @see com.project.mde.learning.LearningPackage#getConceptMastery()
 * @model
 * @generated
 */
public interface ConceptMastery extends EObject {
	/**
	 * Returns the value of the '<em><b>Student</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Student</em>' reference.
	 * @see #setStudent(Student)
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_Student()
	 * @model required="true"
	 * @generated
	 */
	Student getStudent();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getStudent <em>Student</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Student</em>' reference.
	 * @see #getStudent()
	 * @generated
	 */
	void setStudent(Student value);

	/**
	 * Returns the value of the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept</em>' reference.
	 * @see #setConcept(Concept)
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_Concept()
	 * @model required="true"
	 * @generated
	 */
	Concept getConcept();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getConcept <em>Concept</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept</em>' reference.
	 * @see #getConcept()
	 * @generated
	 */
	void setConcept(Concept value);

	/**
	 * Returns the value of the '<em><b>Mastery Score</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mastery Score</em>' attribute.
	 * @see #setMasteryScore(double)
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_MasteryScore()
	 * @model
	 * @generated
	 */
	double getMasteryScore();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getMasteryScore <em>Mastery Score</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mastery Score</em>' attribute.
	 * @see #getMasteryScore()
	 * @generated
	 */
	void setMasteryScore(double value);

	/**
	 * Returns the value of the '<em><b>Attempt Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attempt Count</em>' attribute.
	 * @see #setAttemptCount(int)
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_AttemptCount()
	 * @model
	 * @generated
	 */
	int getAttemptCount();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getAttemptCount <em>Attempt Count</em>}' attribute.
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
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_SuccessCount()
	 * @model
	 * @generated
	 */
	int getSuccessCount();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getSuccessCount <em>Success Count</em>}' attribute.
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
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_FailureCount()
	 * @model
	 * @generated
	 */
	int getFailureCount();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getFailureCount <em>Failure Count</em>}' attribute.
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
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_ConsecutiveFailures()
	 * @model
	 * @generated
	 */
	int getConsecutiveFailures();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getConsecutiveFailures <em>Consecutive Failures</em>}' attribute.
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
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_HintCount()
	 * @model
	 * @generated
	 */
	int getHintCount();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getHintCount <em>Hint Count</em>}' attribute.
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
	 * @see #setAverageResolutionTime(double)
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_AverageResolutionTime()
	 * @model
	 * @generated
	 */
	double getAverageResolutionTime();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getAverageResolutionTime <em>Average Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Average Resolution Time</em>' attribute.
	 * @see #getAverageResolutionTime()
	 * @generated
	 */
	void setAverageResolutionTime(double value);

	/**
	 * Returns the value of the '<em><b>Last Updated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Updated</em>' attribute.
	 * @see #setLastUpdated(Date)
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_LastUpdated()
	 * @model required="true"
	 * @generated
	 */
	Date getLastUpdated();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.ConceptMastery#getLastUpdated <em>Last Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Updated</em>' attribute.
	 * @see #getLastUpdated()
	 * @generated
	 */
	void setLastUpdated(Date value);

	/**
	 * Returns the value of the '<em><b>Recent Error Patterns</b></em>' reference list.
	 * The list contents are of type {@link com.project.mde.learning.ErrorPattern}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recent Error Patterns</em>' reference list.
	 * @see com.project.mde.learning.LearningPackage#getConceptMastery_RecentErrorPatterns()
	 * @model
	 * @generated
	 */
	EList<ErrorPattern> getRecentErrorPatterns();

} // ConceptMastery
