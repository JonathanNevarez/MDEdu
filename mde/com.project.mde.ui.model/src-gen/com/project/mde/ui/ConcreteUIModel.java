/**
 */
package com.project.mde.ui;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concrete UI Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.ConcreteUIModel#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.ConcreteUIModel#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.ui.ConcreteUIModel#getOriginId <em>Origin Id</em>}</li>
 *   <li>{@link com.project.mde.ui.ConcreteUIModel#getLayout <em>Layout</em>}</li>
 *   <li>{@link com.project.mde.ui.ConcreteUIModel#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see com.project.mde.ui.UiPackage#getConcreteUIModel()
 * @model
 * @generated
 */
public interface ConcreteUIModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Id</em>' attribute.
	 * @see #setActivityId(String)
	 * @see com.project.mde.ui.UiPackage#getConcreteUIModel_ActivityId()
	 * @model required="true"
	 * @generated
	 */
	String getActivityId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.ConcreteUIModel#getActivityId <em>Activity Id</em>}' attribute.
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
	 * @see com.project.mde.ui.UiPackage#getConcreteUIModel_ConceptId()
	 * @model required="true"
	 * @generated
	 */
	String getConceptId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.ConcreteUIModel#getConceptId <em>Concept Id</em>}' attribute.
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
	 * @see com.project.mde.ui.UiPackage#getConcreteUIModel_OriginId()
	 * @model required="true"
	 * @generated
	 */
	String getOriginId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.ConcreteUIModel#getOriginId <em>Origin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin Id</em>' attribute.
	 * @see #getOriginId()
	 * @generated
	 */
	void setOriginId(String value);

	/**
	 * Returns the value of the '<em><b>Layout</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.ActivityLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layout</em>' attribute.
	 * @see com.project.mde.ui.ActivityLayout
	 * @see #setLayout(ActivityLayout)
	 * @see com.project.mde.ui.UiPackage#getConcreteUIModel_Layout()
	 * @model required="true"
	 * @generated
	 */
	ActivityLayout getLayout();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.ConcreteUIModel#getLayout <em>Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layout</em>' attribute.
	 * @see com.project.mde.ui.ActivityLayout
	 * @see #getLayout()
	 * @generated
	 */
	void setLayout(ActivityLayout value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.ui.ConcreteElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see com.project.mde.ui.UiPackage#getConcreteUIModel_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConcreteElement> getElements();

} // ConcreteUIModel
