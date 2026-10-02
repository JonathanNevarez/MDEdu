/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.Activity;
import com.project.mde.learning.Attempt;
import com.project.mde.learning.Concept;
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
 * An implementation of the model object '<em><b>Attempt</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#getStudent <em>Student</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#isSuccessful <em>Successful</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#isFunctionalPassed <em>Functional Passed</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#getResolutionTime <em>Resolution Time</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#getHintCount <em>Hint Count</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#getSubmittedAt <em>Submitted At</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.AttemptImpl#getErrorPatterns <em>Error Patterns</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttemptImpl extends MinimalEObjectImpl.Container implements Attempt {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	 * The cached value of the '{@link #getActivity() <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected Activity activity;

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
	 * The default value of the '{@link #isSuccessful() <em>Successful</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuccessful()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUCCESSFUL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSuccessful() <em>Successful</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuccessful()
	 * @generated
	 * @ordered
	 */
	protected boolean successful = SUCCESSFUL_EDEFAULT;

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
	 * The default value of the '{@link #getResolutionTime() <em>Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionTime()
	 * @generated
	 * @ordered
	 */
	protected static final long RESOLUTION_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getResolutionTime() <em>Resolution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionTime()
	 * @generated
	 * @ordered
	 */
	protected long resolutionTime = RESOLUTION_TIME_EDEFAULT;

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
	 * The default value of the '{@link #getSubmittedAt() <em>Submitted At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubmittedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Date SUBMITTED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubmittedAt() <em>Submitted At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubmittedAt()
	 * @generated
	 * @ordered
	 */
	protected Date submittedAt = SUBMITTED_AT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getErrorPatterns() <em>Error Patterns</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList<ErrorPattern> errorPatterns;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttemptImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LearningPackage.Literals.ATTEMPT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__ID, oldId, id));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.ATTEMPT__STUDENT, oldStudent, student));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__STUDENT, oldStudent, student));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Activity getActivity() {
		if (activity != null && activity.eIsProxy()) {
			InternalEObject oldActivity = (InternalEObject)activity;
			activity = (Activity)eResolveProxy(oldActivity);
			if (activity != oldActivity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.ATTEMPT__ACTIVITY, oldActivity, activity));
			}
		}
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity basicGetActivity() {
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivity(Activity newActivity) {
		Activity oldActivity = activity;
		activity = newActivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__ACTIVITY, oldActivity, activity));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.ATTEMPT__CONCEPT, oldConcept, concept));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__CONCEPT, oldConcept, concept));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuccessful(boolean newSuccessful) {
		boolean oldSuccessful = successful;
		successful = newSuccessful;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__SUCCESSFUL, oldSuccessful, successful));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__FUNCTIONAL_PASSED, oldFunctionalPassed, functionalPassed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getResolutionTime() {
		return resolutionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResolutionTime(long newResolutionTime) {
		long oldResolutionTime = resolutionTime;
		resolutionTime = newResolutionTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__RESOLUTION_TIME, oldResolutionTime, resolutionTime));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__HINT_COUNT, oldHintCount, hintCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getSubmittedAt() {
		return submittedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSubmittedAt(Date newSubmittedAt) {
		Date oldSubmittedAt = submittedAt;
		submittedAt = newSubmittedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ATTEMPT__SUBMITTED_AT, oldSubmittedAt, submittedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ErrorPattern> getErrorPatterns() {
		if (errorPatterns == null) {
			errorPatterns = new EObjectResolvingEList<ErrorPattern>(ErrorPattern.class, this, LearningPackage.ATTEMPT__ERROR_PATTERNS);
		}
		return errorPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LearningPackage.ATTEMPT__ID:
				return getId();
			case LearningPackage.ATTEMPT__STUDENT:
				if (resolve) return getStudent();
				return basicGetStudent();
			case LearningPackage.ATTEMPT__ACTIVITY:
				if (resolve) return getActivity();
				return basicGetActivity();
			case LearningPackage.ATTEMPT__CONCEPT:
				if (resolve) return getConcept();
				return basicGetConcept();
			case LearningPackage.ATTEMPT__SUCCESSFUL:
				return isSuccessful();
			case LearningPackage.ATTEMPT__FUNCTIONAL_PASSED:
				return isFunctionalPassed();
			case LearningPackage.ATTEMPT__RESOLUTION_TIME:
				return getResolutionTime();
			case LearningPackage.ATTEMPT__HINT_COUNT:
				return getHintCount();
			case LearningPackage.ATTEMPT__SUBMITTED_AT:
				return getSubmittedAt();
			case LearningPackage.ATTEMPT__ERROR_PATTERNS:
				return getErrorPatterns();
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
			case LearningPackage.ATTEMPT__ID:
				setId((String)newValue);
				return;
			case LearningPackage.ATTEMPT__STUDENT:
				setStudent((Student)newValue);
				return;
			case LearningPackage.ATTEMPT__ACTIVITY:
				setActivity((Activity)newValue);
				return;
			case LearningPackage.ATTEMPT__CONCEPT:
				setConcept((Concept)newValue);
				return;
			case LearningPackage.ATTEMPT__SUCCESSFUL:
				setSuccessful((Boolean)newValue);
				return;
			case LearningPackage.ATTEMPT__FUNCTIONAL_PASSED:
				setFunctionalPassed((Boolean)newValue);
				return;
			case LearningPackage.ATTEMPT__RESOLUTION_TIME:
				setResolutionTime((Long)newValue);
				return;
			case LearningPackage.ATTEMPT__HINT_COUNT:
				setHintCount((Integer)newValue);
				return;
			case LearningPackage.ATTEMPT__SUBMITTED_AT:
				setSubmittedAt((Date)newValue);
				return;
			case LearningPackage.ATTEMPT__ERROR_PATTERNS:
				getErrorPatterns().clear();
				getErrorPatterns().addAll((Collection<? extends ErrorPattern>)newValue);
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
			case LearningPackage.ATTEMPT__ID:
				setId(ID_EDEFAULT);
				return;
			case LearningPackage.ATTEMPT__STUDENT:
				setStudent((Student)null);
				return;
			case LearningPackage.ATTEMPT__ACTIVITY:
				setActivity((Activity)null);
				return;
			case LearningPackage.ATTEMPT__CONCEPT:
				setConcept((Concept)null);
				return;
			case LearningPackage.ATTEMPT__SUCCESSFUL:
				setSuccessful(SUCCESSFUL_EDEFAULT);
				return;
			case LearningPackage.ATTEMPT__FUNCTIONAL_PASSED:
				setFunctionalPassed(FUNCTIONAL_PASSED_EDEFAULT);
				return;
			case LearningPackage.ATTEMPT__RESOLUTION_TIME:
				setResolutionTime(RESOLUTION_TIME_EDEFAULT);
				return;
			case LearningPackage.ATTEMPT__HINT_COUNT:
				setHintCount(HINT_COUNT_EDEFAULT);
				return;
			case LearningPackage.ATTEMPT__SUBMITTED_AT:
				setSubmittedAt(SUBMITTED_AT_EDEFAULT);
				return;
			case LearningPackage.ATTEMPT__ERROR_PATTERNS:
				getErrorPatterns().clear();
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
			case LearningPackage.ATTEMPT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case LearningPackage.ATTEMPT__STUDENT:
				return student != null;
			case LearningPackage.ATTEMPT__ACTIVITY:
				return activity != null;
			case LearningPackage.ATTEMPT__CONCEPT:
				return concept != null;
			case LearningPackage.ATTEMPT__SUCCESSFUL:
				return successful != SUCCESSFUL_EDEFAULT;
			case LearningPackage.ATTEMPT__FUNCTIONAL_PASSED:
				return functionalPassed != FUNCTIONAL_PASSED_EDEFAULT;
			case LearningPackage.ATTEMPT__RESOLUTION_TIME:
				return resolutionTime != RESOLUTION_TIME_EDEFAULT;
			case LearningPackage.ATTEMPT__HINT_COUNT:
				return hintCount != HINT_COUNT_EDEFAULT;
			case LearningPackage.ATTEMPT__SUBMITTED_AT:
				return SUBMITTED_AT_EDEFAULT == null ? submittedAt != null : !SUBMITTED_AT_EDEFAULT.equals(submittedAt);
			case LearningPackage.ATTEMPT__ERROR_PATTERNS:
				return errorPatterns != null && !errorPatterns.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(", successful: ");
		result.append(successful);
		result.append(", functionalPassed: ");
		result.append(functionalPassed);
		result.append(", resolutionTime: ");
		result.append(resolutionTime);
		result.append(", hintCount: ");
		result.append(hintCount);
		result.append(", submittedAt: ");
		result.append(submittedAt);
		result.append(')');
		return result.toString();
	}

} //AttemptImpl
