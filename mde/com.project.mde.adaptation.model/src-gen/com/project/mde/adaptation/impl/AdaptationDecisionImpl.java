/**
 */
package com.project.mde.adaptation.impl;

import com.project.mde.adaptation.Action;
import com.project.mde.adaptation.AdaptationDecision;
import com.project.mde.adaptation.AdaptationExplanation;
import com.project.mde.adaptation.AdaptationPackage;

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
 * An implementation of the model object '<em><b>Decision</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.impl.AdaptationDecisionImpl#getRuleId <em>Rule Id</em>}</li>
 *   <li>{@link com.project.mde.adaptation.impl.AdaptationDecisionImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link com.project.mde.adaptation.impl.AdaptationDecisionImpl#getExplanation <em>Explanation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdaptationDecisionImpl extends MinimalEObjectImpl.Container implements AdaptationDecision {
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
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected EList<Action> actions;

	/**
	 * The cached value of the '{@link #getExplanation() <em>Explanation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExplanation()
	 * @generated
	 * @ordered
	 */
	protected AdaptationExplanation explanation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationDecisionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdaptationPackage.Literals.ADAPTATION_DECISION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_DECISION__RULE_ID, oldRuleId, ruleId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Action> getActions() {
		if (actions == null) {
			actions = new EObjectContainmentEList<Action>(Action.class, this, AdaptationPackage.ADAPTATION_DECISION__ACTIONS);
		}
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationExplanation getExplanation() {
		return explanation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExplanation(AdaptationExplanation newExplanation, NotificationChain msgs) {
		AdaptationExplanation oldExplanation = explanation;
		explanation = newExplanation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_DECISION__EXPLANATION, oldExplanation, newExplanation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExplanation(AdaptationExplanation newExplanation) {
		if (newExplanation != explanation) {
			NotificationChain msgs = null;
			if (explanation != null)
				msgs = ((InternalEObject)explanation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdaptationPackage.ADAPTATION_DECISION__EXPLANATION, null, msgs);
			if (newExplanation != null)
				msgs = ((InternalEObject)newExplanation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AdaptationPackage.ADAPTATION_DECISION__EXPLANATION, null, msgs);
			msgs = basicSetExplanation(newExplanation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_DECISION__EXPLANATION, newExplanation, newExplanation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdaptationPackage.ADAPTATION_DECISION__ACTIONS:
				return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
			case AdaptationPackage.ADAPTATION_DECISION__EXPLANATION:
				return basicSetExplanation(null, msgs);
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
			case AdaptationPackage.ADAPTATION_DECISION__RULE_ID:
				return getRuleId();
			case AdaptationPackage.ADAPTATION_DECISION__ACTIONS:
				return getActions();
			case AdaptationPackage.ADAPTATION_DECISION__EXPLANATION:
				return getExplanation();
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
			case AdaptationPackage.ADAPTATION_DECISION__RULE_ID:
				setRuleId((String)newValue);
				return;
			case AdaptationPackage.ADAPTATION_DECISION__ACTIONS:
				getActions().clear();
				getActions().addAll((Collection<? extends Action>)newValue);
				return;
			case AdaptationPackage.ADAPTATION_DECISION__EXPLANATION:
				setExplanation((AdaptationExplanation)newValue);
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
			case AdaptationPackage.ADAPTATION_DECISION__RULE_ID:
				setRuleId(RULE_ID_EDEFAULT);
				return;
			case AdaptationPackage.ADAPTATION_DECISION__ACTIONS:
				getActions().clear();
				return;
			case AdaptationPackage.ADAPTATION_DECISION__EXPLANATION:
				setExplanation((AdaptationExplanation)null);
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
			case AdaptationPackage.ADAPTATION_DECISION__RULE_ID:
				return RULE_ID_EDEFAULT == null ? ruleId != null : !RULE_ID_EDEFAULT.equals(ruleId);
			case AdaptationPackage.ADAPTATION_DECISION__ACTIONS:
				return actions != null && !actions.isEmpty();
			case AdaptationPackage.ADAPTATION_DECISION__EXPLANATION:
				return explanation != null;
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
		result.append(')');
		return result.toString();
	}

} //AdaptationDecisionImpl
