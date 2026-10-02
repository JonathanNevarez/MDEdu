/**
 */
package com.project.mde.ui.util;

import com.project.mde.ui.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.project.mde.ui.UiPackage
 * @generated
 */
public class UiAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UiPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UiAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = UiPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UiSwitch<Adapter> modelSwitch =
		new UiSwitch<Adapter>() {
			@Override
			public Adapter caseTaskAndDomainModel(TaskAndDomainModel object) {
				return createTaskAndDomainModelAdapter();
			}
			@Override
			public Adapter caseAbstractUIModel(AbstractUIModel object) {
				return createAbstractUIModelAdapter();
			}
			@Override
			public Adapter caseAbstractElement(AbstractElement object) {
				return createAbstractElementAdapter();
			}
			@Override
			public Adapter caseConcreteUIModel(ConcreteUIModel object) {
				return createConcreteUIModelAdapter();
			}
			@Override
			public Adapter caseConcreteElement(ConcreteElement object) {
				return createConcreteElementAdapter();
			}
			@Override
			public Adapter caseFinalUIConfiguration(FinalUIConfiguration object) {
				return createFinalUIConfigurationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.ui.TaskAndDomainModel <em>Task And Domain Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.ui.TaskAndDomainModel
	 * @generated
	 */
	public Adapter createTaskAndDomainModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.ui.AbstractUIModel <em>Abstract UI Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.ui.AbstractUIModel
	 * @generated
	 */
	public Adapter createAbstractUIModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.ui.AbstractElement <em>Abstract Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.ui.AbstractElement
	 * @generated
	 */
	public Adapter createAbstractElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.ui.ConcreteUIModel <em>Concrete UI Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.ui.ConcreteUIModel
	 * @generated
	 */
	public Adapter createConcreteUIModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.ui.ConcreteElement <em>Concrete Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.ui.ConcreteElement
	 * @generated
	 */
	public Adapter createConcreteElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.ui.FinalUIConfiguration <em>Final UI Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.ui.FinalUIConfiguration
	 * @generated
	 */
	public Adapter createFinalUIConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //UiAdapterFactory
