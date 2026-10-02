/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.Activity;
import com.project.mde.learning.Concept;
import com.project.mde.learning.LearningPackage;
import com.project.mde.learning.Progress;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Progress</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.impl.ProgressImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ProgressImpl#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ProgressImpl#isCompleted <em>Completed</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ProgressImpl#isUnlocked <em>Unlocked</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ProgressImpl#getCompletedAt <em>Completed At</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProgressImpl extends MinimalEObjectImpl.Container implements Progress {
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
	 * The default value of the '{@link #isCompleted() <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCompleted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COMPLETED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCompleted() <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCompleted()
	 * @generated
	 * @ordered
	 */
	protected boolean completed = COMPLETED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnlocked() <em>Unlocked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnlocked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNLOCKED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnlocked() <em>Unlocked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnlocked()
	 * @generated
	 * @ordered
	 */
	protected boolean unlocked = UNLOCKED_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompletedAt() <em>Completed At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Date COMPLETED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompletedAt() <em>Completed At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletedAt()
	 * @generated
	 * @ordered
	 */
	protected Date completedAt = COMPLETED_AT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProgressImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LearningPackage.Literals.PROGRESS;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.PROGRESS__ACTIVITY, oldActivity, activity));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.PROGRESS__ACTIVITY, oldActivity, activity));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.PROGRESS__CONCEPT, oldConcept, concept));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.PROGRESS__CONCEPT, oldConcept, concept));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompleted(boolean newCompleted) {
		boolean oldCompleted = completed;
		completed = newCompleted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.PROGRESS__COMPLETED, oldCompleted, completed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUnlocked() {
		return unlocked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnlocked(boolean newUnlocked) {
		boolean oldUnlocked = unlocked;
		unlocked = newUnlocked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.PROGRESS__UNLOCKED, oldUnlocked, unlocked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getCompletedAt() {
		return completedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompletedAt(Date newCompletedAt) {
		Date oldCompletedAt = completedAt;
		completedAt = newCompletedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.PROGRESS__COMPLETED_AT, oldCompletedAt, completedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LearningPackage.PROGRESS__ACTIVITY:
				if (resolve) return getActivity();
				return basicGetActivity();
			case LearningPackage.PROGRESS__CONCEPT:
				if (resolve) return getConcept();
				return basicGetConcept();
			case LearningPackage.PROGRESS__COMPLETED:
				return isCompleted();
			case LearningPackage.PROGRESS__UNLOCKED:
				return isUnlocked();
			case LearningPackage.PROGRESS__COMPLETED_AT:
				return getCompletedAt();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LearningPackage.PROGRESS__ACTIVITY:
				setActivity((Activity)newValue);
				return;
			case LearningPackage.PROGRESS__CONCEPT:
				setConcept((Concept)newValue);
				return;
			case LearningPackage.PROGRESS__COMPLETED:
				setCompleted((Boolean)newValue);
				return;
			case LearningPackage.PROGRESS__UNLOCKED:
				setUnlocked((Boolean)newValue);
				return;
			case LearningPackage.PROGRESS__COMPLETED_AT:
				setCompletedAt((Date)newValue);
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
			case LearningPackage.PROGRESS__ACTIVITY:
				setActivity((Activity)null);
				return;
			case LearningPackage.PROGRESS__CONCEPT:
				setConcept((Concept)null);
				return;
			case LearningPackage.PROGRESS__COMPLETED:
				setCompleted(COMPLETED_EDEFAULT);
				return;
			case LearningPackage.PROGRESS__UNLOCKED:
				setUnlocked(UNLOCKED_EDEFAULT);
				return;
			case LearningPackage.PROGRESS__COMPLETED_AT:
				setCompletedAt(COMPLETED_AT_EDEFAULT);
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
			case LearningPackage.PROGRESS__ACTIVITY:
				return activity != null;
			case LearningPackage.PROGRESS__CONCEPT:
				return concept != null;
			case LearningPackage.PROGRESS__COMPLETED:
				return completed != COMPLETED_EDEFAULT;
			case LearningPackage.PROGRESS__UNLOCKED:
				return unlocked != UNLOCKED_EDEFAULT;
			case LearningPackage.PROGRESS__COMPLETED_AT:
				return COMPLETED_AT_EDEFAULT == null ? completedAt != null : !COMPLETED_AT_EDEFAULT.equals(completedAt);
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
		result.append(" (completed: ");
		result.append(completed);
		result.append(", unlocked: ");
		result.append(unlocked);
		result.append(", completedAt: ");
		result.append(completedAt);
		result.append(')');
		return result.toString();
	}

} //ProgressImpl
