/**
 */
package com.project.mde.adaptation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.Event#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.adaptation.EventType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.project.mde.adaptation.EventType
	 * @see #setType(EventType)
	 * @see com.project.mde.adaptation.AdaptationPackage#getEvent_Type()
	 * @model required="true"
	 * @generated
	 */
	EventType getType();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.Event#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.project.mde.adaptation.EventType
	 * @see #getType()
	 * @generated
	 */
	void setType(EventType value);

} // Event
