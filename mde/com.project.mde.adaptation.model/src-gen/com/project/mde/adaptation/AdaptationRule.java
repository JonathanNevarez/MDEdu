/**
 */
package com.project.mde.adaptation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.AdaptationRule#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRule#getName <em>Name</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRule#getVersion <em>Version</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRule#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRule#getPriority <em>Priority</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRule#getEvent <em>Event</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRule#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationRule#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule()
 * @model
 * @generated
 */
public interface AdaptationRule extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRule#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRule#getName <em>Name</em>}' attribute.
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
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule_Version()
	 * @model required="true"
	 * @generated
	 */
	int getVersion();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRule#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(int value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule_Enabled()
	 * @model required="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRule#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(int)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule_Priority()
	 * @model required="true"
	 * @generated
	 */
	int getPriority();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRule#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(int value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(Event)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule_Event()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Event getEvent();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRule#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Condition)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Condition getCondition();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationRule#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Condition value);

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.adaptation.Action}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions</em>' containment reference list.
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationRule_Actions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Action> getActions();

} // AdaptationRule
