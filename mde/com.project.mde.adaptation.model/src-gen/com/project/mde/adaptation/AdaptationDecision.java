/**
 */
package com.project.mde.adaptation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decision</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.AdaptationDecision#getRuleId <em>Rule Id</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationDecision#getActions <em>Actions</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationDecision#getExplanation <em>Explanation</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationDecision()
 * @model
 * @generated
 */
public interface AdaptationDecision extends EObject {
	/**
	 * Returns the value of the '<em><b>Rule Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule Id</em>' attribute.
	 * @see #setRuleId(String)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationDecision_RuleId()
	 * @model required="true"
	 * @generated
	 */
	String getRuleId();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationDecision#getRuleId <em>Rule Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule Id</em>' attribute.
	 * @see #getRuleId()
	 * @generated
	 */
	void setRuleId(String value);

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.adaptation.Action}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions</em>' containment reference list.
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationDecision_Actions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Action> getActions();

	/**
	 * Returns the value of the '<em><b>Explanation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explanation</em>' containment reference.
	 * @see #setExplanation(AdaptationExplanation)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationDecision_Explanation()
	 * @model containment="true"
	 * @generated
	 */
	AdaptationExplanation getExplanation();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationDecision#getExplanation <em>Explanation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Explanation</em>' containment reference.
	 * @see #getExplanation()
	 * @generated
	 */
	void setExplanation(AdaptationExplanation value);

} // AdaptationDecision
