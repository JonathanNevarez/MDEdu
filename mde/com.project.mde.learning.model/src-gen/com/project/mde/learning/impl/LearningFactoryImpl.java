/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LearningFactoryImpl extends EFactoryImpl implements LearningFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LearningFactory init() {
		try {
			LearningFactory theLearningFactory = (LearningFactory)EPackage.Registry.INSTANCE.getEFactory(LearningPackage.eNS_URI);
			if (theLearningFactory != null) {
				return theLearningFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LearningFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LearningFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LearningPackage.STUDENT: return createStudent();
			case LearningPackage.STUDENT_MODEL: return createStudentModel();
			case LearningPackage.CONCEPT: return createConcept();
			case LearningPackage.CONCEPT_MASTERY: return createConceptMastery();
			case LearningPackage.LEARNING_OBJECTIVE: return createLearningObjective();
			case LearningPackage.ACTIVITY: return createActivity();
			case LearningPackage.ATTEMPT: return createAttempt();
			case LearningPackage.ERROR_PATTERN: return createErrorPattern();
			case LearningPackage.HINT_USAGE: return createHintUsage();
			case LearningPackage.PROGRESS: return createProgress();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Student createStudent() {
		StudentImpl student = new StudentImpl();
		return student;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StudentModel createStudentModel() {
		StudentModelImpl studentModel = new StudentModelImpl();
		return studentModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Concept createConcept() {
		ConceptImpl concept = new ConceptImpl();
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConceptMastery createConceptMastery() {
		ConceptMasteryImpl conceptMastery = new ConceptMasteryImpl();
		return conceptMastery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LearningObjective createLearningObjective() {
		LearningObjectiveImpl learningObjective = new LearningObjectiveImpl();
		return learningObjective;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Activity createActivity() {
		ActivityImpl activity = new ActivityImpl();
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Attempt createAttempt() {
		AttemptImpl attempt = new AttemptImpl();
		return attempt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ErrorPattern createErrorPattern() {
		ErrorPatternImpl errorPattern = new ErrorPatternImpl();
		return errorPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HintUsage createHintUsage() {
		HintUsageImpl hintUsage = new HintUsageImpl();
		return hintUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Progress createProgress() {
		ProgressImpl progress = new ProgressImpl();
		return progress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LearningPackage getLearningPackage() {
		return (LearningPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LearningPackage getPackage() {
		return LearningPackage.eINSTANCE;
	}

} //LearningFactoryImpl
