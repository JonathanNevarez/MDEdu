/**
 */
package com.project.mde.ui;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concrete Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.ConcreteElement#getKind <em>Kind</em>}</li>
 *   <li>{@link com.project.mde.ui.ConcreteElement#getOriginId <em>Origin Id</em>}</li>
 * </ul>
 *
 * @see com.project.mde.ui.UiPackage#getConcreteElement()
 * @model
 * @generated
 */
public interface ConcreteElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.ConcreteKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see com.project.mde.ui.ConcreteKind
	 * @see #setKind(ConcreteKind)
	 * @see com.project.mde.ui.UiPackage#getConcreteElement_Kind()
	 * @model required="true"
	 * @generated
	 */
	ConcreteKind getKind();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.ConcreteElement#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see com.project.mde.ui.ConcreteKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(ConcreteKind value);

	/**
	 * Returns the value of the '<em><b>Origin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin Id</em>' attribute.
	 * @see #setOriginId(String)
	 * @see com.project.mde.ui.UiPackage#getConcreteElement_OriginId()
	 * @model required="true"
	 * @generated
	 */
	String getOriginId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.ConcreteElement#getOriginId <em>Origin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin Id</em>' attribute.
	 * @see #getOriginId()
	 * @generated
	 */
	void setOriginId(String value);

} // ConcreteElement
