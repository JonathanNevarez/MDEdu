/**
 */
package com.project.mde.context;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.context.EnvironmentContext#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link com.project.mde.context.EnvironmentContext#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.context.EnvironmentContext#getActivityId <em>Activity Id</em>}</li>
 * </ul>
 *
 * @see com.project.mde.context.ContextPackage#getEnvironmentContext()
 * @model
 * @generated
 */
public interface EnvironmentContext extends EObject {
	/**
	 * Returns the value of the '<em><b>Level Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level Id</em>' attribute.
	 * @see #setLevelId(String)
	 * @see com.project.mde.context.ContextPackage#getEnvironmentContext_LevelId()
	 * @model required="true"
	 * @generated
	 */
	String getLevelId();

	/**
	 * Sets the value of the '{@link com.project.mde.context.EnvironmentContext#getLevelId <em>Level Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level Id</em>' attribute.
	 * @see #getLevelId()
	 * @generated
	 */
	void setLevelId(String value);

	/**
	 * Returns the value of the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept Id</em>' attribute.
	 * @see #setConceptId(String)
	 * @see com.project.mde.context.ContextPackage#getEnvironmentContext_ConceptId()
	 * @model required="true"
	 * @generated
	 */
	String getConceptId();

	/**
	 * Sets the value of the '{@link com.project.mde.context.EnvironmentContext#getConceptId <em>Concept Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept Id</em>' attribute.
	 * @see #getConceptId()
	 * @generated
	 */
	void setConceptId(String value);

	/**
	 * Returns the value of the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Id</em>' attribute.
	 * @see #setActivityId(String)
	 * @see com.project.mde.context.ContextPackage#getEnvironmentContext_ActivityId()
	 * @model required="true"
	 * @generated
	 */
	String getActivityId();

	/**
	 * Sets the value of the '{@link com.project.mde.context.EnvironmentContext#getActivityId <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Id</em>' attribute.
	 * @see #getActivityId()
	 * @generated
	 */
	void setActivityId(String value);

} // EnvironmentContext
