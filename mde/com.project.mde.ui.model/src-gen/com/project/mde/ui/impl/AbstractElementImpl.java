/**
 */
package com.project.mde.ui.impl;

import com.project.mde.ui.AbstractElement;
import com.project.mde.ui.AbstractKind;
import com.project.mde.ui.UiPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.impl.AbstractElementImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.AbstractElementImpl#getOriginId <em>Origin Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbstractElementImpl extends MinimalEObjectImpl.Container implements AbstractElement {
	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final AbstractKind KIND_EDEFAULT = AbstractKind.WORKSPACE;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected AbstractKind kind = KIND_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UiPackage.Literals.ABSTRACT_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKind(AbstractKind newKind) {
		AbstractKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.ABSTRACT_ELEMENT__KIND, oldKind, kind));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.ABSTRACT_ELEMENT__ORIGIN_ID, oldOriginId, originId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UiPackage.ABSTRACT_ELEMENT__KIND:
				return getKind();
			case UiPackage.ABSTRACT_ELEMENT__ORIGIN_ID:
				return getOriginId();
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
			case UiPackage.ABSTRACT_ELEMENT__KIND:
				setKind((AbstractKind)newValue);
				return;
			case UiPackage.ABSTRACT_ELEMENT__ORIGIN_ID:
				setOriginId((String)newValue);
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
			case UiPackage.ABSTRACT_ELEMENT__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case UiPackage.ABSTRACT_ELEMENT__ORIGIN_ID:
				setOriginId(ORIGIN_ID_EDEFAULT);
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
			case UiPackage.ABSTRACT_ELEMENT__KIND:
				return kind != KIND_EDEFAULT;
			case UiPackage.ABSTRACT_ELEMENT__ORIGIN_ID:
				return ORIGIN_ID_EDEFAULT == null ? originId != null : !ORIGIN_ID_EDEFAULT.equals(originId);
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
		result.append(" (kind: ");
		result.append(kind);
		result.append(", originId: ");
		result.append(originId);
		result.append(')');
		return result.toString();
	}

} //AbstractElementImpl
