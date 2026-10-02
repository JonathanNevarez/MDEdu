/**
 */
package com.project.mde.context;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Platform Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.context.PlatformContext#getPlatform <em>Platform</em>}</li>
 * </ul>
 *
 * @see com.project.mde.context.ContextPackage#getPlatformContext()
 * @model
 * @generated
 */
public interface PlatformContext extends EObject {
	/**
	 * Returns the value of the '<em><b>Platform</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Platform</em>' attribute.
	 * @see #setPlatform(String)
	 * @see com.project.mde.context.ContextPackage#getPlatformContext_Platform()
	 * @model required="true"
	 * @generated
	 */
	String getPlatform();

	/**
	 * Sets the value of the '{@link com.project.mde.context.PlatformContext#getPlatform <em>Platform</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Platform</em>' attribute.
	 * @see #getPlatform()
	 * @generated
	 */
	void setPlatform(String value);

} // PlatformContext
