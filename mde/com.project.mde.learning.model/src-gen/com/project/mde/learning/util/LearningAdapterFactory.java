/**
 */
package com.project.mde.learning.util;

import com.project.mde.learning.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.project.mde.learning.LearningPackage
 * @generated
 */
public class LearningAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LearningPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LearningAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = LearningPackage.eINSTANCE;
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
	protected LearningSwitch<Adapter> modelSwitch =
		new LearningSwitch<Adapter>() {
			@Override
			public Adapter caseStudent(Student object) {
				return createStudentAdapter();
			}
			@Override
			public Adapter caseStudentModel(StudentModel object) {
				return createStudentModelAdapter();
			}
			@Override
			public Adapter caseConcept(Concept object) {
				return createConceptAdapter();
			}
			@Override
			public Adapter caseConceptMastery(ConceptMastery object) {
				return createConceptMasteryAdapter();
			}
			@Override
			public Adapter caseLearningObjective(LearningObjective object) {
				return createLearningObjectiveAdapter();
			}
			@Override
			public Adapter caseActivity(Activity object) {
				return createActivityAdapter();
			}
			@Override
			public Adapter caseAttempt(Attempt object) {
				return createAttemptAdapter();
			}
			@Override
			public Adapter caseErrorPattern(ErrorPattern object) {
				return createErrorPatternAdapter();
			}
			@Override
			public Adapter caseHintUsage(HintUsage object) {
				return createHintUsageAdapter();
			}
			@Override
			public Adapter caseProgress(Progress object) {
				return createProgressAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.Student <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.Student
	 * @generated
	 */
	public Adapter createStudentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.StudentModel <em>Student Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.StudentModel
	 * @generated
	 */
	public Adapter createStudentModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.Concept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.Concept
	 * @generated
	 */
	public Adapter createConceptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.ConceptMastery <em>Concept Mastery</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.ConceptMastery
	 * @generated
	 */
	public Adapter createConceptMasteryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.LearningObjective <em>Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.LearningObjective
	 * @generated
	 */
	public Adapter createLearningObjectiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.Activity
	 * @generated
	 */
	public Adapter createActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.Attempt <em>Attempt</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.Attempt
	 * @generated
	 */
	public Adapter createAttemptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.ErrorPattern <em>Error Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.ErrorPattern
	 * @generated
	 */
	public Adapter createErrorPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.HintUsage <em>Hint Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.HintUsage
	 * @generated
	 */
	public Adapter createHintUsageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.learning.Progress <em>Progress</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.learning.Progress
	 * @generated
	 */
	public Adapter createProgressAdapter() {
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

} //LearningAdapterFactory
