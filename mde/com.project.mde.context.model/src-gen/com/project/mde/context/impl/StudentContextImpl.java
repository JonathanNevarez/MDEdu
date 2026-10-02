/**
 */
package com.project.mde.context.impl;

import com.project.mde.context.ContextPackage;
import com.project.mde.context.StudentContext;

import java.math.BigDecimal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Student Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getStudentId <em>Student Id</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getMasteryScore <em>Mastery Score</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getAttemptCount <em>Attempt Count</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getSuccessCount <em>Success Count</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getFailureCount <em>Failure Count</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getConsecutiveFailures <em>Consecutive Failures</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getHintCount <em>Hint Count</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getAverageResolutionTime <em>Average Resolution Time</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getCurrentResolutionTime <em>Current Resolution Time</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#isActivityPassed <em>Activity Passed</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#isFunctionalPassed <em>Functional Passed</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#isRequiredConceptUsed <em>Required Concept Used</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#getDetectedPatterns <em>Detected Patterns</em>}</li>
 *   <li>{@link com.project.mde.context.impl.StudentContextImpl#isRepeatedErrorPattern <em>Repeated Error Pattern</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StudentContextImpl extends MinimalEObjectImpl.Container implements StudentContext {
	/**
	 * The default value of the '{@link #getStudentId() <em>Student Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudentId()
	 * @generated
	 * @ordered
	 */
	protected static final String STUDENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStudentId() <em>Student Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudentId()
	 * @generated
	 * @ordered
	 */
	protected String studentId = STUDENT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getConceptId() <em>Concept Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConceptId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONCEPT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConceptId() <em>Concept Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConceptId()
	 * @generated
	 * @ordered
	 */
	protected String conceptId = CONCEPT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getActivityId() <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityId()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVITY_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivityId() <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityId()
	 * @generated
	 * @ordered
	 */
	protected String activityId = ACTIVITY_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getMasteryScore() <em>Mastery Score</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMasteryScore()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal MASTERY_SCORE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMasteryScore() <em>Mastery Score</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMasteryScore()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal masteryScore = MASTERY_SCORE_EDEFAULT;

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
	protected static final BigDecimal AVERAGE_RESOLUTION_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAverageResolutionTime() <em>Average Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAverageResolutionTime()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal averageResolutionTime = AVERAGE_RESOLUTION_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurrentResolutionTime() <em>Current Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentResolutionTime()
	 * @generated
	 * @ordered
	 */
	protected static final long CURRENT_RESOLUTION_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCurrentResolutionTime() <em>Current Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentResolutionTime()
	 * @generated
	 * @ordered
	 */
	protected long currentResolutionTime = CURRENT_RESOLUTION_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #isActivityPassed() <em>Activity Passed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivityPassed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVITY_PASSED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActivityPassed() <em>Activity Passed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivityPassed()
	 * @generated
	 * @ordered
	 */
	protected boolean activityPassed = ACTIVITY_PASSED_EDEFAULT;

	/**
	 * The default value of the '{@link #isFunctionalPassed() <em>Functional Passed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFunctionalPassed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FUNCTIONAL_PASSED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFunctionalPassed() <em>Functional Passed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFunctionalPassed()
	 * @generated
	 * @ordered
	 */
	protected boolean functionalPassed = FUNCTIONAL_PASSED_EDEFAULT;

	/**
	 * The default value of the '{@link #isRequiredConceptUsed() <em>Required Concept Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiredConceptUsed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_CONCEPT_USED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequiredConceptUsed() <em>Required Concept Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiredConceptUsed()
	 * @generated
	 * @ordered
	 */
	protected boolean requiredConceptUsed = REQUIRED_CONCEPT_USED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDetectedPatterns() <em>Detected Patterns</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetectedPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList<String> detectedPatterns;

	/**
	 * The default value of the '{@link #isRepeatedErrorPattern() <em>Repeated Error Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRepeatedErrorPattern()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REPEATED_ERROR_PATTERN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRepeatedErrorPattern() <em>Repeated Error Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRepeatedErrorPattern()
	 * @generated
	 * @ordered
	 */
	protected boolean repeatedErrorPattern = REPEATED_ERROR_PATTERN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StudentContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ContextPackage.Literals.STUDENT_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStudentId() {
		return studentId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStudentId(String newStudentId) {
		String oldStudentId = studentId;
		studentId = newStudentId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__STUDENT_ID, oldStudentId, studentId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConceptId() {
		return conceptId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConceptId(String newConceptId) {
		String oldConceptId = conceptId;
		conceptId = newConceptId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__CONCEPT_ID, oldConceptId, conceptId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getActivityId() {
		return activityId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivityId(String newActivityId) {
		String oldActivityId = activityId;
		activityId = newActivityId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__ACTIVITY_ID, oldActivityId, activityId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BigDecimal getMasteryScore() {
		return masteryScore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMasteryScore(BigDecimal newMasteryScore) {
		BigDecimal oldMasteryScore = masteryScore;
		masteryScore = newMasteryScore;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__MASTERY_SCORE, oldMasteryScore, masteryScore));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__ATTEMPT_COUNT, oldAttemptCount, attemptCount));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__SUCCESS_COUNT, oldSuccessCount, successCount));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__FAILURE_COUNT, oldFailureCount, failureCount));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__CONSECUTIVE_FAILURES, oldConsecutiveFailures, consecutiveFailures));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__HINT_COUNT, oldHintCount, hintCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BigDecimal getAverageResolutionTime() {
		return averageResolutionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAverageResolutionTime(BigDecimal newAverageResolutionTime) {
		BigDecimal oldAverageResolutionTime = averageResolutionTime;
		averageResolutionTime = newAverageResolutionTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__AVERAGE_RESOLUTION_TIME, oldAverageResolutionTime, averageResolutionTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getCurrentResolutionTime() {
		return currentResolutionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCurrentResolutionTime(long newCurrentResolutionTime) {
		long oldCurrentResolutionTime = currentResolutionTime;
		currentResolutionTime = newCurrentResolutionTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__CURRENT_RESOLUTION_TIME, oldCurrentResolutionTime, currentResolutionTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isActivityPassed() {
		return activityPassed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivityPassed(boolean newActivityPassed) {
		boolean oldActivityPassed = activityPassed;
		activityPassed = newActivityPassed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__ACTIVITY_PASSED, oldActivityPassed, activityPassed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFunctionalPassed() {
		return functionalPassed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFunctionalPassed(boolean newFunctionalPassed) {
		boolean oldFunctionalPassed = functionalPassed;
		functionalPassed = newFunctionalPassed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__FUNCTIONAL_PASSED, oldFunctionalPassed, functionalPassed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRequiredConceptUsed() {
		return requiredConceptUsed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequiredConceptUsed(boolean newRequiredConceptUsed) {
		boolean oldRequiredConceptUsed = requiredConceptUsed;
		requiredConceptUsed = newRequiredConceptUsed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__REQUIRED_CONCEPT_USED, oldRequiredConceptUsed, requiredConceptUsed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getDetectedPatterns() {
		if (detectedPatterns == null) {
			detectedPatterns = new EDataTypeUniqueEList<String>(String.class, this, ContextPackage.STUDENT_CONTEXT__DETECTED_PATTERNS);
		}
		return detectedPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRepeatedErrorPattern() {
		return repeatedErrorPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRepeatedErrorPattern(boolean newRepeatedErrorPattern) {
		boolean oldRepeatedErrorPattern = repeatedErrorPattern;
		repeatedErrorPattern = newRepeatedErrorPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.STUDENT_CONTEXT__REPEATED_ERROR_PATTERN, oldRepeatedErrorPattern, repeatedErrorPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ContextPackage.STUDENT_CONTEXT__STUDENT_ID:
				return getStudentId();
			case ContextPackage.STUDENT_CONTEXT__CONCEPT_ID:
				return getConceptId();
			case ContextPackage.STUDENT_CONTEXT__ACTIVITY_ID:
				return getActivityId();
			case ContextPackage.STUDENT_CONTEXT__MASTERY_SCORE:
				return getMasteryScore();
			case ContextPackage.STUDENT_CONTEXT__ATTEMPT_COUNT:
				return getAttemptCount();
			case ContextPackage.STUDENT_CONTEXT__SUCCESS_COUNT:
				return getSuccessCount();
			case ContextPackage.STUDENT_CONTEXT__FAILURE_COUNT:
				return getFailureCount();
			case ContextPackage.STUDENT_CONTEXT__CONSECUTIVE_FAILURES:
				return getConsecutiveFailures();
			case ContextPackage.STUDENT_CONTEXT__HINT_COUNT:
				return getHintCount();
			case ContextPackage.STUDENT_CONTEXT__AVERAGE_RESOLUTION_TIME:
				return getAverageResolutionTime();
			case ContextPackage.STUDENT_CONTEXT__CURRENT_RESOLUTION_TIME:
				return getCurrentResolutionTime();
			case ContextPackage.STUDENT_CONTEXT__ACTIVITY_PASSED:
				return isActivityPassed();
			case ContextPackage.STUDENT_CONTEXT__FUNCTIONAL_PASSED:
				return isFunctionalPassed();
			case ContextPackage.STUDENT_CONTEXT__REQUIRED_CONCEPT_USED:
				return isRequiredConceptUsed();
			case ContextPackage.STUDENT_CONTEXT__DETECTED_PATTERNS:
				return getDetectedPatterns();
			case ContextPackage.STUDENT_CONTEXT__REPEATED_ERROR_PATTERN:
				return isRepeatedErrorPattern();
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
			case ContextPackage.STUDENT_CONTEXT__STUDENT_ID:
				setStudentId((String)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__CONCEPT_ID:
				setConceptId((String)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__ACTIVITY_ID:
				setActivityId((String)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__MASTERY_SCORE:
				setMasteryScore((BigDecimal)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__ATTEMPT_COUNT:
				setAttemptCount((Integer)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__SUCCESS_COUNT:
				setSuccessCount((Integer)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__FAILURE_COUNT:
				setFailureCount((Integer)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__CONSECUTIVE_FAILURES:
				setConsecutiveFailures((Integer)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__HINT_COUNT:
				setHintCount((Integer)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__AVERAGE_RESOLUTION_TIME:
				setAverageResolutionTime((BigDecimal)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__CURRENT_RESOLUTION_TIME:
				setCurrentResolutionTime((Long)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__ACTIVITY_PASSED:
				setActivityPassed((Boolean)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__FUNCTIONAL_PASSED:
				setFunctionalPassed((Boolean)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__REQUIRED_CONCEPT_USED:
				setRequiredConceptUsed((Boolean)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__DETECTED_PATTERNS:
				getDetectedPatterns().clear();
				getDetectedPatterns().addAll((Collection<? extends String>)newValue);
				return;
			case ContextPackage.STUDENT_CONTEXT__REPEATED_ERROR_PATTERN:
				setRepeatedErrorPattern((Boolean)newValue);
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
			case ContextPackage.STUDENT_CONTEXT__STUDENT_ID:
				setStudentId(STUDENT_ID_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__CONCEPT_ID:
				setConceptId(CONCEPT_ID_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__ACTIVITY_ID:
				setActivityId(ACTIVITY_ID_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__MASTERY_SCORE:
				setMasteryScore(MASTERY_SCORE_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__ATTEMPT_COUNT:
				setAttemptCount(ATTEMPT_COUNT_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__SUCCESS_COUNT:
				setSuccessCount(SUCCESS_COUNT_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__FAILURE_COUNT:
				setFailureCount(FAILURE_COUNT_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__CONSECUTIVE_FAILURES:
				setConsecutiveFailures(CONSECUTIVE_FAILURES_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__HINT_COUNT:
				setHintCount(HINT_COUNT_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__AVERAGE_RESOLUTION_TIME:
				setAverageResolutionTime(AVERAGE_RESOLUTION_TIME_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__CURRENT_RESOLUTION_TIME:
				setCurrentResolutionTime(CURRENT_RESOLUTION_TIME_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__ACTIVITY_PASSED:
				setActivityPassed(ACTIVITY_PASSED_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__FUNCTIONAL_PASSED:
				setFunctionalPassed(FUNCTIONAL_PASSED_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__REQUIRED_CONCEPT_USED:
				setRequiredConceptUsed(REQUIRED_CONCEPT_USED_EDEFAULT);
				return;
			case ContextPackage.STUDENT_CONTEXT__DETECTED_PATTERNS:
				getDetectedPatterns().clear();
				return;
			case ContextPackage.STUDENT_CONTEXT__REPEATED_ERROR_PATTERN:
				setRepeatedErrorPattern(REPEATED_ERROR_PATTERN_EDEFAULT);
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
			case ContextPackage.STUDENT_CONTEXT__STUDENT_ID:
				return STUDENT_ID_EDEFAULT == null ? studentId != null : !STUDENT_ID_EDEFAULT.equals(studentId);
			case ContextPackage.STUDENT_CONTEXT__CONCEPT_ID:
				return CONCEPT_ID_EDEFAULT == null ? conceptId != null : !CONCEPT_ID_EDEFAULT.equals(conceptId);
			case ContextPackage.STUDENT_CONTEXT__ACTIVITY_ID:
				return ACTIVITY_ID_EDEFAULT == null ? activityId != null : !ACTIVITY_ID_EDEFAULT.equals(activityId);
			case ContextPackage.STUDENT_CONTEXT__MASTERY_SCORE:
				return MASTERY_SCORE_EDEFAULT == null ? masteryScore != null : !MASTERY_SCORE_EDEFAULT.equals(masteryScore);
			case ContextPackage.STUDENT_CONTEXT__ATTEMPT_COUNT:
				return attemptCount != ATTEMPT_COUNT_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__SUCCESS_COUNT:
				return successCount != SUCCESS_COUNT_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__FAILURE_COUNT:
				return failureCount != FAILURE_COUNT_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__CONSECUTIVE_FAILURES:
				return consecutiveFailures != CONSECUTIVE_FAILURES_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__HINT_COUNT:
				return hintCount != HINT_COUNT_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__AVERAGE_RESOLUTION_TIME:
				return AVERAGE_RESOLUTION_TIME_EDEFAULT == null ? averageResolutionTime != null : !AVERAGE_RESOLUTION_TIME_EDEFAULT.equals(averageResolutionTime);
			case ContextPackage.STUDENT_CONTEXT__CURRENT_RESOLUTION_TIME:
				return currentResolutionTime != CURRENT_RESOLUTION_TIME_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__ACTIVITY_PASSED:
				return activityPassed != ACTIVITY_PASSED_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__FUNCTIONAL_PASSED:
				return functionalPassed != FUNCTIONAL_PASSED_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__REQUIRED_CONCEPT_USED:
				return requiredConceptUsed != REQUIRED_CONCEPT_USED_EDEFAULT;
			case ContextPackage.STUDENT_CONTEXT__DETECTED_PATTERNS:
				return detectedPatterns != null && !detectedPatterns.isEmpty();
			case ContextPackage.STUDENT_CONTEXT__REPEATED_ERROR_PATTERN:
				return repeatedErrorPattern != REPEATED_ERROR_PATTERN_EDEFAULT;
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
		result.append(" (studentId: ");
		result.append(studentId);
		result.append(", conceptId: ");
		result.append(conceptId);
		result.append(", activityId: ");
		result.append(activityId);
		result.append(", masteryScore: ");
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
		result.append(", currentResolutionTime: ");
		result.append(currentResolutionTime);
		result.append(", activityPassed: ");
		result.append(activityPassed);
		result.append(", functionalPassed: ");
		result.append(functionalPassed);
		result.append(", requiredConceptUsed: ");
		result.append(requiredConceptUsed);
		result.append(", detectedPatterns: ");
		result.append(detectedPatterns);
		result.append(", repeatedErrorPattern: ");
		result.append(repeatedErrorPattern);
		result.append(')');
		return result.toString();
	}

} //StudentContextImpl
