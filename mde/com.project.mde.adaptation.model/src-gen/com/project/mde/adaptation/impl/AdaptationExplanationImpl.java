/**
 */
package com.project.mde.adaptation.impl;

import com.project.mde.adaptation.AdaptationExplanation;
import com.project.mde.adaptation.AdaptationPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Explanation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.impl.AdaptationExplanationImpl#getRuleId <em>Rule Id</em>}</li>
 *   <li>{@link com.project.mde.adaptation.impl.AdaptationExplanationImpl#getReason <em>Reason</em>}</li>
 *   <li>{@link com.project.mde.adaptation.impl.AdaptationExplanationImpl#getEvidence <em>Evidence</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdaptationExplanationImpl extends MinimalEObjectImpl.Container implements AdaptationExplanation {
	/**
	 * The default value of the '{@link #getRuleId() <em>Rule Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuleId()
	 * @generated
	 * @ordered
	 */
	protected static final String RULE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRuleId() <em>Rule Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuleId()
	 * @generated
	 * @ordered
	 */
	protected String ruleId = RULE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getReason() <em>Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReason()
	 * @generated
	 * @ordered
	 */
	protected static final String REASON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReason() <em>Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReason()
	 * @generated
	 * @ordered
	 */
	protected String reason = REASON_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEvidence() <em>Evidence</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvidence()
	 * @generated
	 * @ordered
	 */
	protected EList<String> evidence;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationExplanationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdaptationPackage.Literals.ADAPTATION_EXPLANATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRuleId(String newRuleId) {
		String oldRuleId = ruleId;
		ruleId = newRuleId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_EXPLANATION__RULE_ID, oldRuleId, ruleId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getReason() {
		return reason;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReason(String newReason) {
		String oldReason = reason;
		reason = newReason;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_EXPLANATION__REASON, oldReason, reason));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getEvidence() {
		if (evidence == null) {
			evidence = new EDataTypeUniqueEList<String>(String.class, this, AdaptationPackage.ADAPTATION_EXPLANATION__EVIDENCE);
		}
		return evidence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AdaptationPackage.ADAPTATION_EXPLANATION__RULE_ID:
				return getRuleId();
			case AdaptationPackage.ADAPTATION_EXPLANATION__REASON:
				return getReason();
			case AdaptationPackage.ADAPTATION_EXPLANATION__EVIDENCE:
				return getEvidence();
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
			case AdaptationPackage.ADAPTATION_EXPLANATION__RULE_ID:
				setRuleId((String)newValue);
				return;
			case AdaptationPackage.ADAPTATION_EXPLANATION__REASON:
				setReason((String)newValue);
				return;
			case AdaptationPackage.ADAPTATION_EXPLANATION__EVIDENCE:
				getEvidence().clear();
				getEvidence().addAll((Collection<? extends String>)newValue);
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
			case AdaptationPackage.ADAPTATION_EXPLANATION__RULE_ID:
				setRuleId(RULE_ID_EDEFAULT);
				return;
			case AdaptationPackage.ADAPTATION_EXPLANATION__REASON:
				setReason(REASON_EDEFAULT);
				return;
			case AdaptationPackage.ADAPTATION_EXPLANATION__EVIDENCE:
				getEvidence().clear();
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
			case AdaptationPackage.ADAPTATION_EXPLANATION__RULE_ID:
				return RULE_ID_EDEFAULT == null ? ruleId != null : !RULE_ID_EDEFAULT.equals(ruleId);
			case AdaptationPackage.ADAPTATION_EXPLANATION__REASON:
				return REASON_EDEFAULT == null ? reason != null : !REASON_EDEFAULT.equals(reason);
			case AdaptationPackage.ADAPTATION_EXPLANATION__EVIDENCE:
				return evidence != null && !evidence.isEmpty();
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
		result.append(" (ruleId: ");
		result.append(ruleId);
		result.append(", reason: ");
		result.append(reason);
		result.append(", evidence: ");
		result.append(evidence);
		result.append(')');
		return result.toString();
	}

} //AdaptationExplanationImpl
