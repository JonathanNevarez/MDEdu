/**
 */
package com.project.mde.ui;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract UI Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.AbstractUIModel#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.AbstractUIModel#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.ui.AbstractUIModel#getOriginId <em>Origin Id</em>}</li>
 *   <li>{@link com.project.mde.ui.AbstractUIModel#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see com.project.mde.ui.UiPackage#getAbstractUIModel()
 * @model
 * @generated
 */
public interface AbstractUIModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Id</em>' attribute.
	 * @see #setActivityId(String)
	 * @see com.project.mde.ui.UiPackage#getAbstractUIModel_ActivityId()
	 * @model required="true"
	 * @generated
	 */
	String getActivityId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.AbstractUIModel#getActivityId <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Id</em>' attribute.
	 * @see #getActivityId()
	 * @generated
	 */
	void setActivityId(String value);

	/**
	 * Returns the value of the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept Id</em>' attribute.
	 * @see #setConceptId(String)
	 * @see com.project.mde.ui.UiPackage#getAbstractUIModel_ConceptId()
	 * @model required="true"
	 * @generated
	 */
	String getConceptId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.AbstractUIModel#getConceptId <em>Concept Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept Id</em>' attribute.
	 * @see #getConceptId()
	 * @generated
	 */
	void setConceptId(String value);

	/**
	 * Returns the value of the '<em><b>Origin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin Id</em>' attribute.
	 * @see #setOriginId(String)
	 * @see com.project.mde.ui.UiPackage#getAbstractUIModel_OriginId()
	 * @model required="true"
	 * @generated
	 */
	String getOriginId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.AbstractUIModel#getOriginId <em>Origin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin Id</em>' attribute.
	 * @see #getOriginId()
	 * @generated
	 */
	void setOriginId(String value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.ui.AbstractElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see com.project.mde.ui.UiPackage#getAbstractUIModel_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractElement> getElements();

} // AbstractUIModel
