/**
 */
package com.project.mde.ui;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.project.mde.ui.UiPackage
 * @generated
 */
public interface UiFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UiFactory eINSTANCE = com.project.mde.ui.impl.UiFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Task And Domain Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task And Domain Model</em>'.
	 * @generated
	 */
	TaskAndDomainModel createTaskAndDomainModel();

	/**
	 * Returns a new object of class '<em>Abstract UI Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract UI Model</em>'.
	 * @generated
	 */
	AbstractUIModel createAbstractUIModel();

	/**
	 * Returns a new object of class '<em>Abstract Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Element</em>'.
	 * @generated
	 */
	AbstractElement createAbstractElement();

	/**
	 * Returns a new object of class '<em>Concrete UI Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Concrete UI Model</em>'.
	 * @generated
	 */
	ConcreteUIModel createConcreteUIModel();

	/**
	 * Returns a new object of class '<em>Concrete Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Concrete Element</em>'.
	 * @generated
	 */
	ConcreteElement createConcreteElement();

	/**
	 * Returns a new object of class '<em>Final UI Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Final UI Configuration</em>'.
	 * @generated
	 */
	FinalUIConfiguration createFinalUIConfiguration();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UiPackage getUiPackage();

} //UiFactory
