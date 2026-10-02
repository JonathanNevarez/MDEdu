/**
 */
package com.project.mde.adaptation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Explanation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.AdaptationExplanation#getRuleId <em>Rule Id</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationExplanation#getReason <em>Reason</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationExplanation#getEvidence <em>Evidence</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationExplanation()
 * @model
 * @generated
 */
public interface AdaptationExplanation extends EObject {
	/**
	 * Returns the value of the '<em><b>Rule Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule Id</em>' attribute.
	 * @see #setRuleId(String)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationExplanation_RuleId()
	 * @model required="true"
	 * @generated
	 */
	String getRuleId();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationExplanation#getRuleId <em>Rule Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule Id</em>' attribute.
	 * @see #getRuleId()
	 * @generated
	 */
	void setRuleId(String value);

	/**
	 * Returns the value of the '<em><b>Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reason</em>' attribute.
	 * @see #setReason(String)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationExplanation_Reason()
	 * @model required="true"
	 * @generated
	 */
	String getReason();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationExplanation#getReason <em>Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reason</em>' attribute.
	 * @see #getReason()
	 * @generated
	 */
	void setReason(String value);

	/**
	 * Returns the value of the '<em><b>Evidence</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evidence</em>' attribute list.
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationExplanation_Evidence()
	 * @model
	 * @generated
	 */
	EList<String> getEvidence();

} // AdaptationExplanation
