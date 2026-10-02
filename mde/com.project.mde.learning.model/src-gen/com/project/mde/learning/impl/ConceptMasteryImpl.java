/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.Concept;
import com.project.mde.learning.ConceptMastery;
import com.project.mde.learning.ErrorPattern;
import com.project.mde.learning.LearningPackage;
import com.project.mde.learning.Student;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concept Mastery</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getStudent <em>Student</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getMasteryScore <em>Mastery Score</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getAttemptCount <em>Attempt Count</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getSuccessCount <em>Success Count</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getFailureCount <em>Failure Count</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getConsecutiveFailures <em>Consecutive Failures</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getHintCount <em>Hint Count</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getAverageResolutionTime <em>Average Resolution Time</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getLastUpdated <em>Last Updated</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptMasteryImpl#getRecentErrorPatterns <em>Recent Error Patterns</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConceptMasteryImpl extends MinimalEObjectImpl.Container implements ConceptMastery {
	/**
	 * The cached value of the '{@link #getStudent() <em>Student</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudent()
	 * @generated
	 * @ordered
	 */
	protected Student student;

	/**
	 * The cached value of the '{@link #getConcept() <em>Concept</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcept()
	 * @generated
	 * @ordered
	 */
	protected Concept concept;

	/**
	 * The default value of the '{@link #getMasteryScore() <em>Mastery Score</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMasteryScore()
	 * @generated
	 * @ordered
	 */
	protected static final double MASTERY_SCORE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMasteryScore() <em>Mastery Score</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMasteryScore()
	 * @generated
	 * @ordered
	 */
	protected double masteryScore = MASTERY_SCORE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttemptCount() <em>Attempt Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttemptCount()
	 * @generated
	 * @ordered
	 */
	protected static final int ATTEMPT_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAttemptCount() <em>Attempt Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttemptCount()
	 * @generated
	 * @ordered
	 */
	protected int attemptCount = ATTEMPT_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSuccessCount() <em>Success Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuccessCount()
	 * @generated
	 * @ordered
	 */
	protected static final int SUCCESS_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSuccessCount() <em>Success Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuccessCount()
	 * @generated
	 * @ordered
	 */
	protected int successCount = SUCCESS_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFailureCount() <em>Failure Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailureCount()
	 * @generated
	 * @ordered
	 */
	protected static final int FAILURE_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFailureCount() <em>Failure Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailureCount()
	 * @generated
	 * @ordered
	 */
	protected int failureCount = FAILURE_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getConsecutiveFailures() <em>Consecutive Failures</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConsecutiveFailures()
	 * @generated
	 * @ordered
	 */
	protected static final int CONSECUTIVE_FAILURES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getConsecutiveFailures() <em>Consecutive Failures</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConsecutiveFailures()
	 * @generated
	 * @ordered
	 */
	protected int consecutiveFailures = CONSECUTIVE_FAILURES_EDEFAULT;

	/**
	 * The default value of the '{@link #getHintCount() <em>Hint Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintCount()
	 * @generated
	 * @ordered
	 */
	protected static final int HINT_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHintCount() <em>Hint Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintCount()
	 * @generated
	 * @ordered
	 */
	protected int hintCount = HINT_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getAverageResolutionTime() <em>Average Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAverageResolutionTime()
	 * @generated
	 * @ordered
	 */
	protected static final double AVERAGE_RESOLUTION_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAverageResolutionTime() <em>Average Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAverageResolutionTime()
	 * @generated
	 * @ordered
	 */
	protected double averageResolutionTime = AVERAGE_RESOLUTION_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastUpdated()
	 * @generated
	 * @ordered
	 */
	protected static final Date LAST_UPDATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastUpdated()
	 * @generated
	 * @ordered
	 */
	protected Date lastUpdated = LAST_UPDATED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRecentErrorPatterns() <em>Recent Error Patterns</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecentErrorPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList<ErrorPattern> recentErrorPatterns;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConceptMasteryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LearningPackage.Literals.CONCEPT_MASTERY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Student getStudent() {
		if (student != null && student.eIsProxy()) {
			InternalEObject oldStudent = (InternalEObject)student;
			student = (Student)eResolveProxy(oldStudent);
			if (student != oldStudent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.CONCEPT_MASTERY__STUDENT, oldStudent, student));
			}
		}
		return student;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Student basicGetStudent() {
		return student;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStudent(Student newStudent) {
		Student oldStudent = student;
		student = newStudent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__STUDENT, oldStudent, student));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Concept getConcept() {
		if (concept != null && concept.eIsProxy()) {
			InternalEObject oldConcept = (InternalEObject)concept;
			concept = (Concept)eResolveProxy(oldConcept);
			if (concept != oldConcept) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.CONCEPT_MASTERY__CONCEPT, oldConcept, concept));
			}
		}
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concept basicGetConcept() {
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConcept(Concept newConcept) {
		Concept oldConcept = concept;
		concept = newConcept;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__CONCEPT, oldConcept, concept));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMasteryScore() {
		return masteryScore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMasteryScore(double newMasteryScore) {
		double oldMasteryScore = masteryScore;
		masteryScore = newMasteryScore;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__MASTERY_SCORE, oldMasteryScore, masteryScore));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getAttemptCount() {
		return attemptCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAttemptCount(int newAttemptCount) {
		int oldAttemptCount = attemptCount;
		attemptCount = newAttemptCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__ATTEMPT_COUNT, oldAttemptCount, attemptCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getSuccessCount() {
		return successCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuccessCount(int newSuccessCount) {
		int oldSuccessCount = successCount;
		successCount = newSuccessCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__SUCCESS_COUNT, oldSuccessCount, successCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getFailureCount() {
		return failureCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFailureCount(int newFailureCount) {
		int oldFailureCount = failureCount;
		failureCount = newFailureCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__FAILURE_COUNT, oldFailureCount, failureCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getConsecutiveFailures() {
		return consecutiveFailures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConsecutiveFailures(int newConsecutiveFailures) {
		int oldConsecutiveFailures = consecutiveFailures;
		consecutiveFailures = newConsecutiveFailures;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__CONSECUTIVE_FAILURES, oldConsecutiveFailures, consecutiveFailures));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getHintCount() {
		return hintCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHintCount(int newHintCount) {
		int oldHintCount = hintCount;
		hintCount = newHintCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__HINT_COUNT, oldHintCount, hintCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getAverageResolutionTime() {
		return averageResolutionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAverageResolutionTime(double newAverageResolutionTime) {
		double oldAverageResolutionTime = averageResolutionTime;
		averageResolutionTime = newAverageResolutionTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__AVERAGE_RESOLUTION_TIME, oldAverageResolutionTime, averageResolutionTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLastUpdated(Date newLastUpdated) {
		Date oldLastUpdated = lastUpdated;
		lastUpdated = newLastUpdated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT_MASTERY__LAST_UPDATED, oldLastUpdated, lastUpdated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ErrorPattern> getRecentErrorPatterns() {
		if (recentErrorPatterns == null) {
			recentErrorPatterns = new EObjectResolvingEList<ErrorPattern>(ErrorPattern.class, this, LearningPackage.CONCEPT_MASTERY__RECENT_ERROR_PATTERNS);
		}
		return recentErrorPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LearningPackage.CONCEPT_MASTERY__STUDENT:
				if (resolve) return getStudent();
				return basicGetStudent();
			case LearningPackage.CONCEPT_MASTERY__CONCEPT:
				if (resolve) return getConcept();
				return basicGetConcept();
			case LearningPackage.CONCEPT_MASTERY__MASTERY_SCORE:
				return getMasteryScore();
			case LearningPackage.CONCEPT_MASTERY__ATTEMPT_COUNT:
				return getAttemptCount();
			case LearningPackage.CONCEPT_MASTERY__SUCCESS_COUNT:
				return getSuccessCount();
			case LearningPackage.CONCEPT_MASTERY__FAILURE_COUNT:
				return getFailureCount();
			case LearningPackage.CONCEPT_MASTERY__CONSECUTIVE_FAILURES:
				return getConsecutiveFailures();
			case LearningPackage.CONCEPT_MASTERY__HINT_COUNT:
				return getHintCount();
			case LearningPackage.CONCEPT_MASTERY__AVERAGE_RESOLUTION_TIME:
				return getAverageResolutionTime();
			case LearningPackage.CONCEPT_MASTERY__LAST_UPDATED:
				return getLastUpdated();
			case LearningPackage.CONCEPT_MASTERY__RECENT_ERROR_PATTERNS:
				return getRecentErrorPatterns();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LearningPackage.CONCEPT_MASTERY__STUDENT:
				setStudent((Student)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__CONCEPT:
				setConcept((Concept)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__MASTERY_SCORE:
				setMasteryScore((Double)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__ATTEMPT_COUNT:
				setAttemptCount((Integer)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__SUCCESS_COUNT:
				setSuccessCount((Integer)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__FAILURE_COUNT:
				setFailureCount((Integer)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__CONSECUTIVE_FAILURES:
				setConsecutiveFailures((Integer)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__HINT_COUNT:
				setHintCount((Integer)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__AVERAGE_RESOLUTION_TIME:
				setAverageResolutionTime((Double)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__LAST_UPDATED:
				setLastUpdated((Date)newValue);
				return;
			case LearningPackage.CONCEPT_MASTERY__RECENT_ERROR_PATTERNS:
				getRecentErrorPatterns().clear();
				getRecentErrorPatterns().addAll((Collection<? extends ErrorPattern>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LearningPackage.CONCEPT_MASTERY__STUDENT:
				setStudent((Student)null);
				return;
			case LearningPackage.CONCEPT_MASTERY__CONCEPT:
				setConcept((Concept)null);
				return;
			case LearningPackage.CONCEPT_MASTERY__MASTERY_SCORE:
				setMasteryScore(MASTERY_SCORE_EDEFAULT);
				return;
			case LearningPackage.CONCEPT_MASTERY__ATTEMPT_COUNT:
				setAttemptCount(ATTEMPT_COUNT_EDEFAULT);
				return;
			case LearningPackage.CONCEPT_MASTERY__SUCCESS_COUNT:
				setSuccessCount(SUCCESS_COUNT_EDEFAULT);
				return;
			case LearningPackage.CONCEPT_MASTERY__FAILURE_COUNT:
				setFailureCount(FAILURE_COUNT_EDEFAULT);
				return;
			case LearningPackage.CONCEPT_MASTERY__CONSECUTIVE_FAILURES:
				setConsecutiveFailures(CONSECUTIVE_FAILURES_EDEFAULT);
				return;
			case LearningPackage.CONCEPT_MASTERY__HINT_COUNT:
				setHintCount(HINT_COUNT_EDEFAULT);
				return;
			case LearningPackage.CONCEPT_MASTERY__AVERAGE_RESOLUTION_TIME:
				setAverageResolutionTime(AVERAGE_RESOLUTION_TIME_EDEFAULT);
				return;
			case LearningPackage.CONCEPT_MASTERY__LAST_UPDATED:
				setLastUpdated(LAST_UPDATED_EDEFAULT);
				return;
			case LearningPackage.CONCEPT_MASTERY__RECENT_ERROR_PATTERNS:
				getRecentErrorPatterns().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LearningPackage.CONCEPT_MASTERY__STUDENT:
				return student != null;
			case LearningPackage.CONCEPT_MASTERY__CONCEPT:
				return concept != null;
			case LearningPackage.CONCEPT_MASTERY__MASTERY_SCORE:
				return masteryScore != MASTERY_SCORE_EDEFAULT;
			case LearningPackage.CONCEPT_MASTERY__ATTEMPT_COUNT:
				return attemptCount != ATTEMPT_COUNT_EDEFAULT;
			case LearningPackage.CONCEPT_MASTERY__SUCCESS_COUNT:
				return successCount != SUCCESS_COUNT_EDEFAULT;
			case LearningPackage.CONCEPT_MASTERY__FAILURE_COUNT:
				return failureCount != FAILURE_COUNT_EDEFAULT;
			case LearningPackage.CONCEPT_MASTERY__CONSECUTIVE_FAILURES:
				return consecutiveFailures != CONSECUTIVE_FAILURES_EDEFAULT;
			case LearningPackage.CONCEPT_MASTERY__HINT_COUNT:
				return hintCount != HINT_COUNT_EDEFAULT;
			case LearningPackage.CONCEPT_MASTERY__AVERAGE_RESOLUTION_TIME:
				return averageResolutionTime != AVERAGE_RESOLUTION_TIME_EDEFAULT;
			case LearningPackage.CONCEPT_MASTERY__LAST_UPDATED:
				return LAST_UPDATED_EDEFAULT == null ? lastUpdated != null : !LAST_UPDATED_EDEFAULT.equals(lastUpdated);
			case LearningPackage.CONCEPT_MASTERY__RECENT_ERROR_PATTERNS:
				return recentErrorPatterns != null && !recentErrorPatterns.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (masteryScore: ");
		result.append(masteryScore);
		result.append(", attemptCount: ");
		result.append(attemptCount);
		result.append(", successCount: ");
		result.append(successCount);
		result.append(", failureCount: ");
		result.append(failureCount);
		result.append(", consecutiveFailures: ");
		result.append(consecutiveFailures);
		result.append(", hintCount: ");
		result.append(hintCount);
		result.append(", averageResolutionTime: ");
		result.append(averageResolutionTime);
		result.append(", lastUpdated: ");
		result.append(lastUpdated);
		result.append(')');
		return result.toString();
	}

} //ConceptMasteryImpl
