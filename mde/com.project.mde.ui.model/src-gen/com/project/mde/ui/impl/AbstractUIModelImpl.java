/**
 */
package com.project.mde.ui.impl;

import com.project.mde.ui.AbstractElement;
import com.project.mde.ui.AbstractUIModel;
import com.project.mde.ui.UiPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract UI Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.impl.AbstractUIModelImpl#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.AbstractUIModelImpl#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.AbstractUIModelImpl#getOriginId <em>Origin Id</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.AbstractUIModelImpl#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbstractUIModelImpl extends MinimalEObjectImpl.Container implements AbstractUIModel {
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
	 * The default value of the '{@link #getOriginId() <em>Origin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginId()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGIN_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginId() <em>Origin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginId()
	 * @generated
	 * @ordered
	 */
	protected String originId = ORIGIN_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractElement> elements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractUIModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UiPackage.Literals.ABSTRACT_UI_MODEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.ABSTRACT_UI_MODEL__ACTIVITY_ID, oldActivityId, activityId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.ABSTRACT_UI_MODEL__CONCEPT_ID, oldConceptId, conceptId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOriginId() {
		return originId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOriginId(String newOriginId) {
		String oldOriginId = originId;
		originId = newOriginId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.ABSTRACT_UI_MODEL__ORIGIN_ID, oldOriginId, originId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<AbstractElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentEList<AbstractElement>(AbstractElement.class, this, UiPackage.ABSTRACT_UI_MODEL__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UiPackage.ABSTRACT_UI_MODEL__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UiPackage.ABSTRACT_UI_MODEL__ACTIVITY_ID:
				return getActivityId();
			case UiPackage.ABSTRACT_UI_MODEL__CONCEPT_ID:
				return getConceptId();
			case UiPackage.ABSTRACT_UI_MODEL__ORIGIN_ID:
				return getOriginId();
			case UiPackage.ABSTRACT_UI_MODEL__ELEMENTS:
				return getElements();
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
			case UiPackage.ABSTRACT_UI_MODEL__ACTIVITY_ID:
				setActivityId((String)newValue);
				return;
			case UiPackage.ABSTRACT_UI_MODEL__CONCEPT_ID:
				setConceptId((String)newValue);
				return;
			case UiPackage.ABSTRACT_UI_MODEL__ORIGIN_ID:
				setOriginId((String)newValue);
				return;
			case UiPackage.ABSTRACT_UI_MODEL__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends AbstractElement>)newValue);
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
			case UiPackage.ABSTRACT_UI_MODEL__ACTIVITY_ID:
				setActivityId(ACTIVITY_ID_EDEFAULT);
				return;
			case UiPackage.ABSTRACT_UI_MODEL__CONCEPT_ID:
				setConceptId(CONCEPT_ID_EDEFAULT);
				return;
			case UiPackage.ABSTRACT_UI_MODEL__ORIGIN_ID:
				setOriginId(ORIGIN_ID_EDEFAULT);
				return;
			case UiPackage.ABSTRACT_UI_MODEL__ELEMENTS:
				getElements().clear();
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
			case UiPackage.ABSTRACT_UI_MODEL__ACTIVITY_ID:
				return ACTIVITY_ID_EDEFAULT == null ? activityId != null : !ACTIVITY_ID_EDEFAULT.equals(activityId);
			case UiPackage.ABSTRACT_UI_MODEL__CONCEPT_ID:
				return CONCEPT_ID_EDEFAULT == null ? conceptId != null : !CONCEPT_ID_EDEFAULT.equals(conceptId);
			case UiPackage.ABSTRACT_UI_MODEL__ORIGIN_ID:
				return ORIGIN_ID_EDEFAULT == null ? originId != null : !ORIGIN_ID_EDEFAULT.equals(originId);
			case UiPackage.ABSTRACT_UI_MODEL__ELEMENTS:
				return elements != null && !elements.isEmpty();
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
		result.append(" (activityId: ");
		result.append(activityId);
		result.append(", conceptId: ");
		result.append(conceptId);
		result.append(", originId: ");
		result.append(originId);
		result.append(')');
		return result.toString();
	}

} //AbstractUIModelImpl
