/**
 */
package com.project.mde.adaptation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.Action#getType <em>Type</em>}</li>
 *   <li>{@link com.project.mde.adaptation.Action#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getAction()
 * @model
 * @generated
 */
public interface Action extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.adaptation.ActionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.project.mde.adaptation.ActionType
	 * @see #setType(ActionType)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAction_Type()
	 * @model required="true"
	 * @generated
	 */
	ActionType getType();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.Action#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.project.mde.adaptation.ActionType
	 * @see #getType()
	 * @generated
	 */
	void setType(ActionType value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference.
	 * @see #setParameters(AdaptationParameters)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAction_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	AdaptationParameters getParameters();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.Action#getParameters <em>Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameters</em>' containment reference.
	 * @see #getParameters()
	 * @generated
	 */
	void setParameters(AdaptationParameters value);

} // Action
