/**
 */
package com.project.mde.adaptation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.AdaptationRuleSet#getName <em>Name</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRuleSet#getVersion <em>Version</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRuleSet#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRuleSet()
 * @model
 * @generated
 */
public interface AdaptationRuleSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRuleSet_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRuleSet#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(int)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRuleSet_Version()
	 * @model required="true"
	 * @generated
	 */
	int getVersion();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRuleSet#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(int value);

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.adaptation.AdaptationRule}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRuleSet_Rules()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<AdaptationRule> getRules();

} // AdaptationRuleSet
