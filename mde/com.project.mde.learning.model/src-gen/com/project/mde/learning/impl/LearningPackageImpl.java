/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.Activity;
import com.project.mde.learning.Attempt;
import com.project.mde.learning.Concept;
import com.project.mde.learning.ConceptMastery;
import com.project.mde.learning.ErrorPattern;
import com.project.mde.learning.HintUsage;
import com.project.mde.learning.LearningFactory;
import com.project.mde.learning.LearningObjective;
import com.project.mde.learning.LearningPackage;
import com.project.mde.learning.Progress;
import com.project.mde.learning.Student;
import com.project.mde.learning.StudentModel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LearningPackageImpl extends EPackageImpl implements LearningPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass studentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass studentModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conceptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conceptMasteryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass learningObjectiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attemptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hintUsageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass progressEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.project.mde.learning.LearningPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LearningPackageImpl() {
		super(eNS_URI, LearningFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link LearningPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LearningPackage init() {
		if (isInited) return (LearningPackage)EPackage.Registry.INSTANCE.getEPackage(LearningPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredLearningPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		LearningPackageImpl theLearningPackage = registeredLearningPackage instanceof LearningPackageImpl ? (LearningPackageImpl)registeredLearningPackage : new LearningPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theLearningPackage.createPackageContents();

		// Initialize created meta-data
		theLearningPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLearningPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LearningPackage.eNS_URI, theLearningPackage);
		return theLearningPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStudent() {
		return studentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStudent_Id() {
		return (EAttribute)studentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStudent_DisplayName() {
		return (EAttribute)studentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStudent_CreatedAt() {
		return (EAttribute)studentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStudentModel() {
		return studentModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStudentModel_ModelVersion() {
		return (EAttribute)studentModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStudentModel_LastUpdated() {
		return (EAttribute)studentModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_Student() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_Concepts() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_Activities() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_LearningObjectives() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_ErrorPatterns() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_ConceptMasteries() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_Attempts() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_HintUsages() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentModel_Progress() {
		return (EReference)studentModelEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConcept() {
		return conceptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcept_Id() {
		return (EAttribute)conceptEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcept_Name() {
		return (EAttribute)conceptEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConcept_Prerequisites() {
		return (EReference)conceptEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcept_MinimumMasteryToUnlock() {
		return (EAttribute)conceptEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConcept_Activities() {
		return (EReference)conceptEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConcept_ReinforcementActivities() {
		return (EReference)conceptEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConceptMastery() {
		return conceptMasteryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConceptMastery_Student() {
		return (EReference)conceptMasteryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConceptMastery_Concept() {
		return (EReference)conceptMasteryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConceptMastery_MasteryScore() {
		return (EAttribute)conceptMasteryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConceptMastery_AttemptCount() {
		return (EAttribute)conceptMasteryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConceptMastery_SuccessCount() {
		return (EAttribute)conceptMasteryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConceptMastery_FailureCount() {
		return (EAttribute)conceptMasteryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConceptMastery_ConsecutiveFailures() {
		return (EAttribute)conceptMasteryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConceptMastery_HintCount() {
		return (EAttribute)conceptMasteryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConceptMastery_AverageResolutionTime() {
		return (EAttribute)conceptMasteryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConceptMastery_LastUpdated() {
		return (EAttribute)conceptMasteryEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConceptMastery_RecentErrorPatterns() {
		return (EReference)conceptMasteryEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLearningObjective() {
		return learningObjectiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLearningObjective_Id() {
		return (EAttribute)learningObjectiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLearningObjective_Description() {
		return (EAttribute)learningObjectiveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLearningObjective_Concept() {
		return (EReference)learningObjectiveEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getActivity() {
		return activityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getActivity_Id() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getActivity_Title() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getActivity_Concept() {
		return (EReference)activityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getActivity_LearningObjectives() {
		return (EReference)activityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAttempt() {
		return attemptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttempt_Id() {
		return (EAttribute)attemptEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAttempt_Student() {
		return (EReference)attemptEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAttempt_Activity() {
		return (EReference)attemptEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAttempt_Concept() {
		return (EReference)attemptEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttempt_Successful() {
		return (EAttribute)attemptEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttempt_FunctionalPassed() {
		return (EAttribute)attemptEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttempt_ResolutionTime() {
		return (EAttribute)attemptEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttempt_HintCount() {
		return (EAttribute)attemptEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttempt_SubmittedAt() {
		return (EAttribute)attemptEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAttempt_ErrorPatterns() {
		return (EReference)attemptEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getErrorPattern() {
		return errorPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorPattern_Id() {
		return (EAttribute)errorPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getErrorPattern_Concept() {
		return (EReference)errorPatternEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorPattern_Severity() {
		return (EAttribute)errorPatternEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorPattern_PedagogicalMeaning() {
		return (EAttribute)errorPatternEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHintUsage() {
		return hintUsageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHintUsage_Id() {
		return (EAttribute)hintUsageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHintUsage_Student() {
		return (EReference)hintUsageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHintUsage_Activity() {
		return (EReference)hintUsageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHintUsage_HintLevel() {
		return (EAttribute)hintUsageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHintUsage_UsedAt() {
		return (EAttribute)hintUsageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProgress() {
		return progressEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProgress_Activity() {
		return (EReference)progressEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProgress_Concept() {
		return (EReference)progressEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProgress_Completed() {
		return (EAttribute)progressEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProgress_Unlocked() {
		return (EAttribute)progressEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProgress_CompletedAt() {
		return (EAttribute)progressEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LearningFactory getLearningFactory() {
		return (LearningFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		studentEClass = createEClass(STUDENT);
		createEAttribute(studentEClass, STUDENT__ID);
		createEAttribute(studentEClass, STUDENT__DISPLAY_NAME);
		createEAttribute(studentEClass, STUDENT__CREATED_AT);

		studentModelEClass = createEClass(STUDENT_MODEL);
		createEAttribute(studentModelEClass, STUDENT_MODEL__MODEL_VERSION);
		createEAttribute(studentModelEClass, STUDENT_MODEL__LAST_UPDATED);
		createEReference(studentModelEClass, STUDENT_MODEL__STUDENT);
		createEReference(studentModelEClass, STUDENT_MODEL__CONCEPTS);
		createEReference(studentModelEClass, STUDENT_MODEL__ACTIVITIES);
		createEReference(studentModelEClass, STUDENT_MODEL__LEARNING_OBJECTIVES);
		createEReference(studentModelEClass, STUDENT_MODEL__ERROR_PATTERNS);
		createEReference(studentModelEClass, STUDENT_MODEL__CONCEPT_MASTERIES);
		createEReference(studentModelEClass, STUDENT_MODEL__ATTEMPTS);
		createEReference(studentModelEClass, STUDENT_MODEL__HINT_USAGES);
		createEReference(studentModelEClass, STUDENT_MODEL__PROGRESS);

		conceptEClass = createEClass(CONCEPT);
		createEAttribute(conceptEClass, CONCEPT__ID);
		createEAttribute(conceptEClass, CONCEPT__NAME);
		createEReference(conceptEClass, CONCEPT__PREREQUISITES);
		createEAttribute(conceptEClass, CONCEPT__MINIMUM_MASTERY_TO_UNLOCK);
		createEReference(conceptEClass, CONCEPT__ACTIVITIES);
		createEReference(conceptEClass, CONCEPT__REINFORCEMENT_ACTIVITIES);

		conceptMasteryEClass = createEClass(CONCEPT_MASTERY);
		createEReference(conceptMasteryEClass, CONCEPT_MASTERY__STUDENT);
		createEReference(conceptMasteryEClass, CONCEPT_MASTERY__CONCEPT);
		createEAttribute(conceptMasteryEClass, CONCEPT_MASTERY__MASTERY_SCORE);
		createEAttribute(conceptMasteryEClass, CONCEPT_MASTERY__ATTEMPT_COUNT);
		createEAttribute(conceptMasteryEClass, CONCEPT_MASTERY__SUCCESS_COUNT);
		createEAttribute(conceptMasteryEClass, CONCEPT_MASTERY__FAILURE_COUNT);
		createEAttribute(conceptMasteryEClass, CONCEPT_MASTERY__CONSECUTIVE_FAILURES);
		createEAttribute(conceptMasteryEClass, CONCEPT_MASTERY__HINT_COUNT);
		createEAttribute(conceptMasteryEClass, CONCEPT_MASTERY__AVERAGE_RESOLUTION_TIME);
		createEAttribute(conceptMasteryEClass, CONCEPT_MASTERY__LAST_UPDATED);
		createEReference(conceptMasteryEClass, CONCEPT_MASTERY__RECENT_ERROR_PATTERNS);

		learningObjectiveEClass = createEClass(LEARNING_OBJECTIVE);
		createEAttribute(learningObjectiveEClass, LEARNING_OBJECTIVE__ID);
		createEAttribute(learningObjectiveEClass, LEARNING_OBJECTIVE__DESCRIPTION);
		createEReference(learningObjectiveEClass, LEARNING_OBJECTIVE__CONCEPT);

		activityEClass = createEClass(ACTIVITY);
		createEAttribute(activityEClass, ACTIVITY__ID);
		createEAttribute(activityEClass, ACTIVITY__TITLE);
		createEReference(activityEClass, ACTIVITY__CONCEPT);
		createEReference(activityEClass, ACTIVITY__LEARNING_OBJECTIVES);

		attemptEClass = createEClass(ATTEMPT);
		createEAttribute(attemptEClass, ATTEMPT__ID);
		createEReference(attemptEClass, ATTEMPT__STUDENT);
		createEReference(attemptEClass, ATTEMPT__ACTIVITY);
		createEReference(attemptEClass, ATTEMPT__CONCEPT);
		createEAttribute(attemptEClass, ATTEMPT__SUCCESSFUL);
		createEAttribute(attemptEClass, ATTEMPT__FUNCTIONAL_PASSED);
		createEAttribute(attemptEClass, ATTEMPT__RESOLUTION_TIME);
		createEAttribute(attemptEClass, ATTEMPT__HINT_COUNT);
		createEAttribute(attemptEClass, ATTEMPT__SUBMITTED_AT);
		createEReference(attemptEClass, ATTEMPT__ERROR_PATTERNS);

		errorPatternEClass = createEClass(ERROR_PATTERN);
		createEAttribute(errorPatternEClass, ERROR_PATTERN__ID);
		createEReference(errorPatternEClass, ERROR_PATTERN__CONCEPT);
		createEAttribute(errorPatternEClass, ERROR_PATTERN__SEVERITY);
		createEAttribute(errorPatternEClass, ERROR_PATTERN__PEDAGOGICAL_MEANING);

		hintUsageEClass = createEClass(HINT_USAGE);
		createEAttribute(hintUsageEClass, HINT_USAGE__ID);
		createEReference(hintUsageEClass, HINT_USAGE__STUDENT);
		createEReference(hintUsageEClass, HINT_USAGE__ACTIVITY);
		createEAttribute(hintUsageEClass, HINT_USAGE__HINT_LEVEL);
		createEAttribute(hintUsageEClass, HINT_USAGE__USED_AT);

		progressEClass = createEClass(PROGRESS);
		createEReference(progressEClass, PROGRESS__ACTIVITY);
		createEReference(progressEClass, PROGRESS__CONCEPT);
		createEAttribute(progressEClass, PROGRESS__COMPLETED);
		createEAttribute(progressEClass, PROGRESS__UNLOCKED);
		createEAttribute(progressEClass, PROGRESS__COMPLETED_AT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(studentEClass, Student.class, "Student", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStudent_Id(), ecorePackage.getEString(), "id", null, 1, 1, Student.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStudent_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, Student.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStudent_CreatedAt(), ecorePackage.getEDate(), "createdAt", null, 1, 1, Student.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(studentModelEClass, StudentModel.class, "StudentModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStudentModel_ModelVersion(), ecorePackage.getEInt(), "modelVersion", "1", 0, 1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStudentModel_LastUpdated(), ecorePackage.getEDate(), "lastUpdated", null, 1, 1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_Student(), this.getStudent(), null, "student", null, 1, 1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_Concepts(), this.getConcept(), null, "concepts", null, 0, -1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_Activities(), this.getActivity(), null, "activities", null, 0, -1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_LearningObjectives(), this.getLearningObjective(), null, "learningObjectives", null, 0, -1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_ErrorPatterns(), this.getErrorPattern(), null, "errorPatterns", null, 0, -1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_ConceptMasteries(), this.getConceptMastery(), null, "conceptMasteries", null, 0, -1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_Attempts(), this.getAttempt(), null, "attempts", null, 0, -1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_HintUsages(), this.getHintUsage(), null, "hintUsages", null, 0, -1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentModel_Progress(), this.getProgress(), null, "progress", null, 0, -1, StudentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conceptEClass, Concept.class, "Concept", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConcept_Id(), ecorePackage.getEString(), "id", null, 1, 1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcept_Name(), ecorePackage.getEString(), "name", null, 1, 1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcept_Prerequisites(), this.getConcept(), null, "prerequisites", null, 0, -1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcept_MinimumMasteryToUnlock(), ecorePackage.getEDouble(), "minimumMasteryToUnlock", null, 0, 1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcept_Activities(), this.getActivity(), null, "activities", null, 0, -1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcept_ReinforcementActivities(), this.getActivity(), null, "reinforcementActivities", null, 0, -1, Concept.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conceptMasteryEClass, ConceptMastery.class, "ConceptMastery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConceptMastery_Student(), this.getStudent(), null, "student", null, 1, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConceptMastery_Concept(), this.getConcept(), null, "concept", null, 1, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConceptMastery_MasteryScore(), ecorePackage.getEDouble(), "masteryScore", null, 0, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConceptMastery_AttemptCount(), ecorePackage.getEInt(), "attemptCount", null, 0, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConceptMastery_SuccessCount(), ecorePackage.getEInt(), "successCount", null, 0, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConceptMastery_FailureCount(), ecorePackage.getEInt(), "failureCount", null, 0, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConceptMastery_ConsecutiveFailures(), ecorePackage.getEInt(), "consecutiveFailures", null, 0, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConceptMastery_HintCount(), ecorePackage.getEInt(), "hintCount", null, 0, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConceptMastery_AverageResolutionTime(), ecorePackage.getEDouble(), "averageResolutionTime", null, 0, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConceptMastery_LastUpdated(), ecorePackage.getEDate(), "lastUpdated", null, 1, 1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConceptMastery_RecentErrorPatterns(), this.getErrorPattern(), null, "recentErrorPatterns", null, 0, -1, ConceptMastery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(learningObjectiveEClass, LearningObjective.class, "LearningObjective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLearningObjective_Id(), ecorePackage.getEString(), "id", null, 1, 1, LearningObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLearningObjective_Description(), ecorePackage.getEString(), "description", null, 1, 1, LearningObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLearningObjective_Concept(), this.getConcept(), null, "concept", null, 1, 1, LearningObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivity_Id(), ecorePackage.getEString(), "id", null, 1, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity_Title(), ecorePackage.getEString(), "title", null, 1, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivity_Concept(), this.getConcept(), null, "concept", null, 1, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivity_LearningObjectives(), this.getLearningObjective(), null, "learningObjectives", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attemptEClass, Attempt.class, "Attempt", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttempt_Id(), ecorePackage.getEString(), "id", null, 1, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttempt_Student(), this.getStudent(), null, "student", null, 1, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttempt_Activity(), this.getActivity(), null, "activity", null, 1, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttempt_Concept(), this.getConcept(), null, "concept", null, 1, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttempt_Successful(), ecorePackage.getEBoolean(), "successful", null, 0, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttempt_FunctionalPassed(), ecorePackage.getEBoolean(), "functionalPassed", null, 0, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttempt_ResolutionTime(), ecorePackage.getELong(), "resolutionTime", null, 0, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttempt_HintCount(), ecorePackage.getEInt(), "hintCount", null, 0, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttempt_SubmittedAt(), ecorePackage.getEDate(), "submittedAt", null, 1, 1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttempt_ErrorPatterns(), this.getErrorPattern(), null, "errorPatterns", null, 0, -1, Attempt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(errorPatternEClass, ErrorPattern.class, "ErrorPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getErrorPattern_Id(), ecorePackage.getEString(), "id", null, 1, 1, ErrorPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getErrorPattern_Concept(), this.getConcept(), null, "concept", null, 1, 1, ErrorPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorPattern_Severity(), ecorePackage.getEString(), "severity", null, 1, 1, ErrorPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorPattern_PedagogicalMeaning(), ecorePackage.getEString(), "pedagogicalMeaning", null, 1, 1, ErrorPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hintUsageEClass, HintUsage.class, "HintUsage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHintUsage_Id(), ecorePackage.getEString(), "id", null, 1, 1, HintUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHintUsage_Student(), this.getStudent(), null, "student", null, 1, 1, HintUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHintUsage_Activity(), this.getActivity(), null, "activity", null, 1, 1, HintUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHintUsage_HintLevel(), ecorePackage.getEInt(), "hintLevel", null, 0, 1, HintUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHintUsage_UsedAt(), ecorePackage.getEDate(), "usedAt", null, 1, 1, HintUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(progressEClass, Progress.class, "Progress", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProgress_Activity(), this.getActivity(), null, "activity", null, 1, 1, Progress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProgress_Concept(), this.getConcept(), null, "concept", null, 1, 1, Progress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProgress_Completed(), ecorePackage.getEBoolean(), "completed", null, 0, 1, Progress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProgress_Unlocked(), ecorePackage.getEBoolean(), "unlocked", null, 0, 1, Progress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProgress_CompletedAt(), ecorePackage.getEDate(), "completedAt", null, 0, 1, Progress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //LearningPackageImpl
