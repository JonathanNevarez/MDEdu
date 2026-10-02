/**
 */
package com.project.mde.context.impl;

import com.project.mde.context.ContextPackage;
import com.project.mde.context.EnvironmentContext;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Environment Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.context.impl.EnvironmentContextImpl#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link com.project.mde.context.impl.EnvironmentContextImpl#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.context.impl.EnvironmentContextImpl#getActivityId <em>Activity Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnvironmentContextImpl extends MinimalEObjectImpl.Container implements EnvironmentContext {
	/**
	 * The default value of the '{@link #getLevelId() <em>Level Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevelId()
	 * @generated
	 * @ordered
	 */
	protected static final String LEVEL_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLevelId() <em>Level Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevelId()
	 * @generated
	 * @ordered
	 */
	protected String levelId = LEVEL_ID_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnvironmentContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ContextPackage.Literals.ENVIRONMENT_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLevelId() {
		return levelId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLevelId(String newLevelId) {
		String oldLevelId = levelId;
		levelId = newLevelId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ENVIRONMENT_CONTEXT__LEVEL_ID, oldLevelId, levelId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ENVIRONMENT_CONTEXT__CONCEPT_ID, oldConceptId, conceptId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.ENVIRONMENT_CONTEXT__ACTIVITY_ID, oldActivityId, activityId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ContextPackage.ENVIRONMENT_CONTEXT__LEVEL_ID:
				return getLevelId();
			case ContextPackage.ENVIRONMENT_CONTEXT__CONCEPT_ID:
				return getConceptId();
			case ContextPackage.ENVIRONMENT_CONTEXT__ACTIVITY_ID:
				return getActivityId();
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
			case ContextPackage.ENVIRONMENT_CONTEXT__LEVEL_ID:
				setLevelId((String)newValue);
				return;
			case ContextPackage.ENVIRONMENT_CONTEXT__CONCEPT_ID:
				setConceptId((String)newValue);
				return;
			case ContextPackage.ENVIRONMENT_CONTEXT__ACTIVITY_ID:
				setActivityId((String)newValue);
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
			case ContextPackage.ENVIRONMENT_CONTEXT__LEVEL_ID:
				setLevelId(LEVEL_ID_EDEFAULT);
				return;
			case ContextPackage.ENVIRONMENT_CONTEXT__CONCEPT_ID:
				setConceptId(CONCEPT_ID_EDEFAULT);
				return;
			case ContextPackage.ENVIRONMENT_CONTEXT__ACTIVITY_ID:
				setActivityId(ACTIVITY_ID_EDEFAULT);
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
			case ContextPackage.ENVIRONMENT_CONTEXT__LEVEL_ID:
				return LEVEL_ID_EDEFAULT == null ? levelId != null : !LEVEL_ID_EDEFAULT.equals(levelId);
			case ContextPackage.ENVIRONMENT_CONTEXT__CONCEPT_ID:
				return CONCEPT_ID_EDEFAULT == null ? conceptId != null : !CONCEPT_ID_EDEFAULT.equals(conceptId);
			case ContextPackage.ENVIRONMENT_CONTEXT__ACTIVITY_ID:
				return ACTIVITY_ID_EDEFAULT == null ? activityId != null : !ACTIVITY_ID_EDEFAULT.equals(activityId);
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
		result.append(" (levelId: ");
		result.append(levelId);
		result.append(", conceptId: ");
		result.append(conceptId);
		result.append(", activityId: ");
		result.append(activityId);
		result.append(')');
		return result.toString();
	}

} //EnvironmentContextImpl
