/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.Activity;
import com.project.mde.learning.HintUsage;
import com.project.mde.learning.LearningPackage;
import com.project.mde.learning.Student;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hint Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.impl.HintUsageImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.HintUsageImpl#getStudent <em>Student</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.HintUsageImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.HintUsageImpl#getHintLevel <em>Hint Level</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.HintUsageImpl#getUsedAt <em>Used At</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HintUsageImpl extends MinimalEObjectImpl.Container implements HintUsage {
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
	 * The default value of the '{@link #getHintLevel() <em>Hint Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintLevel()
	 * @generated
	 * @ordered
	 */
	protected static final int HINT_LEVEL_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHintLevel() <em>Hint Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintLevel()
	 * @generated
	 * @ordered
	 */
	protected int hintLevel = HINT_LEVEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsedAt() <em>Used At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedAt()
	 * @generated
	 * @ordered
	 */
	protected static final Date USED_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsedAt() <em>Used At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedAt()
	 * @generated
	 * @ordered
	 */
	protected Date usedAt = USED_AT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HintUsageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LearningPackage.Literals.HINT_USAGE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.HINT_USAGE__ID, oldId, id));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.HINT_USAGE__STUDENT, oldStudent, student));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.HINT_USAGE__STUDENT, oldStudent, student));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.HINT_USAGE__ACTIVITY, oldActivity, activity));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.HINT_USAGE__ACTIVITY, oldActivity, activity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getHintLevel() {
		return hintLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHintLevel(int newHintLevel) {
		int oldHintLevel = hintLevel;
		hintLevel = newHintLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.HINT_USAGE__HINT_LEVEL, oldHintLevel, hintLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getUsedAt() {
		return usedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUsedAt(Date newUsedAt) {
		Date oldUsedAt = usedAt;
		usedAt = newUsedAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.HINT_USAGE__USED_AT, oldUsedAt, usedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LearningPackage.HINT_USAGE__ID:
				return getId();
			case LearningPackage.HINT_USAGE__STUDENT:
				if (resolve) return getStudent();
				return basicGetStudent();
			case LearningPackage.HINT_USAGE__ACTIVITY:
				if (resolve) return getActivity();
				return basicGetActivity();
			case LearningPackage.HINT_USAGE__HINT_LEVEL:
				return getHintLevel();
			case LearningPackage.HINT_USAGE__USED_AT:
				return getUsedAt();
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
			case LearningPackage.HINT_USAGE__ID:
				setId((String)newValue);
				return;
			case LearningPackage.HINT_USAGE__STUDENT:
				setStudent((Student)newValue);
				return;
			case LearningPackage.HINT_USAGE__ACTIVITY:
				setActivity((Activity)newValue);
				return;
			case LearningPackage.HINT_USAGE__HINT_LEVEL:
				setHintLevel((Integer)newValue);
				return;
			case LearningPackage.HINT_USAGE__USED_AT:
				setUsedAt((Date)newValue);
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
			case LearningPackage.HINT_USAGE__ID:
				setId(ID_EDEFAULT);
				return;
			case LearningPackage.HINT_USAGE__STUDENT:
				setStudent((Student)null);
				return;
			case LearningPackage.HINT_USAGE__ACTIVITY:
				setActivity((Activity)null);
				return;
			case LearningPackage.HINT_USAGE__HINT_LEVEL:
				setHintLevel(HINT_LEVEL_EDEFAULT);
				return;
			case LearningPackage.HINT_USAGE__USED_AT:
				setUsedAt(USED_AT_EDEFAULT);
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
			case LearningPackage.HINT_USAGE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case LearningPackage.HINT_USAGE__STUDENT:
				return student != null;
			case LearningPackage.HINT_USAGE__ACTIVITY:
				return activity != null;
			case LearningPackage.HINT_USAGE__HINT_LEVEL:
				return hintLevel != HINT_LEVEL_EDEFAULT;
			case LearningPackage.HINT_USAGE__USED_AT:
				return USED_AT_EDEFAULT == null ? usedAt != null : !USED_AT_EDEFAULT.equals(usedAt);
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
		result.append(", hintLevel: ");
		result.append(hintLevel);
		result.append(", usedAt: ");
		result.append(usedAt);
		result.append(')');
		return result.toString();
	}

} //HintUsageImpl
