/**
 */
package com.project.mde.context;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.context.ContextModel#getModelVersion <em>Model Version</em>}</li>
 *   <li>{@link com.project.mde.context.ContextModel#getStudentContext <em>Student Context</em>}</li>
 *   <li>{@link com.project.mde.context.ContextModel#getPlatformContext <em>Platform Context</em>}</li>
 *   <li>{@link com.project.mde.context.ContextModel#getEnvironmentContext <em>Environment Context</em>}</li>
 * </ul>
 *
 * @see com.project.mde.context.ContextPackage#getContextModel()
 * @model
 * @generated
 */
public interface ContextModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Model Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Version</em>' attribute.
	 * @see #setModelVersion(int)
	 * @see com.project.mde.context.ContextPackage#getContextModel_ModelVersion()
	 * @model required="true"
	 * @generated
	 */
	int getModelVersion();

	/**
	 * Sets the value of the '{@link com.project.mde.context.ContextModel#getModelVersion <em>Model Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Version</em>' attribute.
	 * @see #getModelVersion()
	 * @generated
	 */
	void setModelVersion(int value);

	/**
	 * Returns the value of the '<em><b>Student Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Student Context</em>' containment reference.
	 * @see #setStudentContext(StudentContext)
	 * @see com.project.mde.context.ContextPackage#getContextModel_StudentContext()
	 * @model containment="true" required="true"
	 * @generated
	 */
	StudentContext getStudentContext();

	/**
	 * Sets the value of the '{@link com.project.mde.context.ContextModel#getStudentContext <em>Student Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Student Context</em>' containment reference.
	 * @see #getStudentContext()
	 * @generated
	 */
	void setStudentContext(StudentContext value);

	/**
	 * Returns the value of the '<em><b>Platform Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Platform Context</em>' containment reference.
	 * @see #setPlatformContext(PlatformContext)
	 * @see com.project.mde.context.ContextPackage#getContextModel_PlatformContext()
	 * @model containment="true" required="true"
	 * @generated
	 */
	PlatformContext getPlatformContext();

	/**
	 * Sets the value of the '{@link com.project.mde.context.ContextModel#getPlatformContext <em>Platform Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Platform Context</em>' containment reference.
	 * @see #getPlatformContext()
	 * @generated
	 */
	void setPlatformContext(PlatformContext value);

	/**
	 * Returns the value of the '<em><b>Environment Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Context</em>' containment reference.
	 * @see #setEnvironmentContext(EnvironmentContext)
	 * @see com.project.mde.context.ContextPackage#getContextModel_EnvironmentContext()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EnvironmentContext getEnvironmentContext();

	/**
	 * Sets the value of the '{@link com.project.mde.context.ContextModel#getEnvironmentContext <em>Environment Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment Context</em>' containment reference.
	 * @see #getEnvironmentContext()
	 * @generated
	 */
	void setEnvironmentContext(EnvironmentContext value);

} // ContextModel
