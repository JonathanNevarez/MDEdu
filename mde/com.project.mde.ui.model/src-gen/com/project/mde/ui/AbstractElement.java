/**
 */
package com.project.mde.ui;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.AbstractElement#getKind <em>Kind</em>}</li>
 *   <li>{@link com.project.mde.ui.AbstractElement#getOriginId <em>Origin Id</em>}</li>
 * </ul>
 *
 * @see com.project.mde.ui.UiPackage#getAbstractElement()
 * @model
 * @generated
 */
public interface AbstractElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.AbstractKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see com.project.mde.ui.AbstractKind
	 * @see #setKind(AbstractKind)
	 * @see com.project.mde.ui.UiPackage#getAbstractElement_Kind()
	 * @model required="true"
	 * @generated
	 */
	AbstractKind getKind();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.AbstractElement#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see com.project.mde.ui.AbstractKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(AbstractKind value);

	/**
	 * Returns the value of the '<em><b>Origin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin Id</em>' attribute.
	 * @see #setOriginId(String)
	 * @see com.project.mde.ui.UiPackage#getAbstractElement_OriginId()
	 * @model required="true"
	 * @generated
	 */
	String getOriginId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.AbstractElement#getOriginId <em>Origin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin Id</em>' attribute.
	 * @see #getOriginId()
	 * @generated
	 */
	void setOriginId(String value);

} // AbstractElement
